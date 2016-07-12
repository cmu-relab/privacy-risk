package edu.cmu.cs.relab.tortoise.privacyrisk;

import java.util.ArrayList;

/**
 * Provides the information types and risk level for scoring.
 * 
 * @author CMU RELAB
 * @version 1.0
 */

public class PrivacyRiskTarget {
	public final static String RISK_L1 = "family";
	public final static String RISK_L2 = "workplace";
	public final static String RISK_L3 = "city";
	public final static String RISK_L4 = "state";
	public final static String RISK_L5 = "country";
	
	private String likelihood;
	
	private ArrayList<String> types = new ArrayList<String>();
	
	/**
	 * Creates a new default privacy risk target with risk level {@link #RISK_L1}.
	 */
	
	public PrivacyRiskTarget() {
		this(RISK_L1);
	}
	
	/**
	 * Creates a new default privacy risk target with given risk level.
	 * @param likelihood the risk level for this target
	 */
	
	public PrivacyRiskTarget(String likelihood) {
		this.likelihood = likelihood;
	}
	
	/**
	 * Returns the risk likelihood or risk level.
	 * @return the risk level to score
	 */
	
	public String getLikelihood() {
		return likelihood;
	}
	
	/**
	 * Sets the risk likelihood to the given level.
	 * @param level the risk level to score
	 */
	
	public void setLikelihood(String level) {
		this.likelihood = level;
	}
	
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
