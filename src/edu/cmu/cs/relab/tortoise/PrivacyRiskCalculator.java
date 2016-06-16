package edu.cmu.cs.relab.tortoise;

import java.util.TreeSet;

/**
 * Provides basic features for privacy risk calculators. Each calculator that 
 * subclasses this class should call {@link addSupportedType} to define what
 * information types are supported. This call may be made while building the
 * internal data structure containing risk scores per type.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */

public abstract class PrivacyRiskCalculator {
	private TreeSet<String> types = new TreeSet<String>();
	
	/**
	 * Returns the maximum score for the given {@link PrivacyRiskTarget} object. 
	 * The target contains a description of information types that should be
	 * scored. Each information type provided should be capable of receiving a
	 * score, and this method returns the maximum score among all information
	 * types.
	 * 
	 * @param target describes the information types to score
	 * @return the risk score assigned to the given target
	 */
	
	public abstract PrivacyRiskScore getMaxScore(PrivacyRiskTarget target);
	
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
	 * @param type the type to add to this calculator
	 */
	
	protected void removeSupportedType(String type) {
		this.types.remove(type);
	}
	
	/**
	 * Returns the list of information types supported by this calculator.
	 * Only supported information types should expect to receive scores.
	 * 
	 * @return the list of supported information types
	 */
	
	public TreeSet<String> getSupportedTypes() {
		return new TreeSet<String>(types);
	}
}
