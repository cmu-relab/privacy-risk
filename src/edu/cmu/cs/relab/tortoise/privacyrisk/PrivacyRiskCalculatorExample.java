/**
 * 
 */
package edu.cmu.cs.relab.tortoise.privacyrisk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Provides a runtime example of the privacy risk calculator.
 * 
 * @author CMU RELAB
 * @version 1.0
 */
public class PrivacyRiskCalculatorExample {

	public static void main(String[] args) throws IOException {

		// setup the privacy risk calculator based on estimates
		DataSource estimates = DataSource.read(new File("data/surveydata.csv"), ",");
		DataSource interactions = DataSource.read(new File("data/surveydata_interactions.csv"), ",");
		PrivacyRiskEstimateCalculator calcEst = new PrivacyRiskEstimateCalculator();
		calcEst.readData(estimates, interactions);
		
		// setup the privacy risk calculator based on response mean
		DataSource ratings = DataSource.read(new File("data/surveydata_ratings.csv"), ",");
		PrivacyRiskMeanCalculator calcAvg = new PrivacyRiskMeanCalculator();
		calcAvg.readData(ratings);
		
		// compute an example risk score for each risk level
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
		
		// write the risk scores per information type, harm and risk level to the output file
		for (String risk : risks) {
			for (String harm : harms) {
				for (String type : types) {
					PrivacyRiskTarget target = new PrivacyRiskTarget(risk);
					target.add(type);
					PrivacyRiskScore scoreEst = calcEst.getMaxScore(target);
					PrivacyRiskScore scoreAvg = calcAvg.getMaxScore(target);
					out.write(type + "," + risk + "," + harm + "," + scoreEst.score + "," + scoreAvg.score + "\n");
				}
			}
		}
		out.close();
	}
}
