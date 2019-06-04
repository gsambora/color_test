package colors;

import java.util.ArrayList;
import java.util.HashMap;

public class multcolorings {
	
	public HashMap<Integer, crossing> thisKnot = new HashMap<Integer, crossing>();
	int numberStrands;
	
	
		public multcolorings( HashMap<Integer, crossing> thisKnot, int numberStrands ) {
			this.thisKnot = thisKnot;
			this.numberStrands = numberStrands;
		}
		
	/**
		 * Check every possible coloring for the knot and return the valid ones.
		 * 
		 * @return - list of valid coloring assignments
		 */
		public ArrayList<HashMap<Integer, crossing>> returnMultColorings() {
			ArrayList<HashMap<Integer, crossing>> multcolors = new ArrayList<HashMap<Integer, crossing>>();
			for( int i=0; i < this.numberStrands; ) {
				
			}
			
			return multcolors;
			
		}

}
