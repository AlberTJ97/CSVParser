package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Elements.PairDateValue;
import IO.TypeEnums.DataEnum;
import IO.TypeEnums.VehicleEnum;
import IO.TypeEnums.VehicleFuelEnum;

/**
 * Reader of the data from a CSV. It uses the API Apache Commons CSV.
 */
public class VehicleDataReader {
	/** START_LINE */
	private static final int START_LINE = 6;
	/** END_LINE */
	private static final int END_LINE = 39;
	/** csvAttributeIndex */
	private int csvAttributeIndex;
	/** csvSubAttributeIndex */
	private int csvSubAttributeIndex;
	/** recordList */
	private List<CSVRecord> recordList;
	/** attributeList */
	private CSVRecord attributeList;
	/** subAttributeList */
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
	 * @throws Exception 
	 */
	public String getRawAttributeName() throws Exception {
		String currentAttributeName = VehicleEnum.parse(this.attributeList.get(csvAttributeIndex));
		String currentSubAttributeName = VehicleFuelEnum.parse(this.subAttributeList.get(csvSubAttributeIndex));
		return currentAttributeName + currentSubAttributeName;
	}

	/**
	 * Method that returns an array of all the measures for an attribute.
	 * 
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<PairDateValue> getRawAttribute() throws Exception {
		ArrayList<PairDateValue> pairDateValueArray = new ArrayList<PairDateValue>();
		
		for (int i = 3; i < END_LINE; ++i) {
			String date = DataEnum.parse(this.recordList.get(i).get(0));
			String value = this.recordList.get(i).get(this.csvSubAttributeIndex);
			pairDateValueArray.add(new PairDateValue(date, value));
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
