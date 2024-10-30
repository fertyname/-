package kz.fertyname.work1;

import java.security.KeyPair;
import java.security.PrivateKey;

import javax.crypto.SecretKey;

import kz.fertyname.util.Util;

public class Cipher {

	public static void manager(String data) throws Exception {
		    KeyPair rsaKeyPair = UtilCipher.generateRSAKeyPair();
	        SecretKey aesKey = UtilCipher.generateAESKey();     
	        String encryptedPrivateKey = UtilCipher.encryptPrivateKey(rsaKeyPair.getPrivate(), aesKey);
	        String encryptedAESKey = UtilCipher.encryptAESKey(aesKey, rsaKeyPair.getPublic());
	        String encryptedData = UtilCipher.encryptData(data, aesKey);
	        PrivateKey decryptedPrivateKey = UtilCipher.decryptPrivateKey(encryptedPrivateKey, aesKey);
	        SecretKey decryptedAESKey = UtilCipher.decryptAESKey(encryptedAESKey, decryptedPrivateKey);
	        String decryptedData = UtilCipher.decryptData(encryptedData, decryptedAESKey);
			Util.m_Print("info", "Ответ шифрования: " + encryptedData, 20);
			Util.m_Print("info", "Ответ расшифрования: " + decryptedData, 20);
	}
	
}
