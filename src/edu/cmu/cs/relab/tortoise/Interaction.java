package edu.cmu.cs.relab.tortoise;

public class Interaction {
	public final InformationType info;
	public final RiskLevel risk;
	public final float value;
	
	public Interaction(InformationType info,RiskLevel risk, float value) {
		this.info=info;
		this.risk=risk;
		this.value = value;
	}
	public String toString() {
		return info.name+","+risk.name + "=" + value;
	}
}
