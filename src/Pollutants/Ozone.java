/**
 * File containing the Ozone abstract definition.
 */
package Pollutants;

/**
* Class which calculates the Air Quality Index for Ozone levels.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public class Ozone extends Pollutant {

	/**
	 * Constructor, initializes properly the ranges array.
	 */
	public Ozone(){
		pollutantRanges = new double [][] { {  0,  54},
							       			{ 55,  70},
							       			{ 71,  85},
							       			{ 86, 105},
							       			{106, 200} };
	}
}
