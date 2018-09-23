package com.compute.crypto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.bouncycastle.crypto.RuntimeCryptoException;

public class CryptoProviderTest {
	CryptoProvider provider = new CryptoProvider();
	
	@Before
	public void setUp(){
	}

	@Test
	public void encryptReturnsStringTest(){
		assertTrue("encrypt is not returning an instance of"
				+ "string" , provider.encrypt(10) instanceof String);
	}
	
	@Test
	public void encryptKnowDataTest(){
		String enc = provider.encrypt(2.5);
		assertEquals("Original key is unable to encrypt the data Key has changed?", "UTn8GoIJ1A6ZafPLiAxJsA==", enc);
	}
	
	@Test(expected = RuntimeCryptoException.class)
	public void decryptDataNotEncryptedWithKeyTest(){
		String newData = "thisisrandomdata";
		provider.decrypt(newData);
		
		// This should throw Badpadding exception. If not thrown then something is wrong.
		// As the data was not encrypted using our crypto key		
	}
	
	@Test
	public void decryptKnowDataEncryptedithKeyTest(){
		String knownData = "UTn8GoIJ1A6ZafPLiAxJsA==";
		double num = provider.decrypt(knownData);
		assertEquals(2.5, num,0.00);
	}
}
