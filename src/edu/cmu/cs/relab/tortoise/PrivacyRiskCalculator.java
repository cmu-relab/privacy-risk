package edu.cmu.cs.relab.tortoise;

import java.util.TreeSet;

public abstract class PrivacyRiskCalculator {
	private TreeSet<String> types = new TreeSet<String>();
	
	public abstract PrivacyRiskScore score(PrivacyRiskTarget target);
	
	protected void addSupportedType(String type) {
		this.types.add(type);
	}
	
	protected void removeSupportedType(String type) {
		this.types.remove(type);
	}
	
	public TreeSet<String> getSupportedTypes() {
		return new TreeSet<String>(types);
	}
}
