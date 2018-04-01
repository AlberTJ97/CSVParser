/**
 * File containing the NitrogenDioxide class definition.
 */
package Pollutants;

/**
* Class which calculates the Air Quality Index for nitrogen dioxide levels.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public class NitrogenDioxide extends Pollutant {

	/**
	 * Constructor, initializes properly the ranges array.
	 */
	public NitrogenDioxide(){
		pollutantRanges = new double [][] { {   0,   53},
    			                            {  54,  100},
						    			    { 101,  360},
						    			    { 361,  649},
						    			    { 650, 1249},
						    			    {1250, 1649},
						    			    {1650, 2049} };
	}
}
