package com.compute.crypto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CryptoImplTest {
	CryptoImpl crypto = null; 
	
	@Before
	public void setUp(){
		crypto = new CryptoImpl();
	}
	
	@Test
	public void encryptDecryptTest(){
		byte encByte[] = crypto.encrypt(10.0);
		String s = new String(encByte);
		byte []decryptedText = crypto.decrypt(s);
		double num = Double.parseDouble(new String(decryptedText));
		assertEquals(10.0, num,0.0);
	}
	
	@Test
	public void encryptActuallyEncryptsTest(){
		byte encByte[] = crypto.encrypt(10.0);
		String s = new String(encByte);
		assertNotEquals("10.0", s);
	}
}
