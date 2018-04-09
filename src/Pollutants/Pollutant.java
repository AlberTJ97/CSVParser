/**
 * File containing the Pollutant abstract class definition.
 */
package Pollutants;

/**
* Abstract class which represents a pollutant, it contains the ranges for the Air Quality Index.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public abstract class Pollutant {

	/** Ranges for the Air Quality Index. */
	static int [][] aqiRanges = { {  0,  50},
			                   	  { 51, 100},
			                      {101, 150},
			                      {151, 200},
			                      {201, 300},
			                      {301, 500} };
	/** Ranges for calculate the Air Quality Index associated to an specific pollutant.*/
	double [][] pollutantRanges;
	
	/**
	 * Calculates the air quality index.
	 * @param observedLevel Observed level of the pollutant.
	 * @return Air Quality Index.
	 */
	public double calculateAssociatedAQI(double observedLevel) {
		final int level = (int) observedLevel;
		int rangeIndex = -1;
		for (int i = 0; i < aqiRanges.length; ++i) {
			if (level >= aqiRanges[i][0] && level <= aqiRanges[i][1]) {
				rangeIndex = i;
				break;
			}
		}
		if (rangeIndex < 0) {
			throw new IllegalArgumentException("Bad argument");
		}
		final double iHigh = aqiRanges[rangeIndex][1];
		final double iLow  = aqiRanges[rangeIndex][0];
		final double cHigh = pollutantRanges[rangeIndex][1];
		final double cLow  = pollutantRanges[rangeIndex][0];
		final double result = ((iHigh - iLow) / (cHigh - cLow)) * (level - cLow) + iLow;
		
		return result;
	}
	
	/**
	 * Discretizes a given air quality level.
	 * @param level Air quality level.
	 * @return Discretized level.
	 */
	public static String discretizeLevel(double level) {
		if (level < 0 || level > 500) {
			throw new IllegalArgumentException("Bag argument");
		}
		final double EPS = 0.00001;
		
		if (level < EPS || level < 50 || Math.abs(level - 50) < EPS) {
			return "Buena";
		} else if (level < 100 || Math.abs(level - 100) < EPS) {
			return "Moderada";
		} else if (level < 150 || Math.abs(level - 150) < EPS) {
			return "Insalubre para grupos sensibles";
		} else if (level < 200 || Math.abs(level - 200) < EPS) {
			return "Insalubre";
		} else if (level < 300 || Math.abs(level - 300) < EPS) {
			return "Muy insalubre";
		} else {				// 301 <= level <= 500
			return "Peligroso";
		}
		
	}
}
