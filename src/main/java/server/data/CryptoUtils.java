package server.data;

import logger.Level;
import logger.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class CryptoUtils {
    public static String encrypt(String data, String key) {
        byte[] encryptedValue;
        try {
            Key keyVal = generateEncryptionKey(key.getBytes());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keyVal);
            encryptedValue = cipher.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e){
            Logger.log(Level.ERROR, "ERROR IN ENCRYPTION!!! Likely a client/server key mismatch.", e);
            return null;
        }
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decrypt(String data, String key) {
        byte[] decValue = {};
        try {
            Key encryptionKey = generateEncryptionKey(key.getBytes());
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, encryptionKey);
            byte[] decodedValue = Base64.getDecoder().decode(data);
            decValue = c.doFinal(decodedValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e){
            Logger.log(Level.ERROR, "ERROR IN DECRYPTION!!! Likely a client/server key mismatch.", e);
        }
        return new String(decValue);
    }

    private static Key generateEncryptionKey(byte[] key){
        return new SecretKeySpec(key, "AES");
    }

    public static String randTextAlpha(int length){
        int leftLimit = 65; // letter 'A'
        int rightLimit = 122; // letter 'z'
        Random rand = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++){
            int randomLimitedInt = leftLimit + (int)
                    (rand.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
