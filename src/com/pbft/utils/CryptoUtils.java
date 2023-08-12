package com.pbft.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.logging.Level;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class CryptoUtils {

	private static final Logger LOGGER = Logger.getLogger(CryptoUtils.class.getName());
	private static final String ENCRYPTED_ALGORITHM = "RSA";

	public static byte[] hash(byte[] data) {
		return DigestUtils.sha256(data);
	}

	public static String hash(String data) {
		return DigestUtils.sha256Hex(data);
	}

	public static KeyPair generateKeyPair() {
		KeyPair pair = null;
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			pair = generator.generateKeyPair();
		} catch (NoSuchAlgorithmException ex) {
			LOGGER.log(Level.WARNING, ex.getMessage(), ex);
		}
		return pair;
	}

	public static byte[] encrypt(byte[] rawData, PublicKey publicKey) {
		try {
			Cipher encryptCipher = Cipher.getInstance(ENCRYPTED_ALGORITHM);
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return encryptCipher.doFinal(rawData);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
				 BadPaddingException ex) {
			LOGGER.log(Level.WARNING, ex.getMessage(), ex);
		}
		return null;
	}

	public static byte[] decrypt(byte[] encryptedData, PrivateKey privateKey) {
		try {
			Cipher decryptCipher = Cipher.getInstance("RSA");
			decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
			return decryptCipher.doFinal(encryptedData);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
				 BadPaddingException ex) {
			LOGGER.log(Level.WARNING, ex.getMessage(), ex);
		}
		return null;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		keyFactory.generatePublic(publicKeySpec);

		String secretMessage = "Baeldung secret message";
		System.out.println("rawMsg: " + secretMessage);
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

		byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

		Cipher decryptCipher = Cipher.getInstance("RSA");
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
		String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
		System.out.println("decryptMsg: " + decryptedMessage);
	}
}
