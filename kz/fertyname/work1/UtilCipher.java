package kz.fertyname.work1;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class UtilCipher {
    private static final String RSA_ALGORITHM = "RSA";
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        generator.initialize(2048);
        return generator.generateKeyPair();
    }
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
    public static String encryptPrivateKey(PrivateKey privateKey, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);

        byte[] encryptedKey = cipher.doFinal(privateKey.getEncoded());
        return Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(encryptedKey);
    }
    public static PrivateKey decryptPrivateKey(String encryptedPrivateKey, SecretKey aesKey) throws Exception {
        String[] parts = encryptedPrivateKey.split(":");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(parts[0]));
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);

        byte[] decodedKey = cipher.doFinal(Base64.getDecoder().decode(parts[1]));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
    }
    public static String encryptData(String data, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);

        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(encryptedData);
    }
    public static String decryptData(String encryptedData, SecretKey aesKey) throws Exception {
        String[] parts = encryptedData.split(":");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(parts[0]));
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);

        byte[] decodedData = cipher.doFinal(Base64.getDecoder().decode(parts[1]));
        return new String(decodedData);
    }
    public static String encryptAESKey(SecretKey aesKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKey = cipher.doFinal(aesKey.getEncoded());
        return Base64.getEncoder().encodeToString(encryptedKey);
    }
    public static SecretKey decryptAESKey(String encryptedAESKey, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedKey = cipher.doFinal(Base64.getDecoder().decode(encryptedAESKey));
        return new SecretKeySpec(decodedKey, AES_ALGORITHM);
    }
}
