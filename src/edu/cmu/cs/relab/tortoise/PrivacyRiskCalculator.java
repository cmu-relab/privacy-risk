package edu.cmu.cs.relab.tortoise;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Computes the privacy risk score based on information types.
 * 
 * @author CMU RELAB
 *
 */

public class PrivacyRiskCalculator {
	public final static int RISK_LEVEL_LO = 1;
	public final static int RISK_LEVEL_HI = 2;
	
	private int likelihood = RISK_LEVEL_LO;
	
	public int getLikelihood() {
		return likelihood;
	}
	
	public void setLikelihood(int level) {
		this.likelihood = level;
	}
	
	/*
	 * This function populates the Arraylists for the class PrivacyTarget, 
	 * by reading the survey data estimates from a csv file.
	 * 
	*/
	public static PrivacyRiskTarget setTable() throws IOException {
		
		PrivacyRiskTarget pt=new PrivacyRiskTarget();
		
		// read the table from a URL, could be a file
		CSVFile estimates=CSVFile.read(new File("data/surveydata.csv"));
		CSVFile interactions=CSVFile.read(new File("data/surveydata_interactions.csv"));
		
		//Intercept of the regression equation
		float intercept=Float.parseFloat(estimates.getField("estimate", 0));
		pt.intercept=intercept;
		
		//Risk levels and their estimates
		for(int row=1;row<6;row++){
			String risklevel=estimates.getField("level", row);
			float risklevelvalue=Float.parseFloat(estimates.getField("estimate", row));
			RiskLevel r=new RiskLevel(risklevel,risklevelvalue);
			pt.add(r);
		}
		
		//Harm levels and their estimates
		for(int row=6;row<9;row++){
			String harmlevel=estimates.getField("level", row);
			float harmlevelvalue=Float.parseFloat(estimates.getField("estimate", row));
			PrivacyHarm h=new PrivacyHarm(harmlevel,harmlevelvalue);
			pt.add(h);
		}
		
		//Information types and their estimates
		for(int row=9;row<21;row++){
			String infolevel=estimates.getField("level", row);
			float infolevelvalue=Float.parseFloat(estimates.getField("estimate", row));
			InformationType i=new InformationType(infolevel,infolevelvalue);
			pt.add(i);
		}
		
		for(PrivacyHarm r:pt.harms)
			System.err.println(r);
			
		//Populate the interactions, only if pvalue<0.05
		for(int row=0; row<interactions.size(); row++){
			String infotype=interactions.getField("infotype", row);
			String risk = interactions.getField("risklevel", row);
			float interactionvalue=Float.parseFloat(interactions.getField("estimate", row));
			float pvalue=Float.parseFloat(interactions.getField("pvalue", row));
			float interaction=0;
			
			InformationType info=new InformationType("empty",0);
			RiskLevel r=new RiskLevel("empty",0);
			
			if(pvalue<=0.05){
				for(InformationType i:pt.types){
					if(i.name.equals(infotype)){
						info=new InformationType(i.name,i.value);
					}		
				}
				
				for(RiskLevel risks:pt.risklevels){
					if(risks.name.equals(risk)){
						r=new RiskLevel(risks.name,risks.value);
					}		
				}
				interaction=interactionvalue;
			}
			
			else{
				interaction=0;
			}
				Interaction i=new Interaction(info,r,interaction);
				//System.err.println(i);
				pt.add(i);
		}	
		
		return pt;
	}
	
	public static ArrayList<PrivacyRiskScore> score(PrivacyRiskTarget target) {
		ArrayList<PrivacyRiskScore> scores = new ArrayList<PrivacyRiskScore>();
		
		// version 1.0: Compute score per information type by all factors
		for(RiskLevel risk:target.risklevels){
			for(PrivacyHarm harm:target.harms){
				for(InformationType info:target.types){
					
					//find the interaction value
					Interaction interaction=new Interaction(info,risk,0);
					for(Interaction i:target.interactions){
						if(i.info.name.equals(info.name)&&i.risk.name.equals(risk.name)){
							interaction=i;
							continue;
						}
					}
					//calculate score
					double s=target.intercept+risk.value+info.value+harm.value+interaction.value;
					PrivacyRiskScore sc=new PrivacyRiskScore(target,info,harm,risk,interaction,s);
					scores.add(sc);
				}
			}
		}
		
		// version 2.0: Compute score for all information types combinations by all factors; this supports redaction, and risk change due to append
		
		return scores;
	}
}
