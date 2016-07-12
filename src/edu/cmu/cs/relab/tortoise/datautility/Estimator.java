package edu.cmu.cs.relab.tortoise.datautility;
import java.util.TreeSet;

/*
 * 
 * Provides basic features for incident data utility estimate functions.
 *  Each estimator that subclasses this class should call {@link addSupportedType} 
 *  to define what information types are supported.
 */
public abstract class Estimator {
	private TreeSet<String> types = new TreeSet<String>();
	
	/**
	 * Returns the score for the given {@link UtilityTarget} object. 
	 * The target contains a description of information types being shared.
	 * 
	 * @param target describes the information types to score
	 * @return the score assigned to the given target
	 */
	
	public abstract UtilityScore getScore(UtilityTarget target);
	
	/**
	 * Adds an information type to the list of supported information types.
	 * Only supported information types should expect to receive scores.
	 * 
	 * @param type the type to add to this calculator
	 */
	
	protected void addSupportedType(String type) {
		this.types.add(type);
	}
	
	/**
	 * Removes an information type from the list of supported types. Only
	 * supported information types should expect to receive scores.
	 * 
	 * @param type the type to add to this estimator
	 */
	
	protected void removeSupportedType(String type) {
		this.types.remove(type);
	}
	
	/**
	 * Returns the list of information types supported by this estimator.
	 * Only supported information types should expect to receive scores.
	 * 
	 * @return the list of supported information types
	 */
	
	public TreeSet<String> getSupportedTypes() {
		return new TreeSet<String>(types);
	}


}
