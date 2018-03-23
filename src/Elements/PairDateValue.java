/**
 * PairDateValue.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 23-03-2018
 */
package Elements;

/**
 * [DESCRIPTION]
 */
public class PairDateValue {
	private String date;
	private Double value;
	
	public PairDateValue(String date, String value) {
		this.setDate(date);		
		Double doubleValue = value.trim().isEmpty() ? null : Double.valueOf(value);
		this.setValue(doubleValue);
	}
	
	public PairDateValue(String date, Double value) {
		this.setDate(date);
		this.setValue(value);
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
}
