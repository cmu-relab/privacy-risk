package edu.cmu.cs.relab.tortoise;

import java.util.ArrayList;

public class PrivacyRiskTarget {
	public final static String RISK_L1 = "family";
	public final static String RISK_L2 = "workplace";
	public final static String RISK_L3 = "city";
	public final static String RISK_L4 = "state";
	public final static String RISK_L5 = "country";
	
	private String likelihood = RISK_L1;
	
	private ArrayList<String> types = new ArrayList<String>();

	public PrivacyRiskTarget() {
		return;
	}
	
	public String getLikelihood() {
		return likelihood;
	}
	
	public void setLikelihood(String level) {
		this.likelihood = level;
	}
	
	public void add(String type) {
		this.types.add(type);
	}
	
	public String[] getTypes() {
		return types.toArray(new String[types.size()]);
	}
}
