package edu.cmu.cs.relab.tortoise;


/**
 * Describes a privacy risk score for a collection of information types. The
 * information types are described in a {@link PrivacyRiskTarget} object,
 * which includes the risk level used to determine the score.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */
public class PrivacyRiskScore {
	
	/**
	 * The target, including information types and risk level, that was scored.
	 */
	public PrivacyRiskTarget target;
	public double score;

	
	/**
	 * Creates a new mapping between a privacy risk score and scored target
	 * 
	 * @param target the scored target
	 * @param score the privacy risk score
	 */
	public PrivacyRiskScore(PrivacyRiskTarget target, double score) {
		this.target = target;
		this.score = score;
	}
	
	/**
	 * Returns the scored target, including information types and risk level
	 * @return the scored target
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
