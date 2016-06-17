
/**
 * Provides classes for privacy risk calculator.
 * 
 * The privacy risk score is calculated using a {@link PrivacyRiskCalculator}
 * class. To obtain a {@link PrivacyRiskScore}, one should pass as input a PrivacyRiskTarget 
 * object to the method {@link PrivacyRiskCalculator#getMaxScore(PrivacyRiskTarget)}.
 * 
 * <p>The {@link PrivacyRiskTarget} object is constructed from a risk level and 
 * one or more information types. The risk levels range from {@link PrivacyRiskTarget#RISK_L1},
 * which is the highest risk, to {@link PrivacyRiskTarget#RISK_L5}, which is
 * the lowest risk. The default setting is RISK_L1, if the target object is 
 * constructed using the default constructor.
 * 
 * <p>The risk level may be set using a constructor or by calling the method
 * {@link PrivacyRiskTarget#setLikelihood(String)}.
 * 
 * <p>Information types have unique names that can be added using the method
 * {@link PrivacyRiskTarget#add(String)}. The target object may have more than
 * one information type. To identify the list of supported information types
 * for a privacy risk calculator, call the method {@link PrivacyRiskCalculator#getSupportedTypes()}.
 * 
 * <p>The calculated result is returned as a {@link PrivacyRiskScore} object that
 * contains the score and a reference to the given {@link PrivacyRiskTarget} object.
 */

package edu.cmu.cs.relab.tortoise;