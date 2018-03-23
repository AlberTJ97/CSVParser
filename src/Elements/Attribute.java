package Elements;
import java.util.TreeMap;

public class Attribute {
	private String attributeName;
	private TreeMap<String, String> valueArray;

	public Attribute(String attributeName, TreeMap<String, String> attribute) {
		this.attributeName = attributeName;
		this.valueArray = attribute;
	}
	
	public String toString() {
		String resultString = this.attributeName + " : [";
		for (String value : this.valueArray.values()) {
			resultString += value + ", ";
		}
		return resultString + "]";
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}
}
