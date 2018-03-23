package IO;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DataReader {	
	private int csvAttributeIndex;
	private List<CSVRecord> recordList;
	private CSVRecord dateList;
	
	public DataReader(String filename) throws IOException {
		 Reader csvData = new FileReader(filename);
		 CSVParser parser = CSVParser.parse(csvData, CSVFormat.RFC4180);		 
		 
		 this.recordList = parser.getRecords();
		 this.dateList = recordList.get(0);
		 this.csvAttributeIndex = 1;
		 
		 parser.close();
		 csvData.close();
	}

	/**
	 * @return
	 */
	public boolean areMoreAttributes() {
		return (this.csvAttributeIndex < this.dateList.size());
	}
	
	public String getRawAttributeName() {
		return this.recordList.get(0).get(this.csvAttributeIndex);
	}
	
	public TreeMap<String, String> getRawAttribute() throws IOException {
		TreeMap<String, String> dateAttributeValueMap = new TreeMap<String, String>();
		
		for (CSVRecord csvRecord : this.recordList) {
			dateAttributeValueMap.put(csvRecord.get(0), csvRecord.get(this.csvAttributeIndex));
		 }
		
		this.csvAttributeIndex += 2;
		return dateAttributeValueMap;
	}
}
