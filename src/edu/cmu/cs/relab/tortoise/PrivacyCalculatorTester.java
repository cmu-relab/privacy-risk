/**
 * 
 */
package edu.cmu.cs.relab.tortoise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author jbhatia
 *
 */
public class PrivacyCalculatorTester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		PrivacyRiskTarget target=PrivacyRiskCalculator.setTable();
		ArrayList<PrivacyRiskScore> scores=PrivacyRiskCalculator.score(target);
		FileWriter out=new FileWriter(new File("data/scores.csv"));
		out.write("Information Type,Risk Level,Harm,Score\n");
		for(PrivacyRiskScore score:scores){
			out.write(score.infotype.name+","+score.risk.name+","+score.harm.name
					+","+score.interaction.toString()+","+score.score+"\n");
		}
		out.close();
		System.err.println("end");
		
	}

}
