package biz.bagira.shds.service;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * Created by Dmitriy on 13.10.2016.
 */
public class PasswordService {
    final static Logger logger = Logger.getLogger(PasswordService.class);

    public String getSecurePassword(String passwordToHash, byte[] salt)  {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            generatedPassword = convertByteToHex(bytes);
        }
        catch (NoSuchAlgorithmException e) {
         logger.debug(e.getMessage());
        }
        return generatedPassword;
    }

    private String convertByteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return  sb.toString();
    }


    public byte[] getSalt()  {
        SecureRandom sr = null;
        byte[] salt = new byte[16];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG","SUN");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            logger.debug(e.getMessage());
        } catch (NoSuchProviderException e) {
            logger.debug(e.getMessage());
        }
           return salt;
    }



    public  boolean validatePassword(String originalPass, String passFromDB) {
        return originalPass.equals(passFromDB);
    }

    public static boolean isNumber(String str) {
            if (str == null || str.isEmpty()) {
                return false;
            }
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                if (aChar == '.') {
                    continue;
                }
                if (!Character.isDigit(aChar)) {
                    return false;
                }
            }
            return true;
    }

    public static boolean isValidString(String s) {
           if (s == null) return false;
           if (s.trim().isEmpty()) return false;
           return true;
       }

}
