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
	 * Function that updates the changed strands in different crossings. 
	 * If we assign strands 1, 2, and 4 in crossing 0, 
	 * then let's go through the other crossings and update our colors for strands 1, 2, and 4.
	 * 
	 * @param crossings - our main hashmap with crossing info
	 * @param changedStrands - the strand numbers that need to be updated
	 * @param changes - a hashmap containing strand numbers and thecolors they were changed to
	 */
	public static void updateColoring( HashMap<Integer, crossing> crossings, ArrayList<Integer> changedStrands, HashMap<Integer, String> changes ) {
		for (int c = 0; c < changes.size(); c++ ) {
			for ( int i = 0; i < crossings.size(); i++ ) {
				if ( crossings.get(i).strandsCross.containsKey(changedStrands.get(c))) {
					crossings.get(i).putStrand(changedStrands.get(c), changes.get(changedStrands.get(c)));
					crossings.get(i).updateValues();
				}
			}}
		}
	
	
	/**
	 * Checks if all of the crossings are valid and completely colored. 
	 * 
	 * @param crossings - main hashmap
	 * @return - true if everything is colored and valid, false otherwise.
	 */
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
	
	
	/**
	 * Checks if we need to try a different coloring. 
	 * 
	 * @param crossings - main hashmap
	 * @return - true if we need to try something else immediately, false if everything is okay to proceed.
	 */
	public static Boolean turnAround( HashMap<Integer, crossing> crossings ) {
		for ( int m = 0; m < crossings.size(); m++ ) {
			if( (crossings.get(m).isValid() != true) && (crossings.get(m).isComplete() == true) ) {
				System.out.println("Crossing " + m + " is invalid");
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}
	
	/**
	 * This is the function that does all of the work! It works recursively to color the complete knot.
	 * 
	 * @param crossings - main hashmap
	 * @param i - the crossing number we are working on. 
	 * @return - true if we are done, false if something is wrong and we need to turn around. 
	 */
	public static Boolean colorKnot( HashMap<Integer, crossing> crossings, int i ) {
		System.out.println("Working on crossing number: " + i);
		
		// If we're done, stop everything! We colored the knot. 
		if( doneColoring(crossings) ) {
			System.out.println("We're done!");
			return true;
		}
		
		// Let's try making the strands three different colors first. 
		HashMap<Integer, String> changes = crossings.get(i).make3Valid();
		ArrayList<Integer> changedStrands = new ArrayList<Integer>();
		changedStrands.addAll(changes.keySet());
		
		updateColoring( crossings, changedStrands, changes );
		
		System.out.println("we made crossing " + i + " 3 different colors!");
		
		// Making this crossing three different colors didn't work, so let's try making everything color "a". 
		if( turnAround(crossings) == true ) {
			
			HashMap<Integer, String> changes1 = crossings.get(i).make1Valid("a");
			ArrayList<Integer> changedStrands1 = new ArrayList<Integer>();
			changedStrands1.addAll(changes1.keySet());
			
			updateColoring( crossings, changedStrands1, changes1 );
			
			System.out.println("we made crossing " + i + " 1 color ---- a!");
			
			// Making everything "a" didn't work, let's try "b".
			if( turnAround(crossings) == true ) {
				System.out.println("something is wrong!");
				HashMap<Integer, String> changes2 = crossings.get(i).make1Valid("b");
				ArrayList<Integer> changedStrands2 = new ArrayList<Integer>();
				changedStrands2.addAll(changes2.keySet());
				
				updateColoring( crossings, changedStrands2, changes2 );
				System.out.println("we made crossing " + i + " 1 color ---- b!");
				
				// Making everything "b" didn't work, let's try "c". This is the last thing we can try. 
				if( turnAround(crossings) == true ) {
					System.out.println("something is wrong!");
					HashMap<Integer, String> changes3 = crossings.get(i).make1Valid("c");
					ArrayList<Integer> changedStrands3 = new ArrayList<Integer>();
					changedStrands3.addAll(changes3.keySet());
					
					updateColoring( crossings, changedStrands3, changes3 );
					System.out.println("we made crossing " + i + " 1 color ---- c!");
					
					// Making everything "c" has to work, so let's continue to the next crossing.
					colorKnot( crossings, i + 1);
					
				} 
				
				// Making everything "b" worked, so let's go to the next crossing.
				else { colorKnot( crossings, i + 1); }
			}
			// Making everything "a" worked, so let's go to the next crossing.
			else { colorKnot( crossings, i + 1);; }
		} 
		// Wow this worked on the first try! Making the crossing three different colors worked, so let's move on. 
		else { colorKnot( crossings, i + 1);; }
		
		
		// This is just here to make sure we return something. We'll never reach this point unless we're done with the coloring.
		return true;
	}
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Overstrand lists for different knots can be put in here! 
		// Do NOT put the extra loop in. If there are 7 crossings, put 7 numbers. 
		int[] k6_1 = new int[] { 2, 4, 0, 5, 1, 3 };
		int[] k7_7 = new int[] { 4, 3, 7, 5, 0, 1, 2 };

		// First, make the hashmap of crossings given the overstrand list for a knot.
		HashMap<Integer, crossing> crossings = makeCrossings(k7_7);
		
		
		// Make the coloring!
		colorKnot( crossings, 0 );
		
		// Show us the results.
		for ( int i = 0; i < crossings.size(); i++ ) {
			System.out.println("Crossing number: "+i);
			System.out.println(crossings.get(i).strandList());
			System.out.println("Colors: " + crossings.get(i).valueList );
			System.out.println(crossings.get(i).isValid());
			System.out.println(crossings.get(i).isComplete());
		}
		
		
		
		
		
	}

}
