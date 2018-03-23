package Parser;
import java.io.File;
import java.io.IOException;

import Elements.Station;
import Elements.Zone;
import IO.DataReader;

public class StartParse {
	
	public static void main(String[] args) throws IOException {
		File zoneFolder = new File(args[0]);
		File[] listOfStationFolder = zoneFolder.listFiles();
		
		// Read all the folders in the zone passed by argument.

		Zone zone = new Zone(zoneFolder.getName());
		for (File stationFolder: listOfStationFolder) {
			zone.add(processStation(stationFolder));
		}
		
		System.out.println(zone);
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
				station.addAttribute(reader.getRawAttributeName(), reader.getRawAttribute());
			}
		}
		
		return station;
	}

}
