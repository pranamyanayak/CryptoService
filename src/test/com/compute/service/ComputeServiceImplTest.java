package com.compute.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Before;

public class ComputeServiceImplTest {
	
	ComputeServiceImpl cryptoImpl;
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputeServiceImplTest.class);
	
	@Before
	public void setUp(){
		cryptoImpl = new ComputeServiceImpl();
	}
	
	@Test
	public void getAverageAndStandardDeviationTest(){
		double []num = cryptoImpl.getAverageAndStandardDeviation(1);
		assertEquals(1, num[0],0.0);
		num = cryptoImpl.getAverageAndStandardDeviation(4);
		assertEquals(2.5, num[0],0.0);assertEquals(1.5, num[1],0.001);
		num = cryptoImpl.getAverageAndStandardDeviation(4);
		assertEquals(3.0, num[0],0.0);assertEquals(1.414, num[1],0.001);
		
		num = cryptoImpl.getAverageAndStandardDeviation(11);
		assertEquals(5.0, num[0],0.0);assertEquals(3.674, num[1],0.001);	
	}
	
	@Test
	public void getAverageAndStandardDeviationForNegativesTest(){
		double []num = cryptoImpl.getAverageAndStandardDeviation(-1);
		num = cryptoImpl.getAverageAndStandardDeviation(-2);
		assertEquals(-1.5, num[0],0.0);assertEquals(0.5, num[1],0.001);
	}
	
	@Test
	public void getAverageAndStandardDeviationForDecimalsTest(){
		double []num = cryptoImpl.getAverageAndStandardDeviation(0.1);
		num = cryptoImpl.getAverageAndStandardDeviation(0.5);
		assertEquals(0.3, num[0],0.00001);assertEquals(0.2, num[1],0.001);
	}
	
	
	/**
	 * Tests the standardDeviation and encrypt function with concurrency.
	 * @throws InterruptedException
	 * @throws BrokenBarrierException
	 */
	@Test
	public void getAverageAndStandardDeviationParallelTest() throws InterruptedException, BrokenBarrierException{
		CyclicBarrier gate = new CyclicBarrier(5);
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					gate.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				cryptoImpl.getAverageAndStandardDeviation(11);
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					gate.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				cryptoImpl.getAverageAndStandardDeviation(21);
			}
		};
		Thread t3 = new Thread() {
			@Override
			public void run() {
				try {
					gate.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				cryptoImpl.getAverageAndStandardDeviation(10);
			}
		};
		Thread t4 = new Thread() {
			@Override
			public void run() {
				try {
					gate.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				cryptoImpl.getAverageAndStandardDeviation(42);
			}
		};
		
		t1.start();t2.start();t3.start();t4.start();
		
		gate.await();
		t1.join();t2.join();t3.join();t4.join();
		
		double res[] = cryptoImpl.getAverageAndStandardDeviation(53);
		assertEquals(27.4,res[0],0.0); // Assert average
		assertEquals(17.2116,res[1],0.001); // Assert standard deviation
	}
	
}
