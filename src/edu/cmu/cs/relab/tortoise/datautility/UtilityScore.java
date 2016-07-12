package edu.cmu.cs.relab.tortoise.datautility;


/**
 * Describes a utility score for a collection of information types. The
 * information types are described in a {@link UtilityTarget} object.
 */
public class UtilityScore {
	
	public UtilityTarget target;
	public double score;
	
	/**
	 * Creates a new mapping between a utility score and scored target
	 * 
	 * @param target the scored target
	 * @param score the utility score
	 */
	public UtilityScore(UtilityTarget target, double score) {
		this.target = target;
		this.score = score;
	}
	
	
	/**
	 * Returns the scored target, including information types
	 * @return the scored target
	 */
	
	public UtilityTarget getTarget() {
		return target;
	}
	
	/**
	 * Returns the utility score
	 * @return the utility score
	 */
	public double getScore() {
		return score;
	}
	
}
