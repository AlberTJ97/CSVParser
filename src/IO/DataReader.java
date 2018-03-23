package IO;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Elements.PairDateValue;

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
	
	public ArrayList<PairDateValue> getRawAttribute() throws IOException {
		ArrayList<PairDateValue> pairDateValueArray = new ArrayList<PairDateValue>();
		
		boolean startRecord = true;
		for (CSVRecord csvRecord : this.recordList) {
			if (startRecord) { // Don't introduce header.
				startRecord = !startRecord;
			}
			else {
				pairDateValueArray.add(new PairDateValue(csvRecord.get(0), csvRecord.get(this.csvAttributeIndex)));				
			}
		 }
		
		
		return pairDateValueArray;
	}

	/**
	 * 
	 */
	public void nextAttribute() {
		this.csvAttributeIndex += 2;		
	}
}
