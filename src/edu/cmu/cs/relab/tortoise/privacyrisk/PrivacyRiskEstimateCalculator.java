package edu.cmu.cs.relab.tortoise.privacyrisk;

import java.util.TreeMap;

/**
 * Computes the privacy risk score based on a regression model with multiple 
 * parameters. The current parameters include privacy harm type, risk 
 * likelihood, and information type. The algorithm adjusts scores to include
 * only factors that are statistically significant with a default p value
 * less than 0.05.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */

public class PrivacyRiskEstimateCalculator extends PrivacyRiskCalculator {
	private TreeMap<String,Double> harms = new TreeMap<String,Double>();
	private TreeMap<String,Double> risks = new TreeMap<String,Double>();
	private TreeMap<String,Double> types = new TreeMap<String,Double>();
	private TreeMap<String,TreeMap<String,TreeMap<String,Double>>> scores = new TreeMap<String,TreeMap<String,TreeMap<String,Double>>>();
	private double pvalue = 0.05;
	private String harm;
	
	/**
	 * Sets the p-value for whether to accept or reject interactions among factors.
	 * 
	 * @param pvalue the minimum, inclusive p-value
	 */
	
	public void setPValue(double pvalue) {
		this.pvalue = pvalue;
	}
	
	/**
	 * Sets the default privacy harm to be used when scoring information types.
	 * 
	 * @param harm the privacy harm
	 */
	
	public void setPrivacyHarm(String harm) {
		if (!harms.containsKey(harm)) {
			return;
		}
		else {
			this.harm = harm;
		}
	}
	
	/**
	 * Reads the estimates and interactions from a data source. The estimates
	 * measure the relative weight of each factor, and the interactions measure
	 * the weight of interactions among factors.
	 * 
	 * @param estimates the individual factor estimates
	 * @param interactions the estimates among factors
	 */
	public void readData(DataSource estimates, DataSource interactions) {
		// Read intercept of the regression equation
		double intercept = Double.parseDouble(estimates.get(0, "estimate"));
		
		// Read risk levels and their estimates
		for(int row = 1; row < 6; row++){
			String risk = estimates.get(row, "level");
			double value = Double.parseDouble(estimates.get(row, "estimate"));
			risks.put(risk, value);
			scores.put(risk, new TreeMap<String,TreeMap<String,Double>>());
		}
		
		// Read harm levels and their estimates, choose max harm as default harm
		String maxHarm = "";
		double maxHarmValue = 0;
		for(int row = 6; row < 9;row++){
			String harm = estimates.get(row, "level");
			double value = Double.parseDouble(estimates.get(row, "estimate"));
			harms.put(harm, value);
			if (value >= maxHarmValue) {
				maxHarmValue = value;
				maxHarm = harm;
			}
			for (String level : scores.keySet()) {
				scores.get(level).put(harm, new TreeMap<String,Double>());
			}
		}
		this.harm = maxHarm;
		
		// Read information types and their estimates
		for(int row = 9; row < 21;row++){
			String type = estimates.get(row, "level");
			double value = Double.parseDouble(estimates.get(row, "estimate"));
			types.put(type, value);
			addSupportedType(type);
		}
			
		// Populate the interactions, only if p <= 0.05
		for(int row = 0; row < interactions.size(); row++){
			double p = Double.parseDouble(interactions.get(row, "pvalue"));
			double estInteract = 0.0;
			
			if(p > pvalue) {
				estInteract = 0.0;
			}
			
			else{
				estInteract = Double.parseDouble(interactions.get(row, "estimate"));
			}
			
			// create a score for info type and risk level
			String type = interactions.get(row, "infotype");
			String risk = interactions.get(row, "risklevel");
			
			// read and lookup relevant estimates
			double estType = types.get(type);
			double estRisk = risks.get(risk);
			
			for (String harm : harms.keySet()) {
				double estHarm = harms.get(harm);
				double score = intercept + estRisk + estType + estHarm + estInteract;
				scores.get(risk).get(harm).put(type, score);
			}
		}
	}
	
	/**
	 * Returns the maximum willingness to share estimate based on a linear equation.
	 * @return the maximum willingness to share estimate.
	 */
	
	public PrivacyRiskScore getMaxScore(PrivacyRiskTarget target) {
		String risk = target.getLikelihood();
		double maxScore = 0;
		
		for (String type : target.getTypes()) {
			try {
				double value = scores.get(risk).get(harm).get(type);
				if (value > maxScore) {
					maxScore = value;
				}
			}
			catch (NullPointerException e) {
				System.err.println("Error: No score for " + type + " at risk level=" + risk + " and harm=" + harm);
			}
		}

		PrivacyRiskScore score = new PrivacyRiskScore(target, maxScore);
		return score;
	}
}
