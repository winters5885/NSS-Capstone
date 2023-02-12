package com.nashss.se.ascendnashville.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility to create a random memberId and eventId.
 */
public class AscendNashvilleUtils {
    static final int MEMBER_ID_LENGTH = 6;
    static final int Event_ID_LENGTH = 4;

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
        return RandomStringUtils.randomNumeric(Event_ID_LENGTH);
    }
}
