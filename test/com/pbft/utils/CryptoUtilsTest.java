package com.pbft.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Arrays;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class CryptoUtilsTest {

	@Before
	public void init() {
	}

	@Test
//	@Ignore
	public void hashTest_WithInputSameString_ThenReturnEqual() {
		String rawData1 = "chungnt";
		String rawData2 = "chungnt";
		String hashData1 = CryptoUtils.hash(rawData1);
		String hashData2 = CryptoUtils.hash(rawData2);
		Assert.assertTrue(String.format("hashData1:%s, hashData2:%s", hashData1, hashData2), hashData1.equals(hashData2));
		System.out.println("hashTest_WithInputSameString_ThenReturnEqual: " + hashData1);
	}

	@Test
//	@Ignore
	public void hashTest_WithInputSameByteArr_ThenReturnEqual() {
		byte[] rawData1 = "chungnt".getBytes(StandardCharsets.UTF_8);
		byte[] rawData2 = "chungnt".getBytes(StandardCharsets.UTF_8);
		byte[] hashData1 = CryptoUtils.hash(rawData1);
		byte[] hashData2 = CryptoUtils.hash(rawData2);
		Assert.assertTrue(String.format("hashData1:%s, hashData2:%s", hashData1, hashData2), Arrays.equals(hashData1, hashData2));
		System.out.println("hashTest_WithInputSameByteArr_ThenReturnEqual: " + hashData1);
	}

	@Test
//	@Ignore
	public void hashTest_WithInputDifferentString_ThenReturnNotEqual() {
		String rawData1 = "chungnt-123";
		String rawData2 = "chungnt-321";
		String hashData1 = CryptoUtils.hash(rawData1);
		String hashData2 = CryptoUtils.hash(rawData2);
		Assert.assertTrue(String.format("hashData1:%s, hashData2:%s", hashData1, hashData2), !hashData1.equals(hashData2));
		System.out.println("hashTest_WithInputDifferentString_ThenReturnNotEqual: hash1: " + hashData1 + ", hash2: " + hashData2);
	}

	@Test
//	@Ignore
	public void hashTest_WithInputDifferentByteArr_ThenReturnNotEqual() {
		byte[] rawData1 = "chungnt-123".getBytes(StandardCharsets.UTF_8);
		byte[] rawData2 = "chungnt-321".getBytes(StandardCharsets.UTF_8);
		byte[] hashData1 = CryptoUtils.hash(rawData1);
		byte[] hashData2 = CryptoUtils.hash(rawData2);
		Assert.assertTrue(String.format("hashData1:%s, hashData2:%s", hashData1, hashData2), !Arrays.equals(hashData1, hashData2));
		System.out.println("hashTest_WithInputDifferentByteArr_ThenReturnNotEqual: hash1: " + hashData1 + ", hash2: " + hashData2);
	}

	@Test
//	@Ignore
	public void generateKeyPairTest() {
		KeyPair keyPair = CryptoUtils.generateKeyPair();
		Assert.assertTrue("KeyPair gen is NULL", keyPair != null);
		System.out.println("pubKey: " + keyPair.getPublic() + ",\nprivKey: " + keyPair.getPrivate());
	}

	@Test
//	@Ignore
	public void encryptDecryptMethodTest() {
		KeyPair keyPair = CryptoUtils.generateKeyPair();
		Assert.assertTrue("KeyPair gen is NULL", keyPair != null);
		String rawData = "chungnt";
		byte[] rawDataByte = rawData.getBytes(StandardCharsets.UTF_8);

		byte[] encryptedMsgByte = CryptoUtils.encrypt(rawDataByte, keyPair.getPrivate());
		byte[] decryptedMsgByte = CryptoUtils.decrypt(encryptedMsgByte, keyPair.getPublic());
		String decryptedMsg = new String(decryptedMsgByte, StandardCharsets.UTF_8);

		Assert.assertTrue(String.format("[encryptDecryptMethodTest]- rawData: %s, decryptedData: %s", rawData, decryptedMsg),
				rawData.equals(decryptedMsg));
	}
}
