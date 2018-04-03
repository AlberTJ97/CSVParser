package Parser;

import Elements.Station;

public class ArffParser {
	private final String DATE_FORMAT = "\"dd-MM-yyyy\"";
	private final String CLASS_ATTRIB = "{25, 52, 75, 100, 125, 150}";
	private final Station meanStation;
	
	/**
	 * Default constructor to parse the information to arff format
	 * 
	 * @param meanStation Mean station
	 */
	public ArffParser(Station meanStation) {
		this.meanStation = meanStation;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		int numberOfDays = meanStation.getAttributeAt(0).getElementNumber();
		String resultString = "";
		
		resultString += "@relation " + meanStation.getStationName() + System.lineSeparator() +
			"@attribute date DATE " + DATE_FORMAT + System.lineSeparator();
		
		for(int i = 0; i < meanStation.getAttributeNumber(); i++) {
			resultString += "@attribute '" + meanStation.getAttributeAt(i).getAttributeName()  + 
					"' NUMERIC " + System.lineSeparator();
		}
		
		resultString += System.lineSeparator() + "@data" + System.lineSeparator();
		
		for(int i = 0; i < numberOfDays; i++) {
			for(int j = 0; j < meanStation.getAttributeNumber(); j++) {
				resultString += (j == 0) ? meanStation.getAttributeAt(j).getPairDateValueAt(i).getDate() + "," : "";
				resultString += meanStation.getAttributeAt(j).getPairDateValueAt(i).getValue();
				resultString += (j + 1 < numberOfDays) ? "," : "";
			}
			resultString += System.lineSeparator();
		}
		
		return resultString;
	}
}
