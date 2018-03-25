/**
 * VehicleEnum.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 24-03-2018
 */
package IO.TypeEnums;

/**
 * Enum for parsing vehicles fuels.
 */
public enum VehicleFuelEnum {
	G("Gasolina"),
	D("Di�sel"),
	E("El�ctrico");
	
	private String traductionName;
	
	private VehicleFuelEnum(String traductionName) {
		this.traductionName = traductionName;
	}
	
	public static String parse(String traductionName) throws Exception {
		for (VehicleFuelEnum vehicleFuel : VehicleFuelEnum.values()) {
			if (vehicleFuel.getTraductionName().equals(traductionName.trim())) {
				return vehicleFuel.name();
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
