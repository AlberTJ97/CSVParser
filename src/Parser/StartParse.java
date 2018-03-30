package Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Elements.Attribute;
import Elements.Station;
import Elements.Zone;
import IO.AirDataReader;
import IO.VehicleDataReader;

/**
 * Class that starts the parsing. It receives in the main method the name of the
 * zone folder. After that, it analyzes the subfolders the zone has (it's
 * stations) and generate the station with all the attributes in the different
 * CSV.
 */
public class StartParse {

	/**
	 * commonAttributes represents a collection of the common attributes for the
	 * stations in the zone.
	 */
	private static ArrayList<String> commonAttributes;

	/**
	 * @throws IOException
	 * 
	 */
	private static void readCommonAttributes(File commonAttribute) throws IOException {
		BufferedReader attributeReader = new BufferedReader(new FileReader(commonAttribute));
		while (attributeReader.ready()) {
			commonAttributes.add(attributeReader.readLine().trim());
		}
		attributeReader.close();
	}

	/**
	 * Creates the station reading the csv in the folder that receives by parameter.
	 * 
	 * @param absolutePath
	 * @return
	 * @throws Exception 
	 */
	private static Station processStation(File stationCSVFolder) throws Exception {
		Station station = new Station(stationCSVFolder.getName());

		for (File stationCSV : stationCSVFolder.listFiles()) {
			AirDataReader reader = new AirDataReader(stationCSV.getAbsolutePath());

			while (reader.areMoreAttributes()) {
				String attributeName = reader.getRawAttributeName();
				for (String acceptedAttribute : commonAttributes) {
					if (!attributeName.contains(acceptedAttribute)) {
						continue;
					}
					station.addAttribute(new Attribute(acceptedAttribute, reader.getRawAttribute()));
				}
				reader.nextAttribute();
			}
		}

		return station;
	}

	/**
	 * Method that makes the parsing, reading the subfolders of the folder received
	 * by parameter.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File zoneFolder = new File(args[0]);
		File[] listOfStationFolder = zoneFolder.listFiles();
		Zone zone = new Zone(zoneFolder.getName());
		commonAttributes = new ArrayList<String>();
		String vehicleDataFilename = "";

		// Get the zone common attributes
		for (File stationFolder : listOfStationFolder) {
			if (stationFolder.isFile()) {
				if (stationFolder.getName().equals("common_attributes")) {
					readCommonAttributes(stationFolder);					
				}
				else {
					vehicleDataFilename = stationFolder.getAbsolutePath();
				}
			}
		}

		// Read all the stations in the zone.
		for (File stationFolder : listOfStationFolder) {
			if (stationFolder.isDirectory()) {
				zone.add(processStation(stationFolder));
			}
		}
		
		Station meanStation = zone.getMeanStation();
		
		// Read the vehicle data
		if (!vehicleDataFilename.isEmpty()) {
			VehicleDataReader vehicleDataReader = new VehicleDataReader(vehicleDataFilename);
			while (vehicleDataReader.areMoreAttributes()) {
				meanStation.addVehicleAttribute(new Attribute(vehicleDataReader.getRawAttributeName(), vehicleDataReader.getRawAttribute()));
				vehicleDataReader.nextAttribute();
			}
		}		
		
		System.out.println(meanStation);
		
		// TODO arff Writer
//		BufferedWriter writer = new BufferedWriter(new FileWriter("a.txt"));
//		writer.write(meanStation.toString());
//		writer.close();
	}

}
