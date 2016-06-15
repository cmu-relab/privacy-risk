/**
 * 
 */
package edu.cmu.cs.relab.tortoise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @author jbhatia
 *
 */
public class PrivacyRiskEstimateCalculatorTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		// setup the privacy risk calculator based on estimates
		DataSource estimates = DataSource.read(new File("data/surveydata.csv"), ",");
		DataSource interactions = DataSource.read(new File("data/surveydata_interactions.csv"), ",");
		PrivacyRiskEstimateCalculator calcEst = new PrivacyRiskEstimateCalculator();
		calcEst.setPValue(Double.MAX_VALUE);
		calcEst.readData(estimates, interactions);
		
		// setup the privacy risk calculator based on mean ratings
		DataSource ratings = DataSource.read(new File("data/surveydata_ratings.csv"), ",");
		PrivacyRiskMeanCalculator calcAvg = new PrivacyRiskMeanCalculator();
		calcAvg.readData(ratings);
		
		// compute an example risk scores for each risk level
		String[] risks = { 
				PrivacyRiskTarget.RISK_L1, 
				PrivacyRiskTarget.RISK_L2, 
				PrivacyRiskTarget.RISK_L3, 
				PrivacyRiskTarget.RISK_L4, 
				PrivacyRiskTarget.RISK_L5 };
		String[] harms = {
				"Surveillance",
				"Insecurity",
				"InducedDisclosure"
		};
		TreeSet<String> types = calcEst.getSupportedTypes();

		// setup the output file
		FileWriter out = new FileWriter(new File("data/scores.csv"));
		out.write("InformationType,RiskLevel,Harm,Predicted,Mean\n");
		
		for (String risk : risks) {
			for (String harm : harms) {
				for (String type : types) {
					PrivacyRiskTarget target = new PrivacyRiskTarget();
					target.add(type);
					target.setLikelihood(risk);
					PrivacyRiskScore scoreEst = calcEst.score(target);
					PrivacyRiskScore scoreAvg = calcAvg.score(target);
					out.write(type + "," + risk + "," + harm + "," + scoreEst.score + "," + scoreAvg.score + "\n");
				}
			}
		}
		out.close();
	}
}
