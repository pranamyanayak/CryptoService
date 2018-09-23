package com.compute.crypto;

import com.google.inject.Singleton;

@Singleton
public class CryptoProvider {
	 
	CryptoImpl crypto = new CryptoImpl();  
	
    
    public String encrypt(double num){
    	byte []cipherBytes = crypto.encrypt(num);
    	return new String(cipherBytes);
    }
    
    public double decrypt(String encryptedText){
    	byte []plainText = crypto.decrypt(encryptedText);
    	String s = new String(plainText);
    	double d = Double.parseDouble(s);
    	return d;
    }
}
