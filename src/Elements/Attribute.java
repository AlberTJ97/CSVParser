package Elements;
import java.util.HashMap;

public class Attribute {
	private String attributeName;
	private HashMap<String, Double> valueArray;

	public Attribute(String attributeName) {
		this.attributeName = attributeName;
		this.valueArray = new HashMap<String, Double>();
	}
	
	public void addAttribute(String date, Double value) {
		this.valueArray.put(date, value);
	}
	
	public String toString() {
		for (String a : this.valueArray.keySet()) {
			System.out.println(a);
		}
		return "";
	}
}
