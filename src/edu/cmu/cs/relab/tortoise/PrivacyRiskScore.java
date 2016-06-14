package edu.cmu.cs.relab.tortoise;


/**
 * Describes a privacy risk score for a collection of information types.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */
public class PrivacyRiskScore {
	
	/**
	 * The list of scored information types and demographic factors
	 */
	public  PrivacyRiskTarget target;
	public  InformationType infotype;
	public  RiskLevel risk;
	public  PrivacyHarm harm;
	public  Interaction interaction;
	public double score;

	
	/**
	 * Creates a new mapping between a privacy risk score and scored information types
	 * @param types the scored information types
	 * @param score the privacy risk score
	 */
	public PrivacyRiskScore(PrivacyRiskTarget target, InformationType info, PrivacyHarm harm, RiskLevel risk, Interaction interaction, double score) {
		this.target = target;
		this.infotype=info;
		this.risk=risk;
		this.harm=harm;
		this.interaction=interaction;
		this.score = score;
	}
	
	/**
	 * Returns the scored information types
	 * @return the scored information types
	 */
	
	public PrivacyRiskTarget getTarget() {
		return target;
	}
	
	/**
	 * Returns the privacy risk score
	 * @return the privacy risk score
	 */
	public double getScore() {
		return score;
	}
}
