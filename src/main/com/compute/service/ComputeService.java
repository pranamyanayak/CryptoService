package com.compute.service;

public interface ComputeService {	
	public double decrypt(String encryptedText);
	public double[] getAverageAndStandardDeviation(double num);
	
	public String[] getEncryptedAverageAndStandardDeviation(double num);
	
}
