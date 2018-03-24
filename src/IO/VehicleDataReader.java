package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Elements.PairDateValue;

/**
 * Reader of the data from a CSV. It uses the API Apache Commons CSV.
 */
public class VehicleDataReader {
	private static final int START_LINE = 6;
	private static final int END_LINE = 46;
	/** csvAttributeIndex */
	private int csvAttributeIndex;
	/** recordList */
	private List<CSVRecord> recordList;
	/** dateList */
	private CSVRecord attributeList;
	private int csvSubAttributeIndex;
	private CSVRecord subAttributeList;

	/**
	 * Save the records of the CSV.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public VehicleDataReader(String filename) throws IOException {
		BufferedReader csvData = new BufferedReader(new FileReader(filename));
		for (int i = 0; i < START_LINE; ++i) {
			csvData.readLine();
		}
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.RFC4180);
		
		this.recordList = parser.getRecords();
		this.attributeList = recordList.get(0);
		this.subAttributeList = recordList.get(1);
		this.csvAttributeIndex = 1;
		this.csvSubAttributeIndex = 1;
		
//		System.out.println(recordList.size());
//		System.out.println(this.attributeList.size());
//		for (String a: this.attributeList) {
//			System.out.println(a);
//		}

		parser.close();
		csvData.close();
	}

	/**
	 * Return true if there are more attributes to search.
	 * 
	 * @return
	 */
	public boolean areMoreAttributes() {
		return (this.csvAttributeIndex < this.attributeList.size());
	}

	/**
	 * @return the current attribute name.
	 */
	public String getRawAttributeName() {
		String currentAttributeName = this.attributeList.get(csvAttributeIndex) + " " +
				this.subAttributeList.get(csvSubAttributeIndex);
		return currentAttributeName;
	}

	/**
	 * Method that returns an array of all the measures for an attribute.
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<PairDateValue> getRawAttribute() throws IOException {
		ArrayList<PairDateValue> pairDateValueArray = new ArrayList<PairDateValue>();
		
		System.out.println(this.attributeList.get(csvAttributeIndex));
		
		boolean startRecord = true;
		for (int i = START_LINE; i < END_LINE; ++i) {
			if (startRecord) { // Don't introduce header.
				startRecord = !startRecord;
			}
			else {
				pairDateValueArray.add(new PairDateValue(this.recordList.get(i).get(0), 
						this.recordList.get(i).get(this.csvAttributeIndex)));
			}
		}
		return pairDateValueArray;
	}

	/**
	 * Advance to the nextAttribute. Note that is each 2 position because in the csv
	 * file there are flags.
	 */
	public void nextAttribute() {
		if ((csvSubAttributeIndex % 3) != 0) {
			this.csvSubAttributeIndex ++;
		}
		else {
			this.csvAttributeIndex += 3;
			this.csvSubAttributeIndex ++;
		}
	}
}
