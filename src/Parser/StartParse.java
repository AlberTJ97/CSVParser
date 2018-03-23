package Parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Elements.Attribute;
import Elements.Station;
import Elements.Zone;
import IO.DataReader;

public class StartParse {
	
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
	 * @param absolutePath
	 * @return
	 * @throws IOException 
	 */
	private static Station processStation(File stationCSVFolder) throws IOException {
		Station station = new Station(stationCSVFolder.getName());
		
		for (File stationCSV: stationCSVFolder.listFiles()) {
			DataReader reader = new DataReader(stationCSV.getAbsolutePath());
			
			while(reader.areMoreAttributes()) {
				String attributeName = reader.getRawAttributeName();
				for (String acceptedAttribute : commonAttributes) {
					if (!attributeName.contains(acceptedAttribute)) { continue; }
					station.addAttribute(new Attribute(acceptedAttribute, reader.getRawAttribute()));
				}
				reader.nextAttribute();
			}
		}
		
		return station;
	}
	
	public static void main(String[] args) throws IOException {
		File zoneFolder = new File(args[0]);
		File[] listOfStationFolder = zoneFolder.listFiles();		
		Zone zone = new Zone(zoneFolder.getName());
		commonAttributes = new ArrayList<String>();
		
		// Get the zone common attributes
		for (File stationFolder: listOfStationFolder) {
			if (stationFolder.isFile()) {
				readCommonAttributes(stationFolder);
			}
		}
		
		// Read all the stations in the zone.
		for (File stationFolder: listOfStationFolder) {
			if (stationFolder.isDirectory()) {
				zone.add(processStation(stationFolder));				
			}
		}
		
		System.out.println(zone.getMeanStation());
	}

}
