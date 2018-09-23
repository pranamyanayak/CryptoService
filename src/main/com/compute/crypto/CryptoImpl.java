package com.compute.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoImpl{
	Cipher ecnCipher = null;
	Cipher decCipher = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoImpl.class);
	public CryptoImpl(){
			try {
				Security.addProvider(new BouncyCastleProvider());
				ecnCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				byte[] keyBytes   = new byte[]{90,21,42,13,48,54,62,47,68,99,18,18,12,13,14,15};
				IvParameterSpec ivspec = new IvParameterSpec(keyBytes);
				String algorithm  = "AES";
				SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
				ecnCipher.init(Cipher.ENCRYPT_MODE, key,ivspec);
				decCipher.init(Cipher.DECRYPT_MODE, key,ivspec);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} 
	}
	
	byte[] encrypt(double num) {
		try{
			byte plainText[] =  Double.toString(num).getBytes("UTF-8");;
			byte[] cipherText = ecnCipher.doFinal(plainText);
			byte encodedCipherText[] = Base64.encode(cipherText);
			return encodedCipherText;
		}catch(Exception ex){
			throw new RuntimeCryptoException("Unable to encrypt data");
		}
	}

	
	byte[] decrypt(String encryptedText) {
		// TODO Auto-generated method stub
		try {
			byte []binaryCipherTextBytes = Base64.decode(encryptedText);
			byte []decryptedText = decCipher.doFinal(binaryCipherTextBytes);
			return decryptedText;
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeCryptoException("Unable to decrypt data");
		} 
	}

}
