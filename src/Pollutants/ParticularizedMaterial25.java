/**
 * File containing the ParticularizedMaterial25 class definition.
 */
package Pollutants;

/**
* Class which calculates the Air Quality Index for particularized materials (2,5x10⁻³ mm) levels.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public class ParticularizedMaterial25 extends Pollutant {

	/**
	 * Constructor, initializes properly the ranges array.
	 */
	public ParticularizedMaterial25(){
		pollutantRanges = new double [][] { {    0,    12},
    			   							{ 12.1,  35.4},
 						    			    { 35.5,  55.4},
						    		        { 55.5, 150.4},
						    			    {150.5, 250.4},
						    			    {250.5, 350.4},
						    			    {350.5, 500.4}};
	}

}
