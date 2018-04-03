package Elements;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

import Pollutants.CarbonMonoxide;
import Pollutants.NitrogenDioxide;
import Pollutants.Ozone;
import Pollutants.ParticularizedMaterial10;
import Pollutants.ParticularizedMaterial25;
import Pollutants.Pollutant;
import Pollutants.SulphurDioxide;

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
	
	/**
	 * Adds the Air Quality Index attribute. The pullutants attributes must be previously stored at attributeArray
	 */
	public void addAQIAttribute() {
		// We assume that the pollutants attributes are in the first positions of attributeArray
		final int FIRST_POLLUTANT_INDEX = 0;
		final int LAST_POLLUTANT_INDEX  = 5;
		// The pollutants are ordered alphabetically, like in attributeArray
		final Pollutant [] pollutants = { new CarbonMonoxide(),
				  						  new NitrogenDioxide(),
										  new Ozone(),
										  new ParticularizedMaterial10(),
										  new ParticularizedMaterial25(),
										  new SulphurDioxide() };
		Attribute aqiAttribute = new Attribute("AQI");
		for (int i = 0; i < attributeArray.get(FIRST_POLLUTANT_INDEX).getPairDateValue().size(); ++i) {
			ArrayList<Double> aqi = new ArrayList<Double>();
			for (int j = FIRST_POLLUTANT_INDEX; j <= LAST_POLLUTANT_INDEX; ++j) {
				aqi.add(pollutants[j].calculateAssociatedAQI(attributeArray.get(j).getPairDateValueAt(i).getValue()));
			}
			String date = attributeArray.get(FIRST_POLLUTANT_INDEX).getPairDateValueAt(i).getDate();
			double value = Collections.max(aqi);
			aqiAttribute.add(date, value);
		}
		this.addAttribute(aqiAttribute);
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
	public String getStationName() {
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
