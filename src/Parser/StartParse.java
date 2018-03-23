package Parser;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import Elements.Station;
import IO.DataReader;

public class StartParse {
	
	private static String INPUT_FOLDER_NAME = "input/";

	public static void main(String[] args) throws IOException {
		File folder = new File(INPUT_FOLDER_NAME);
		File[] listOfFiles = folder.listFiles();
		
		// Read all the files starting with the name passed by argument.
		String stationToRead = args[0];
		Station station = new Station(stationToRead);
		for (File file: listOfFiles) {
			if (file.getName().startsWith(stationToRead)) {
				DataReader reader = new DataReader(file.getAbsolutePath());
				while(reader.areMoreAttributes()) {
					System.out.println("\n\n\t **" + reader.getAttributeName());
					for (Map.Entry<String, String> a: reader.getAttribute().entrySet()) {
						System.out.println(a);
					}
				}
			}
		}
	}

}
