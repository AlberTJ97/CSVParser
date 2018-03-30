package Elements;

import java.util.ArrayList;

/**
 * Attribute represents a Measure in the Station, such as BEN, CO... that
 * contains an array of values.
 */
public class Attribute implements Comparable<Attribute> {
	/** attributeName represents the name of the attribute, such as BEN. */
	private String attributeName;
	/**
	 * pairDateValueArray represents the values taken for that attribute in an
	 * specific date.
	 */
	private ArrayList<PairDateValue> pairDateValueArray;

	/**
	 * Default constructor.
	 * 
	 * @param attributeName2
	 */
	public Attribute(String attributeName) {
		this.attributeName = attributeName;
		this.pairDateValueArray = new ArrayList<PairDateValue>();
	}

	/**
	 * Default constructor.
	 * 
	 * @param attributeName
	 * @param pairDateValueArray
	 */
	public Attribute(String attributeName, ArrayList<PairDateValue> pairDateValueArray) {
		this.attributeName = attributeName;
		this.pairDateValueArray = pairDateValueArray;
		this.pairDateValueArray.sort(null);
	}

	/**
	 * Adds a new pair of string string to the array.
	 * 
	 * @param date
	 * @param value
	 */
	public void add(String date, String value) {
		this.pairDateValueArray.add(new PairDateValue(date, value));
		this.pairDateValueArray.sort(null);
	}

	/**
	 * Adds a new pair of string double to the array.
	 * 
	 * @param date
	 * @param value
	 */
	public void add(String date, Double value) {
		this.pairDateValueArray.add(new PairDateValue(date, value));
	}
	
	/**
	 * Concat the elements with another attribute.
	 * @param newAttribute
	 */
	public void concat(Attribute newAttribute) {
		for (int i = 0; i < newAttribute.getElementNumber() ; ++i) {
			PairDateValue newPair = newAttribute.getPairDateValueAt(i);
			this.add(newPair.getDate(), newPair.getValue());
		}
		this.pairDateValueArray.sort(null);
	}

	/**
	 * @param attributeName
	 *          the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the valueArray
	 */
	public PairDateValue getPairDateValueAt(int index) {
		return pairDateValueArray.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String resultString = this.attributeName + " : [";
		for (PairDateValue pairDateValue : this.pairDateValueArray) {
			resultString += pairDateValue.getValue() + ", ";
//			resultString += pairDateValue.getDate() + ", ";
		}
		return resultString + "]";
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Attribute anotherAttribute) {
		return this.getAttributeName().compareTo(anotherAttribute.getAttributeName());
	}
	
	/**
	 * @param anotherAttribute
	 * @return
	 */
	@Override
	public boolean equals(Object object) {
		Attribute anotherAttribute = (Attribute) object;
		return (this.compareTo(anotherAttribute) == 0);
	}

	/**
	 * @return
	 */
	public int getElementNumber() {
		return this.pairDateValueArray.size();
	}	

}
