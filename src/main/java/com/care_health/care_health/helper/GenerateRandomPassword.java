package com.care_health.care_health.helper;
import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandomPassword {
    public static String generateRandomPassword(int length) {

        return RandomStringUtils.randomAlphanumeric(length);
    }
}
