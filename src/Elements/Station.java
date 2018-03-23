package Elements;
import java.util.ArrayList;

public class Station {

	private String stationName;
	private ArrayList<Attribute> attributeArray;

	public Station(String stationName) {
		this.stationName = stationName;
		this.attributeArray = new ArrayList<Attribute>();
	}

	/**
	 * @param attributeName
	 * @param attribute
	 */
	public void addAttribute(Attribute newAttribute) {
		this.getAttributeArray().add(newAttribute);		
		this.getAttributeArray().sort(null);
	}
	
	public String toString() {
		String resultString = "\tList of Attributes for Station " + this.getStationName() + System.lineSeparator();
		for (Attribute attribute : this.attributeArray) {
			resultString += "\t\t" + attribute + System.lineSeparator();
		}
		
		return resultString;
	}

	/**
	 * @return the stationName
	 */
	private String getStationName() {
		return stationName;
	}

	/**
	 * @return the attributeArray
	 */
	public ArrayList<Attribute> getAttributeArray() {
		return attributeArray;
	}
}
