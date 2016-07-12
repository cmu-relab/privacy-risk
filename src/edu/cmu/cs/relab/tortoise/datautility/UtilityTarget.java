package edu.cmu.cs.relab.tortoise.datautility;
import java.util.ArrayList;

/*
 *  * Provides the information types for scoring.
 */
public class UtilityTarget {
	
	private ArrayList<String> types = new ArrayList<String>();
	
	/**
	 * Adds an information type to score.
	 * @param type the information type to score
	 */
	
	public void add(String type) {
		this.types.add(type);
	}
	
	/**
	 * Returns the information types to score.
	 * @return the information types to score
	 */
	
	public String[] getTypes() {
		return types.toArray(new String[types.size()]);
	}

}
