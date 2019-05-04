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

	public static void updateColoring( HashMap<Integer, crossing> crossings, ArrayList<Integer> changedStrands, HashMap<Integer, String> changes ) {
		for (int c = 0; c < changes.size(); c++ ) {
			for ( int i = 0; i < crossings.size(); i++ ) {
				if ( crossings.get(i).strandsCross.containsKey(changedStrands.get(c))) {
					crossings.get(i).putStrand(changedStrands.get(c), changes.get(changedStrands.get(c)));
					crossings.get(i).updateValues();
				}
			}
		}
	}
	
	public static Boolean doneColoring( HashMap<Integer, crossing> crossings ) {
		for ( int i = 0; i < crossings.size(); i++ ) {
			if ( crossings.get(i).isValid() && crossings.get(i).isComplete() ) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public static Boolean turnAround( HashMap<Integer, crossing> crossings ) {
		for ( int m = 0; m < crossings.size(); m++ ) {
			if( (crossings.get(m).isValid() != true) && (crossings.get(m).isComplete() == true) ) {
				System.out.println("Crossing " + m + " is fucked up");
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}
	
	public static Boolean fixthatbitch( HashMap<Integer, crossing> crossings, int i ) {
		System.out.println("Working on crossing number: " + i);
		
		if( doneColoring(crossings) ) {
			System.out.println("We're done!");
			return true;
		}
		
		System.out.println("\nSTARTING CONDITION:");
		for ( int m = 0; m < crossings.size(); m++ ) {
			System.out.println("Crossing number: "+m);
			System.out.println(crossings.get(m).strandList());
			System.out.println("Colors: " + crossings.get(m).valueList );
		}
		
		HashMap<Integer, crossing> undo = new HashMap<Integer, crossing>();
		
		undo.putAll(crossings);		
		
		HashMap<Integer, String> changes = crossings.get(i).make3Valid();
		ArrayList<Integer> changedStrands = new ArrayList<Integer>();
		changedStrands.addAll(changes.keySet());
		updateColoring( crossings, changedStrands, changes );
		
		if( turnAround(crossings) == true ) {
			HashMap<Integer, String> changes1 = crossings.get(i).make1Valid("a");
			ArrayList<Integer> changedStrands1 = new ArrayList<Integer>();
			changedStrands.addAll(changes.keySet());
			
			updateColoring( crossings, changedStrands1, changes1 );
			
			if( turnAround(crossings) == true ) {
				HashMap<Integer, String> changes2 = crossings.get(i).make1Valid("b");
				ArrayList<Integer> changedStrands2 = new ArrayList<Integer>();
				changedStrands.addAll(changes.keySet());
				
				updateColoring( crossings, changedStrands2, changes2 );
				
				if( turnAround(crossings) == true ) {
					HashMap<Integer, String> changes3 = crossings.get(i).make3Valid();
					ArrayList<Integer> changedStrands3 = new ArrayList<Integer>();
					changedStrands.addAll(changes.keySet());
					
					updateColoring( crossings, changedStrands3, changes3 );
					
					if( turnAround(crossings) == true ) {
						System.out.println("we are fucked");
						return false;
					} 
					
					else {
						fixthatbitch(crossings, i+1);
					}	
					
				} 
				
				else {
					fixthatbitch(crossings, i+1);
				}
				
			} 
			
			else {
				fixthatbitch(crossings, i+1);
			}
		} 
		
		else {
			fixthatbitch(crossings, i+1);
		}
		
		return false;
	}
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		int[] k6_1 = new int[] { 2, 4, 0, 5, 1, 3 };
		int[] k7_7 = new int[] { 4, 3, 7, 5, 0, 1, 2 };

		// First, make the hashmap of crossings given the overstrand list for 6_1
		HashMap<Integer, crossing> crossings = makeCrossings(k7_7);
		
		fixthatbitch( crossings, 0 );
		
		/* for( int k = 0; k < crossings.size(); k++ ) {
			HashMap<Integer, String> changes = crossings.get(k).make1Valid("a");
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
			
		}*/
		
		for ( int i = 0; i < crossings.size(); i++ ) {
			System.out.println("Crossing number: "+i);
			System.out.println(crossings.get(i).strandList());
			System.out.println("Colors: " + crossings.get(i).valueList );
			System.out.println(crossings.get(i).isValid());
			System.out.println(crossings.get(i).isComplete());
		}
		
		
		
		
		
	}

}
