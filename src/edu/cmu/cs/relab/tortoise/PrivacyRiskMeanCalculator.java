package edu.cmu.cs.relab.tortoise;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Computes the privacy risk score based on response averages.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */

public class PrivacyRiskMeanCalculator extends PrivacyRiskCalculator {
	private TreeMap<String,TreeMap<String,Double>> scores = new TreeMap<String,TreeMap<String,Double>>();
	
	/**
	 * Reads raw survey data and computes the mean privacy risk scores. The
	 * survey data appears in a comma seperated values (csv) file with a
	 * header row, and at least three column headers: risk, infotype and
	 * willingness-, which is the willingness to share response value
	 * ordered from 1 to 8 where 1 is extremely willing, and 8 is extremely
	 * unwilling.
	 * 
	 * The risk levels should be written as: "only 1 person in your X", where
	 * X is equal to one of family, workplace, city, state or country.
	 * 
	 * @param source the raw survey data
	 */
	
	public void readData(DataSource source) {
		TreeMap<String,String> riskMap = new TreeMap<String,String>();
		riskMap.put("only 1 person in your family", PrivacyRiskTarget.RISK_L1);
		riskMap.put("only 1 person in your workplace", PrivacyRiskTarget.RISK_L2);
		riskMap.put("only 1 person in your city", PrivacyRiskTarget.RISK_L3);
		riskMap.put("only 1 person in your state", PrivacyRiskTarget.RISK_L4);
		riskMap.put("only 1 person in your country", PrivacyRiskTarget.RISK_L5);
		

		// prepare the maps from risk to types to values
		TreeMap<String,TreeMap<String,ArrayList<Integer>>> values = new TreeMap<String,TreeMap<String,ArrayList<Integer>>>();
		for (String risk : riskMap.values()) {
			scores.put(risk, new TreeMap<String,Double>());
			values.put(risk, new TreeMap<String,ArrayList<Integer>>());
		}
		
		// tally up the values from the data source
		for (int i = 0; i < source.size(); i++) {
			String risk = riskMap.get(source.get(i, "risk"));
			String type = source.get(i, "infotype");
			
			ArrayList<Integer> list = values.get(risk).get(type);
			if (list == null) {
				list = new ArrayList<Integer>();
				values.get(risk).put(type, list);
			}
			list.add(Integer.parseInt(source.get(i, "willingness-")));
		}
		
		// compute the means for each information type
		for (String risk : values.keySet()) {
			for (String type : values.get(risk).keySet()) {
				double score = 0;
				for (Integer value : values.get(risk).get(type)) {
					score += value;
				}
				score = score / values.get(risk).get(type).size();
				scores.get(risk).put(type, score);
			}
		}
	}
	
	/**
	 * Returns the maximum mean willingness to share across all respondents.
	 * @return the mean privacy risk score
	 */
	
	public PrivacyRiskScore getMaxScore(PrivacyRiskTarget target) {
		String risk = target.getLikelihood();
		double maxScore = 0;
		
		for (String type : target.getTypes()) {
			double score = scores.get(risk).get(type);
			if (score > maxScore) {
				maxScore = score;
			}
		}
		return new PrivacyRiskScore(target, maxScore);
	}
}
