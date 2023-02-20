package com.nashss.se.ascendnashville.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Utility to create a random memberId and eventId.
 */
public class AscendNashvilleUtils {
    static final int MEMBER_ID_LENGTH = 6;
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    private AscendNashvilleUtils() {
    }

    /**
     * Method to generate a random memberId.
     * @return randomly generated numeric string.
     */
    public static String generateMemberId() {
        return RandomStringUtils.randomNumeric(MEMBER_ID_LENGTH);
    }

    /**
     * Method to generate a random eventId.
     * @return randomly generated numeric string.
     */
    public static String generateEventId() {
        Random rand = new Random();
        int max = 5;
        int min = 1;

        int randomEventId = rand.nextInt((max - min) + 1) + min;

        return String.valueOf(randomEventId);
    }

    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }
}
