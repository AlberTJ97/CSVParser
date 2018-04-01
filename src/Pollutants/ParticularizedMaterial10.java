/**
 * File containing the ParticularizedMaterial10 class definition.
 */
package Pollutants;

/**
* Class which calculates the Air Quality Index for particularized materials (1x10⁻² mm) levels.
* It was created for the project of TID (Tratamiento Inteligente de Datos) 
* course of ULL (Universidad de la Laguna).
* 
* @author  Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
* @version 1.0
* @since   31-03-2018
*/
public class ParticularizedMaterial10 extends Pollutant {

	/**
	 * Constructor, initializes properly the ranges array.
	 */
	public ParticularizedMaterial10(){
		pollutantRanges = new double [][] { {   0,  54},
						    			    {  55, 154},
						    			    { 155, 254},
						    			    { 255, 354},
						    			    { 355, 424},
						    			    { 425, 504},
						    			    { 505, 604} };
	}

}
