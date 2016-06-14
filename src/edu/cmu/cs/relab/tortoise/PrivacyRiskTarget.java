package edu.cmu.cs.relab.tortoise;

import java.util.ArrayList;

public class PrivacyRiskTarget {
	float intercept=(float) 4.69;
	ArrayList<InformationType> types = new ArrayList<InformationType>();
	ArrayList<DemographicFactor> factors = new ArrayList<DemographicFactor>();
	ArrayList<RiskLevel> risklevels = new ArrayList<RiskLevel>();
	ArrayList<PrivacyHarm> harms = new ArrayList<PrivacyHarm>();
	ArrayList<Interaction> interactions = new ArrayList<Interaction>();
	
	public PrivacyRiskTarget() {
		return;
	}
	
	public void add(InformationType type) {
		this.types.add(type);
	}
	
	public void add(DemographicFactor factor) {
		this.factors.add(factor);
	}
	
	public void add(PrivacyHarm harm) {
		this.harms.add(harm);
	}
	
	public void add(RiskLevel level) {
		this.risklevels.add(level);
	}
	
	public void add(Interaction i) {
		this.interactions.add(i);
	}
}
