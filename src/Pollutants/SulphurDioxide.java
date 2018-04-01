/**
 * File containing the SulphurDioxide class definition.
 */
package Pollutants;

/**
* Class which calculates the Air Quality Index for sulphur dioxide levels.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public class SulphurDioxide extends Pollutant {

	/**
	 * Constructor, initializes properly the ranges array.
	 */
	public SulphurDioxide(){
		pollutantRanges = new double [][] { {  0,   35},
						    			    { 36,   75},
						    			    { 76,  185},
						    			    {186,  304},
						    			    {305,  604},
						    			    {605,  804},
						    			    {805, 1004} };
	}

}
