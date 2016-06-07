package edu.cmu.cs.relab.tortoise;

import java.util.ArrayList;

public class PrivacyRiskTarget {
	ArrayList<InformationType> types = new ArrayList<InformationType>();
	ArrayList<DemographicFactor> factors = new ArrayList<DemographicFactor>();
	
	public PrivacyRiskTarget() {
		return;
	}
	
	public void add(InformationType type) {
		this.types.add(type);
	}
	
	public void add(DemographicFactor factor) {
		this.factors.add(factor);
	}
}
