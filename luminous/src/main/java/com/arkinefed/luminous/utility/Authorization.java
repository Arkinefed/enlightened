package com.arkinefed.luminous.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Map;

public class Authorization {
    private static final String SECRET = "777";
    private static final String HMAC_SHA256 = "HmacSHA256";

    public static String generateToken(String header, String payload) throws NoSuchAlgorithmException, InvalidKeyException {
        String encodedHeader = encode(header.getBytes(StandardCharsets.UTF_8));
        String encodedPayload = encode(payload.getBytes(StandardCharsets.UTF_8));

        String signature = calculateSignature(encodedHeader, encodedPayload);

        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    public static boolean verifyToken(String token) throws NoSuchAlgorithmException, InvalidKeyException {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            return false;
        }

        return calculateSignature(parts[0], parts[1]).equals(parts[2]);
    }

    public static String getValueFromTokenPayload(String key, String token) throws JsonProcessingException {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            return null;
        }

        String json = new String(Base64.getUrlDecoder().decode(parts[1]));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);

        return (String) map.get(key);
    }

    public static boolean tokenExpired(String token) throws JsonProcessingException {
        String expiration = getValueFromTokenPayload("exp", token);
        LocalDateTime expirationDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(expiration)), ZoneId.systemDefault());

        return LocalDateTime.now().isAfter(expirationDateTime);
    }

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean verifyPassword(String password, String encoded) {
        return new BCryptPasswordEncoder().matches(password, encoded);
    }

    private static String calculateSignature(String header, String payload) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), HMAC_SHA256);
        Mac hmac = Mac.getInstance(HMAC_SHA256);
        hmac.init(key);

        return encode(hmac.doFinal((header + "." + payload).getBytes(StandardCharsets.UTF_8)));
    }

    private static String encode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
}
