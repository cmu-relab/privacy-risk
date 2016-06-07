package edu.cmu.cs.relab.tortoise;

public class DemographicFactor {
	public final String name;
	public final String value;
	
	public DemographicFactor(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String toString() {
		return name + "=" + value;
	}
}
