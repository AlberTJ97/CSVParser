/**
 * Zone.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 23-03-2018
 */
package Elements;

import java.util.ArrayList;

/**
 * Zone represents a set of stations.
 */
public class Zone {

	/** zoneName is the name of the zone. */
	private String zoneName;
	/** stationArray is the collection of stations. */
	private ArrayList<Station> stationArray;

	/**
	 * Default constructor.
	 * 
	 * @param string
	 */
	public Zone(String zoneName) {
		this.zoneName = zoneName;
		stationArray = new ArrayList<Station>();
	}

	/**
	 * @param newStation
	 */
	public void add(Station newStation) {
		this.stationArray.add(newStation);
	}

	/**
	 * For each value in the attribute elements of all the stations, calculate the
	 * mean and add it to a new mean station.
	 * 
	 * @return
	 */
	public Station getMeanStation() {
		Station meanStation = new Station(this.getZoneName());
		final int numberOfStations = this.stationArray.size();
		final int numberOfAttributes = this.stationArray.get(0).getAttributeArray().size();
		final int numberOfAttributeData = this.stationArray.get(0).getAttributeArray().get(0).getPairDateValueArray()
				.size();

		// For each attribute.
		for (int attributeIndex = 0; attributeIndex < numberOfAttributes; ++attributeIndex) {
			Attribute meanAttribute = new Attribute(
					stationArray.get(0).getAttributeArray().get(attributeIndex).getAttributeName());

			// For each attribute element.
			for (int attributeDataIndex = 0; attributeDataIndex < numberOfAttributeData; ++attributeDataIndex) {
				String date = stationArray.get(0).getAttributeArray().get(attributeIndex).getPairDateValueArray()
						.get(attributeDataIndex).getDate();
				Double meanAttributeValue = 0.0;
				int counterOfNotNullAttributes = 0;

				// For each station, calculate the mean between it's elements.
				for (int stationIndex = 0; stationIndex < numberOfStations; ++stationIndex) {
					Double elementValue = stationArray.get(stationIndex).getAttributeArray().get(attributeIndex)
							.getPairDateValueArray().get(attributeDataIndex).getValue();
					if (elementValue != null) {
						meanAttributeValue += elementValue;
						counterOfNotNullAttributes++;
					}
				}

				if (counterOfNotNullAttributes != 0) {
					meanAttributeValue /= counterOfNotNullAttributes;
				}
				double roundedMeanAttributeValue = Math.round(meanAttributeValue * 100d) / 100d;
				meanAttribute.add(date, roundedMeanAttributeValue);
			}
			meanStation.addAttribute(meanAttribute);
		}

		return meanStation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String resultString = "List of stations for zone: " + this.getZoneName() + "\n";
		for (Station station : this.getStationArray()) {
			resultString += station;
		}
		return resultString;
	}

	/**
	 * @return the stationArray
	 */
	private ArrayList<Station> getStationArray() {
		return stationArray;
	}

	/**
	 * @return the zoneName
	 */
	private String getZoneName() {
		return zoneName;
	}
}
