package colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class crossing {
	// Each crossing object will have a hashmap containing the strand numbers and their colors 
	// and an arraylist of strings which is just the colors present at the crossing 
	public HashMap<Integer, String> strandsCross = new HashMap<Integer, String>();
	ArrayList<String> valueList = new ArrayList<String>();
	ArrayList<String> possibleColors = new ArrayList<String>();
	
	/**
	 * Constructor for the crossing object
	 * @param strandsCross - Hashmap containing strand numbers and their colors, must be provided at creation
	 * @param valueList - list of colors at the crossing, most likely will be all z (undecided) at creation
	 */
	public crossing( HashMap<Integer, String> strandsCross, ArrayList<String> valueList ) {
		this.strandsCross = strandsCross;
		this.valueList = valueList;
		this.valueList.addAll( this.strandsCross.values() );
		this.possibleColors = possibleColors;
		this.possibleColors.add("a");
		this.possibleColors.add("b");
		this.possibleColors.add("c");
		
		
	}
	
	/**
	 * Function to reassign a strand's color given the strand number and the new color
	 * @param i - strand number
	 * @param s - color variable - a, b, c, or z (undecided)
	 */
	public void putStrand( int i, String s ) {
		this.strandsCross.put(i, s);
	}
	
	/**
	 * Function that returns the list of strand numbers at the crossing 
	 * @return - just a list of strand numbers 
	 */
	public ArrayList<Integer> strandList() {
		ArrayList<Integer> strandList = new ArrayList<Integer>();
		strandList.addAll(this.strandsCross.keySet());
		return strandList;
	}
	
	/**
	 * Function that will change the appropriate strand's color if it is at this crossing
	 * @param i - strand number (may or may not be at this crossing)
	 * @param s - new color
	 */
	public void editCross( int i, String s ) {
		if ( this.strandsCross.containsKey(i) ) {
			strandsCross.put(i, s);
		}
	}
	
	public void updateValues() {
		this.valueList.clear();
		this.valueList.addAll( this.strandsCross.values() );
	}
	/**
	 * Function that tells if the crossing has been completely colored - there are no undecided "z" color strands
	 * Doesn't mean that the crossing is valid
	 * @return - true or false: true if all colors are decided, otherwise false
	 */
	public Boolean isComplete() {
		if( this.strandsCross.values().contains("z") ) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Function that tells if a crossing is valid 
	 * All colors are the same or all are different
	 * A crossing is still valid if it has "z" colored strands
	 * @return - true or false: true if it is a valid crossing, otherwise false
	 */
	public Boolean isValid() {
		
		if( ( this.valueList.get(0).equals(this.valueList.get(1))) && ( this.valueList.get(0).equals(this.valueList.get(2))) && ( this.valueList.get(1).equals( this.valueList.get(2)))) {
			return true;
		}
		else if(( this.valueList.get(0).equals(this.valueList.get(1)) == false ) && ( this.valueList.get(0).equals(this.valueList.get(2))== false ) && ( this.valueList.get(1).equals( this.valueList.get(2)) == false ) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns a list of strand numbers that have been unassigned at this crossing
	 * Strands that have color "z"
	 * @return - list of unassigned strand numbers
	 */
	public ArrayList<Integer> unassignedStrands() {
		ArrayList<Integer> unassigned = new ArrayList<Integer>();
		ArrayList<Integer> allStrands = this.strandList();
		
		for ( int i=0; i < allStrands.size(); i++ ) {
			if ( this.strandsCross.get(allStrands.get(i)).equals("z") ) {
				unassigned.add(allStrands.get(i));
			}
		}
		
		return unassigned;
		
	}
	
	/**
	 * Returns a list of assigned strand numbers at this crossing
	 * Strands with color a, b, or c
	 * @return - list of assigned strand numbers
	 */
	public HashMap<Integer, String> assignedStrands() {
		HashMap<Integer, String> assigned = new HashMap<Integer, String>();
		ArrayList<Integer> allStrands = this.strandList();
		
		for ( int i=0; i < allStrands.size(); i++ ) {
			if ( this.strandsCross.get(allStrands.get(i)).equals("z") == false ) {
				assigned.put(allStrands.get(i), this.strandsCross.get(allStrands.get(i)));
			}
		}
		
		return assigned;
	}
	
	public HashMap<Integer, String> make3Valid() {		
		for( int i=0; i < 3; i++ ) {
			if( this.possibleColors.contains(valueList.get(i))) {
				this.possibleColors.remove(this.possibleColors.indexOf(valueList.get(i)));
			}
		}
		
		int numberToAssign = this.unassignedStrands().size();
		
		
		HashMap<Integer, String> changes = new HashMap<Integer, String>();
		
		for( int i=0; i < numberToAssign; i++ ) {
			int unassigned = this.unassignedStrands().get(0);
			this.strandsCross.put( unassigned, this.possibleColors.get(i));
			changes.put(unassigned, this.possibleColors.get(i));
		}
		
		this.updateValues();
		//this.strandsCross.put(this.unassignedStrands().get(2), "c");
		
		//System.out.println( this.valueList );
		return changes;
	}
	
	public HashMap<Integer, String> make1Valid( String s ) {
		String newColor = s;
		HashMap<Integer, String> changes = new HashMap<Integer, String>();
		
		for( int i=0; i < 3; i++ ) {
			this.strandsCross.put(this.strandList().get(i), newColor);
		}
		
		this.updateValues();
		
		//System.out.println( this.valueList );
		return changes;
		
	}

}
