package edu.cmu.cs.relab.tortoise.datautility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
	
	public static void main(String[] args) throws FileNotFoundException {
					
		ArrayList<Double[]> averages = new ArrayList<>();
		Double[] finalScores = new Double[26];

		File file = new File("confusion_data.csv"); 
		Scanner sc = new Scanner(file);
		String header = sc.nextLine();
		//correlate each of the 26 information types to its index in the file	
		String[] types = header.split(","); 
		while (sc.hasNextLine()) { //for each person
			Double[] avgs = new Double[26];
			
			String line = sc.nextLine();
			String[] scoresStr = line.split(",");
			Double[] scores = new Double[26];
		
			for (int i = 0; i < scoresStr.length; i++) {
				Double d;
				try {
				d = Double.parseDouble(scoresStr[i]);
				scores[i] = d;
				} catch(NumberFormatException e) {
				}			
			}
			
			Double[][] table = new Double[26][26];
			for(int i = 0; i < scores.length; i++) {
				Double iScore = scores[i];
				for(int j = 0; j < scores.length; j++) {
					Double jScore = scores[j];
					if (iScore < jScore) {
						table[i][j] = 0.0; //doesn't matter which right?
					} else if (jScore < iScore) {
						table[i][j] = 1.0;
					} else {
						table[i][j] = 0.5;
					}
				}
			}
					
			for(int i = 0; i < table.length; i++) { //for every row in this p's table
				Double[] row = table[i];
				Double avg = 0.0;
				for(int j = 0; j < row.length; j++) { //ie 26
					avg = avg + row[j];
				}
				avg = avg / row.length; 
				avgs[i] = avg;
			}
			averages.add(avgs); //add person's average for each type to list
		
		}
		//check 0 - 1
		//use all table averages for final score
		for(int i = 0; i < 26; i++) { //for each information type
			Double finalScore = 0.0;
			
			for (Double[] avgs : averages) {
				finalScore = finalScore + avgs[i];
			}
			finalScore = finalScore / 76.0;
			finalScores[i] = finalScore;
		}
		
		for (int i = 0; i < 26; i++) {
			System.out.println(types[i] + " has a score of " + finalScores[i]);
		} //and can print like a function too
	
	}

}
