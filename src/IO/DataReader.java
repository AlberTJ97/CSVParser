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

/**
 * Reader of the data from a CSV. It uses the API Apache Commons CSV.
 */
public class DataReader {
	/** csvAttributeIndex */
	private int csvAttributeIndex;
	/** recordList */
	private List<CSVRecord> recordList;
	/** dateList */
	private CSVRecord dateList;

	/**
	 * Save the records of the CSV.
	 * 
	 * @param filename
	 * @throws IOException
	 */
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
	 * Return true if there are more attributes to search.
	 * 
	 * @return
	 */
	public boolean areMoreAttributes() {
		return (this.csvAttributeIndex < this.dateList.size());
	}

	/**
	 * @return the current attribute name.
	 */
	public String getRawAttributeName() {
		return this.recordList.get(0).get(this.csvAttributeIndex);
	}

	/**
	 * Method that returns an array of all the measures for an attribute.
	 * 
	 * @return
	 * @throws IOException
	 */
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
	 * Advance to the nextAttribute. Note that is each 2 position because in the csv
	 * file there are flags.
	 */
	public void nextAttribute() {
		this.csvAttributeIndex += 2;
	}
}
