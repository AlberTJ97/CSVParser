/**
 * File containing the CarbonMonoxide class definition.
 */
package Pollutants;

/**
* Class which calculates the Air Quality Index for carbon monoxide levels.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public class CarbonMonoxide extends Pollutant {

	/**
	 * Constructor, initializes properly the ranges array.
	 */
	public CarbonMonoxide() {
		pollutantRanges = new double [][] { {   0,  4.4},
						    			    { 4.5,  9.4},
						    			    { 9.5, 12.4},
						    			    {12.5, 15.4},
						    			    {15.5, 30.4},
						    			    {30.5, 40.4},
						    			    {40.5, 50.4} };
	}

}
