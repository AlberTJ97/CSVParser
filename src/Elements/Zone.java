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
