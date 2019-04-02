package colors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class main {
	
	public main() {
		
	}
	
	/**
	 * Function that takes the entered overstrand list
	 * produces a hashmap of each crossing paired with the hashmap of strands at the crossing paired with their colors
	 * @param overstrand - list of integers
	 * @return - hashmap of hashmaps that gives the 3 strands and their colors paired with a crossing number
	 */
	public static HashMap<Integer, crossing > makeCrossings( int[] overstrand ) {
		// initialize the hashmap of hashmaps
		HashMap<Integer, crossing > crossings = new HashMap<Integer, crossing>();
		
		// for each number in the overstrand, put the number given in the list, its position, and its position + 1 as the strands
		// make this into a hashmap of the three strands paired with color "z" as a placeholder
		for ( int i = 0; i < (overstrand.length); i++ ) {
			HashMap<Integer, String> strands = new HashMap<Integer,String>();
			ArrayList<String> valueList = new ArrayList<String>();
			
			strands.put(i, "z");
			strands.put(overstrand[i], "z");
			strands.put(i+1, "z");
			
			crossing strandsCross = new crossing(strands, valueList);
			
			crossings.put(i, strandsCross);
		}
		
		return crossings;
	}

	
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		int[] k6_1 = new int[] { 2, 4, 0, 5, 1, 3 };
		
		// First, make the hashmap of crossings given the overstrand list for 6_1
		HashMap<Integer, crossing> crossings = makeCrossings(k6_1);
		
		// For each crossing, print out the crossing number, strand list, color list, and whether it is valid or complete
		/*for ( int i = 0; i < crossings.size(); i++ ) {
			System.out.println("Crossing number: "+i);
			System.out.println(crossings.get(i).strandList());
			System.out.println("Colors: " + crossings.get(i).valueList );
			System.out.println(crossings.get(i).isValid());
			System.out.println(crossings.get(i).isComplete());
		}*/
		
		// Right now, trying to figure out how to apply the changes given from the makeValid function 
		/*HashMap<Integer, String> changes = crossings.get(crossings.size()-1).make3Valid();
		ArrayList<Integer> changedStrands = new ArrayList<Integer>();
		changedStrands.addAll(changes.keySet());
		
		for (int c = 0; c < changes.size(); c++ ) {
			for ( int i = 0; i < crossings.size(); i++ ) {
				if ( crossings.get(i).strandsCross.containsKey(changedStrands.get(c))) {
					crossings.get(i).putStrand(changedStrands.get(c), changes.get(changedStrands.get(c)));
					crossings.get(i).updateValues();
				}
			}
		}*/
		
		for( int k = 0; k < crossings.size(); k++ ) {
			HashMap<Integer, String> changes = crossings.get(k).make3Valid();
			ArrayList<Integer> changedStrands = new ArrayList<Integer>();
			changedStrands.addAll(changes.keySet());
			
			for (int c = 0; c < changes.size(); c++ ) {
				for ( int i = 0; i < crossings.size(); i++ ) {
					if ( crossings.get(i).strandsCross.containsKey(changedStrands.get(c))) {
						crossings.get(i).putStrand(changedStrands.get(c), changes.get(changedStrands.get(c)));
						crossings.get(i).updateValues();
					}
				}
			}
			
		}
		
		for ( int i = 0; i < crossings.size(); i++ ) {
			System.out.println("Crossing number: "+i);
			System.out.println(crossings.get(i).strandList());
			System.out.println("Colors: " + crossings.get(i).valueList );
			System.out.println(crossings.get(i).isValid());
			System.out.println(crossings.get(i).isComplete());
		}
		
		
		
		
		
	}

}
