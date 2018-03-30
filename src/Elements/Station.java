package Elements;

import java.time.Month;
import java.time.Year;
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
		if (attributeArray.contains(newAttribute)) {
			int repeatedAttributeIndex = this.attributeArray.indexOf(newAttribute);
			this.attributeArray.get(repeatedAttributeIndex).concat(newAttribute);
		}
		else {
			this.attributeArray.add(newAttribute);
			this.attributeArray.sort(null);
		}
	}

	/**
	 * Custom method for vehicles. For each month, it estimate the daily amount of vehicles.
	 * @param attribute
	 */
	public void addVehicleAttribute(Attribute attribute) {
		Attribute newAttribute = new Attribute(attribute.getAttributeName());
		
		for (int i = 0; i < attribute.getElementNumber(); ++i) {
			PairDateValue attributePair = attribute.getPairDateValueAt(i);			
			int monthLength = Month.of(attributePair.getMonth()).length(Year.isLeap(attributePair.getYear()));
			
			for (int j = 1; j <= monthLength; ++j) {
				String day = (j < 10) ? ("0" + j) : String.valueOf(j);
				String newDate = day + "-" + attributePair.getDate();
				Double estimatedValue = attributePair.getValue() / monthLength;
				
				newAttribute.add(newDate, estimatedValue);
			}
		}		
		
		this.addAttribute(newAttribute);
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
	public Attribute getAttributeAt(int index) {
		return attributeArray.get(index);
	}

	/**
	 * @return
	 */
	public int getAttributeNumber() {
		return attributeArray.size();
	}
}
