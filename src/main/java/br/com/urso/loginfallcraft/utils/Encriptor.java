package br.com.urso.loginfallcraft.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptor {
    public static String toMD5(final String text) {
        try {
            final MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(text.getBytes(), 0, text.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toSHA1(final String text) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(text.getBytes(StandardCharsets.UTF_8));
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            return null;
        }
    }
}
