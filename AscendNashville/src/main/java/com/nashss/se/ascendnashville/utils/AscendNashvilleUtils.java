package com.nashss.se.ascendnashville.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Utility to create a random memberId and eventId, or validate a string.
 */
public class AscendNashvilleUtils {
    static final int MEMBER_ID_LENGTH = 6;
    static final int EVENT_ID_LENGTH = 4;
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
        return RandomStringUtils.randomNumeric(EVENT_ID_LENGTH);
    }

    /**
     * Method to validate a string.
     *
     * @param stringToValidate string that needs validation.
     *
     * @return boolean
     */
    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }
}
