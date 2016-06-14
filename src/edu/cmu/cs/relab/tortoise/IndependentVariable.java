package edu.cmu.cs.relab.tortoise;

public class IndependentVariable implements Comparable<IndependentVariable>{
	public final String name;
	public final float value;
	
	public IndependentVariable(String name, float value) {
		this.name = name;
		this.value = value;
	}
	
	public boolean equals(IndependentVariable t) {
		return compareTo(t) == 0;
	}
	
	public String toString() {
		return name + "=" + value;
	}

	public int compareTo(IndependentVariable t) {
		return name.compareTo(t.name);
	}
}
