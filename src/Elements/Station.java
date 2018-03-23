package Elements;

import java.util.ArrayList;

/**
 * Represents a collection of attributes with it's measures.
 */
public class Station {

	/** stationName is the name of the station. */
	private String stationName;
	/** attributeArray represents the collection of attributes. */
	private ArrayList<Attribute> attributeArray;

	/**
	 * Default constructor.
	 * 
	 * @param stationName
	 */
	public Station(String stationName) {
		this.stationName = stationName;
		this.attributeArray = new ArrayList<Attribute>();
	}

	/**
	 * Adds the attribute and sort the collection.
	 * 
	 * @param attributeName
	 * @param attribute
	 */
	public void addAttribute(Attribute newAttribute) {
		this.getAttributeArray().add(newAttribute);
		this.getAttributeArray().sort(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
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
