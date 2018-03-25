/**
 * VehicleEnum.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 24-03-2018
 */
package IO.TypeEnums;

/**
 * Enum for parsing vehicles types.
 */
public enum VehicleEnum {
	VCF("Camiones y furgonetas"),
	VCP("Camiones hasta 3.500 kg"),
	VCG("Camiones m�s de 3.500 kg"),
	VF("Furgonetas"),
	VG("Guaguas"),
	VT("Turismos"),
	VM("Motocicletas");
	
	private String traductionName;
	
	private VehicleEnum(String traductionName) {
		this.traductionName = traductionName;
	}
	
	public static String parse(String traductionName) throws Exception {
		for (VehicleEnum vehicle : VehicleEnum.values()) {
			if (vehicle.getTraductionName().equals(traductionName.trim())) {
				return vehicle.name();
			}
		}
		throw new Exception("Fail to parse: " + traductionName);
	}

	/**
	 * @return the traductionName
	 */
	private String getTraductionName() {
		return traductionName;
	}
}
