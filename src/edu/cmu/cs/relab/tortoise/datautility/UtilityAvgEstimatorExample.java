package edu.cmu.cs.relab.tortoise.datautility;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 *  * Provides a runtime example of the utility estimator.
 */
public class UtilityAvgEstimatorExample {
	
	public static void main(String[] args) throws IOException {
		Estimator estimator = new UtilityAvgEstimator();
		String[] types = {"Packet Data", "Video / Image Files", "Chat History"};
		FileWriter out = new FileWriter(new File("/Users/liorafriedberg/Desktop/score.csv"));
		out.write("TYPES: ");
		
		UtilityTarget target = new UtilityTarget();
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			estimator.addSupportedType(type);
			target.add(type);
			out.write(type + ", ");
		}
		UtilityScore score = estimator.getScore(target);
		out.write("SCORE: " + score.getScore());
		out.close();
	}

}
