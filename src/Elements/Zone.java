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
 * [DESCRIPTION]
 */
public class Zone {

	private String zoneName;
	private ArrayList<Station> stationArray;

	/**
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
	 * @return
	 */
	public Station getMeanStation() {
		Station meanStation = new Station(this.getZoneName());
		final int numberOfStations = this.stationArray.size();
		final int numberOfAttributes = this.stationArray.get(0).getAttributeArray().size();
		final int numberOfAttributeData = this.stationArray.get(0).getAttributeArray().get(0).getPairDateValueArray().size();

		// Por cada atributo
		for (int attributeIndex = 0; attributeIndex < numberOfAttributes; ++attributeIndex) {
			Attribute meanAttribute = new Attribute(stationArray.get(0).getAttributeArray().get(attributeIndex).getAttributeName());
			
			// Por cada elemento del atributo
			for (int attributeDataIndex = 0; attributeDataIndex < numberOfAttributeData; ++attributeDataIndex) {
				String date = stationArray.get(0).getAttributeArray().get(attributeIndex).getPairDateValueArray().get(attributeDataIndex).getDate();
				Double meanAttributeValue = 0.0;
				int counterOfNotNullAttributes = 0;
				
				// Por cada estación hago la media del elemento del atributo.
				for (int stationIndex = 0; stationIndex < numberOfStations; ++stationIndex) {
					Double elementValue = stationArray.get(stationIndex).getAttributeArray().get(attributeIndex).
							getPairDateValueArray().get(attributeDataIndex).getValue();
					if (elementValue != null) {
						meanAttributeValue += elementValue;
						counterOfNotNullAttributes++;
					}
				}
				
				if (counterOfNotNullAttributes != 0) {	meanAttributeValue /= counterOfNotNullAttributes;	}			
				double roundedMeanAttributeValue = Math.round(meanAttributeValue * 100d) / 100d;
				meanAttribute.add(date, roundedMeanAttributeValue);
			}			
			meanStation.addAttribute(meanAttribute);			
		}
		
		return meanStation;
	}
	
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
