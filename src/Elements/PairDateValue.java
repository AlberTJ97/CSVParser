/**
 * PairDateValue.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 23-03-2018
 */
package Elements;

/**
 * Represents the date of a measure and it's value.
 */
public class PairDateValue implements Comparable<PairDateValue> {
	/** date represents the date of the measure. */
	private String date;
	/** value represents the value. */
	private Double value;

	/**
	 * Default constructor with string string.
	 * 
	 * @param date
	 * @param value
	 */
	public PairDateValue(String date, String value) {
		this.setDate(date);
		Double doubleValue = value.trim().isEmpty() ? null : Double.valueOf(value);
		this.setValue(doubleValue);
	}

	/**
	 * Default constructor with string double.
	 * 
	 * @param date
	 * @param value
	 */
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
	 * @param date
	 *          the date to set
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
	 * @param value
	 *          the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PairDateValue anotherPair) {
		String day1 = this.getDate().split("-")[0];
		String day2 = anotherPair.getDate().split("-")[0];
		String month1 = this.getDate().split("-")[1];
		String month2 = anotherPair.getDate().split("-")[1];
		String year1 = (this.getDate().split("-").length > 2) ? this.getDate().split("-")[2] : "";
		String year2 = (anotherPair.getDate().split("-").length > 2) ? anotherPair.getDate().split("-")[2] : "";

		if (year1.compareTo(year2) != 0) {
			return year1.compareTo(year2);
		}
		else if (month1.compareTo(month2) != 0) {
			return month1.compareTo(month2);
		}
		else {
			return day1.compareTo(day2);
		}
	}

	/**
	 * @return
	 */
	public int getMonth() {
		return (this.getDate().split("-").length > 2) ? Integer.parseInt(this.getDate().split("-")[1])
				: Integer.parseInt(this.getDate().split("-")[0]);
	}

	/**
	 * @return
	 */
	public int getYear() {
		return (this.getDate().split("-").length > 2) ? Integer.parseInt(this.getDate().split("-")[2])
				: Integer.parseInt(this.getDate().split("-")[1]);
	}

}
