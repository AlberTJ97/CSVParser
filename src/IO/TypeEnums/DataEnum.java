/**
 * VehicleEnum.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 24-03-2018
 */
package IO.TypeEnums;

/**
 * Enum for parsing data of the csv.
 */
public enum DataEnum {
	JAN("Enero"),
	FEB("Febrero"),
	MAR("Marzo"),
	APR("Abril"),
	MAY("Mayo"),
	JUN("Junio"),
	JUL("Julio"),
	AUG("Agosto"),
	SEP("Septiembre"),
	OCT("Octubre"),
	NOV("Noviembre"),
	DEC("Diciembre");
	
	/** traductionName */
	private String traductionName;
	
	/**
	 * @param traductionName
	 */
	private DataEnum(String traductionName) {
		this.traductionName = traductionName;
	}
	
	/**
	 * @param traductionName
	 * @return
	 * @throws Exception
	 */
	public static String parse(String traductionName) throws Exception {
		String year = traductionName.split("\\s")[0];
		String month = DataEnum.parseMonth(traductionName.split("\\s")[1]);
		month = month.substring(0, 1) + month.substring(1, 3).toLowerCase();
		month = getIntegerMonth(month);
		
		return month + "-" + year;
	}
	
	/**
	 * @param rawMonth
	 * @return
	 * @throws Exception
	 */
	private static String parseMonth(String rawMonth) throws Exception {
		for (DataEnum vehicleData : DataEnum.values()) {
			if (vehicleData.getTraductionName().equals(rawMonth.trim())) {
				return vehicleData.name();
			}
		}
		throw new Exception("Fail to parse: " + rawMonth);
	}
	
	/**
	 * Returns the month in integer.
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static String getIntegerMonth(String month) throws Exception {
		int i = 1;
		for (DataEnum vehicleData : DataEnum.values()) {
			if (vehicleData.name().equals(month.trim().toUpperCase())) {
				if (i < 10) {
					return "0" + String.valueOf(i);
				}
				else {
					return String.valueOf(i);					
				}
			}
			i++;
		}
		throw new Exception("Fail to get Integer month of: " + month);
	}

	/**
	 * @return the traductionName
	 */
	private String getTraductionName() {
		return traductionName;
	}
}
