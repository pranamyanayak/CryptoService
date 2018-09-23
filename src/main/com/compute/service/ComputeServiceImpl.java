package com.compute.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compute.crypto.CryptoProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ComputeServiceImpl implements ComputeService{
	
	@Inject
	private CryptoProvider cryptoProvider;
	
	int totElements = 0;
	double average = 0; 
	double deviationSquared = 0;
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputeServiceImpl.class);
	
	@Override
	public double[] getAverageAndStandardDeviation(double num) {
		LOGGER.info("getAverageAndStandardDeviation called num");
		double []tmp = new double[2];
		
		synchronized(this){
			totElements += 1;
	        double avgDiff = (num - average) / totElements;
	        double newAvg = average + avgDiff;
	        double inc = (num - newAvg) * (num - average);
	        double newDeviationSquared = deviationSquared + inc;
	        average = newAvg;
	        deviationSquared = newDeviationSquared;
	        tmp[0] = average;tmp[1] = Math.sqrt(deviationSquared/(totElements));
		}
		return tmp;
	}
	
	

	@Override
	public double decrypt(String encryptedText) {
		// TODO Auto-generated method stub
		return cryptoProvider.decrypt(encryptedText);
	}

	@Override
	public String[] getEncryptedAverageAndStandardDeviation(double num) {
		double []avgStdDeviationRes = getAverageAndStandardDeviation(num);
		String encAverage = cryptoProvider.encrypt(avgStdDeviationRes[0]);
		String encStdDeviation = cryptoProvider.encrypt(avgStdDeviationRes[1]);
		String []res = {encAverage, encStdDeviation};
		return res;
	}

	

}
