package Elements;
import java.util.ArrayList;
import java.util.TreeMap;

public class Attribute implements Comparable<Attribute> {
	private String attributeName;
	private ArrayList<PairDateValue> pairDateValueArray;
	
	/**
	 * @param attributeName2
	 */
	public Attribute(String attributeName) {
		this.attributeName = attributeName;
		this.pairDateValueArray = new ArrayList<PairDateValue>();
	}

	public Attribute(String attributeName, ArrayList<PairDateValue> pairDateValueArray) {
		this.attributeName = attributeName;
		this.pairDateValueArray = pairDateValueArray;
	}
	
	public void add(String date, String value) {
		this.pairDateValueArray.add(new PairDateValue(date, value));
	}	
	
	public void add(String date, Double value) {
		this.pairDateValueArray.add(new PairDateValue(date, value));
	}	
	
	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the valueArray
	 */
	public ArrayList<PairDateValue> getPairDateValueArray() {
		return pairDateValueArray;
	}

	public String toString() {
		String resultString = this.attributeName + " : [";
		for (PairDateValue pairDateValue : this.getPairDateValueArray()) {				
			resultString += pairDateValue.getValue() + ", ";
		}
		return resultString + "]";
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Attribute anotherAttribute) {
		return this.getAttributeName().compareTo(anotherAttribute.getAttributeName());
	}

}
