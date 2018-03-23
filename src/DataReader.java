import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.*;

public class DataReader {
	BufferedReader reader;
	
	public DataReader(String filename) throws IOException {
		 Reader csvData = new FileReader(filename);
		 CSVParser parser = CSVParser.parse(csvData, CSVFormat.RFC4180);
		 for (CSVRecord csvRecord : parser) {
			 System.out.println(csvRecord.get(9));
		 }		 
	}

}
