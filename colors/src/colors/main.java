package colors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class main {
	
	public main() {
		
	}
	
	public static HashMap<Integer, HashMap<Integer, String>> makeCrossings( int[] overstrand ) {
		HashMap<Integer, HashMap<Integer, String>> crossings = new HashMap<Integer, HashMap<Integer, String>>();
		for ( int i = 0; i < overstrand.length; i++ ) {
			HashMap<Integer, String> strandsCross = new HashMap<Integer, String>();
			strandsCross.put(i, "z");
			strandsCross.put(overstrand[i], "z");
			strandsCross.put(i+1, "z");
			crossings.put(i, strandsCross);
		}
		
		return crossings;
	}
	public static Boolean validCross( ArrayList<String> valueList ) {
		if( (valueList.get(0).equals(valueList.get(1))) && ( valueList.get(0).equals(valueList.get(2))) && ( valueList.get(1).equals(valueList.get(2)))) {
			return true;
		}
		else if((valueList.get(0).equals(valueList.get(1)) == false ) && ( valueList.get(0).equals(valueList.get(2))== false ) && ( valueList.get(1).equals(valueList.get(2)) == false ) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		int[] k6_1 = new int[] { 2, 4, 0, 5, 1, 3 };
		//for ( int i = 0; i < k6_1.length; i++ ) {
		//	System.out.println("On strand: "+i+"\nOverstrand: "+k6_1[i] );
		//}
		HashMap<Integer, HashMap<Integer, String>> crossings = makeCrossings(k6_1);
		for ( int i = 0; i < crossings.size(); i++ ) {
			ArrayList<String> valueList = new ArrayList<String>();
			valueList.addAll(crossings.get(i).values());
			System.out.println("Crossing number: "+i);
			System.out.println(crossings.get(i).keySet());
			System.out.println(validCross( valueList ));
		}
		
	}

}
