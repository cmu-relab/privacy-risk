package edu.cmu.cs.relab.tortoise.datautility;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void test() {
		UtilityAvgEstimator estimator = new UtilityAvgEstimator();
		String[] types = {"Network Information", "OS Type and Version", "Passwords", 
				"Keylogging Data"};
		
		UtilityTarget target = new UtilityTarget();
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			estimator.addSupportedType(type);
			target.add(type);
		}
		UtilityScore score = estimator.getScore(target);
		System.out.println("score: " + score.getScore());
		assertTrue(score.getScore() - 1.871900303 < 0.0000001);
	}

}
