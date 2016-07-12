/**
 * Provides classes for data utility calculator.
 * 
 * The data utility score is calculated using a {@link Estimator}
 * class. To obtain a {@link UtilityScore}, one should pass as input a {@link UtilityTarget} 
 * object to the method {@link UtilityAvgEstimator#getScore(UtilityTarget)}.
 * 
 * <p>The {@link UtilityTarget} object is constructed from a List of information types.
 * 
 * <p>Information types have unique names that can be added using the method
 * {@link UtilityTarget#add(String)}. The target object may have more than
 * one information type. To identify the list of supported information types
 * for a data utility calculator, call the method {@link Estimator#getSupportedTypes()}.
 * 
 * <p>The calculated result is returned as a {@link UtilityScore} object that
 * contains the score and a reference to the given {@link UtilityTarget} object.
 */
package edu.cmu.cs.relab.tortoise.datautility;