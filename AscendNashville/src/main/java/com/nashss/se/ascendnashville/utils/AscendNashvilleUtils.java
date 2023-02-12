package com.nashss.se.ascendnashville.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility to create a random memberId.
 */
public class AscendNashvilleUtils {
    static final int MEMBER_ID_LENGTH = 6;

    private AscendNashvilleUtils() {
    }

    /**
     * Method to generate a random memberId.
     * @return randomly generated numberic string.
     */
    public static String generateMemberId() {
        return RandomStringUtils.randomNumeric(6);
    }
}
