package com.pbft.tendermint.node;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class CompliantProcessorTest {

	private CompliantProcessor processor;

	@Before
	public void init() {
		processor = new CompliantProcessor();
	}

	@Test
//	@Ignore
	public void getThresholdConsensusTest() {
		int totalNeighbors = 5;
		int expectedResult = 3;
		int actualResult = processor.getThresholdConsensus(totalNeighbors);
		Assert.assertTrue(String.format("getThresholdConsensusTest: expected=%d, actual=%d", expectedResult, actualResult),
				expectedResult == actualResult);
	}

}
