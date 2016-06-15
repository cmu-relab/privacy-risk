package edu.cmu.cs.relab.tortoise;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Computes the privacy risk score based on information types.
 * 
 * @author CMU RELAB
 *
 */

public class PrivacyRiskEstimateCalculator extends PrivacyRiskCalculator {
	private TreeMap<String,Double> harms = new TreeMap<String,Double>();
	private TreeMap<String,Double> risks = new TreeMap<String,Double>();
	private TreeMap<String,Double> types = new TreeMap<String,Double>();
	private TreeMap<String,TreeMap<String,TreeMap<String,Double>>> scores = new TreeMap<String,TreeMap<String,TreeMap<String,Double>>>();
	private double pvalue = 0.05;
	private String harm;
	
	public void setPValue(double pvalue) {
		this.pvalue = pvalue;
	}
	
	public void setPrivacyHarm(String harm) {
		if (!harms.containsKey(harm)) {
			return;
		}
		else {
			this.harm = harm;
		}
	}
	
	/*
	 * This function populates the Arraylists of the independent variables and the 
	 *  interaction between information types and risk levels
	 *  for the class PrivacyTarget, by reading the survey data estimates from two .csv file 
	 *  - one for the individual estimates and the other for interactions.
	 *  
	 *  Returns a PrivacyRiskTarget object with all the Arraylists set to their estimate values. 
	 * 
	*/
	public void readData(DataSource estimates, DataSource interactions) throws IOException {
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
			double estInteract=0.0;
			
			if(p > pvalue) {
				estInteract=0;
			}
			
			else{
				estInteract = Double.parseDouble(interactions.get(row, "estimate"));
			}
			
			// for p <= pvalue, create score for this interaction
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
	
	
	/*
	 * This function computes the WTS scores for all the combinations of harms, information types 
	 * and risk levels. The input file is a PrivacyRiskTarget object that contains all the values for the estimates. 
	 * It stores the WTS score for each combination as a PrivacyRiskScore object. 
	 * And returns an Arraylist with all the scores, 
	 * for all possible combinations of the independent variables. 
	 * 
	 */
	public PrivacyRiskScore score(PrivacyRiskTarget target) {
		// version 1.0: Computes score for all the combinations of the independent variables - harm, risk and infotype.
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
				// System.err.println("Error: No score for " + type + " at risk level=" + risk + " and harm=" + harm);
			}
		}

		PrivacyRiskScore score = new PrivacyRiskScore(target, risk, maxScore);
		
		// version 2.0: Compute score for all information types combinations by all factors; this supports redaction, and risk change due to append
		
		return score;
	}
}
