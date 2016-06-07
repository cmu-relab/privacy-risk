package edu.cmu.cs.relab.tortoise;

/**
 * Describes an information type by category name.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */

public class InformationType implements Comparable<InformationType> {

	/**
	 * The category name of the information type
	 */
	public final String name;
	
	/**
	 * Creates a new information type with the given category name
	 * 
	 * @param name the name of the new information type
	 */
	public InformationType(String name) {
		this.name = name;
	}
	
	/**
	 * Compares this information type to the given type.
	 *
	 * @param t the information type to compare to
	 * @return the alphanumeric comparison of this category name to the given name
	 */
	public int compareTo(InformationType t) {
		return name.compareTo(t.name);
	}
	
	/**
	 * Compares this information type to the given type.
	 * 
	 * @param t the information type to compare to
	 * @return true, if the two types have the same category name, false otherwise
	 */
	
	public boolean equals(InformationType t) {
		return compareTo(t) == 0;
	}
	
	/**
	 * Returns the category name of this information type.
	 * 
	 * @return the name of this information type category 
	 */
	public String toString() {
		return name;
	}
}
