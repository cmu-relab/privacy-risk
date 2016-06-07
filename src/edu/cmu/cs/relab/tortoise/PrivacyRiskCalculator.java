package edu.cmu.cs.relab.tortoise;

import java.net.URL;
import java.util.ArrayList;

/**
 * Computes the privacy risk score based on information types.
 * 
 * @author CMU RELAB
 *
 */

public class PrivacyRiskCalculator {
	public final static int RISK_LEVEL_LO = 1;
	public final static int RISK_LEVEL_HI = 2;
	
	private int likelihood = RISK_LEVEL_LO;
	
	public int getLikelihood() {
		return likelihood;
	}
	
	public void setLikelihood(int level) {
		this.likelihood = level;
	}
	
	public void setTable(URL url) {
		// read the table from a URL, could be a file
	}
	
	public ArrayList<PrivacyRiskScore> privacyRiskScore(ArrayList<InformationType> types, ArrayList<DemographicFactor> factors) {
		ArrayList<PrivacyRiskScore> scores = new ArrayList<PrivacyRiskScore>();
		
		// version 1.0: Compute score per information type by all factors
		
		// version 2.0: Compute score for all information types combinations by all factors; this supports redaction, and risk change due to append
		
		return scores;
	}
}
