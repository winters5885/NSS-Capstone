package ascendnashville.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility to create a random memberId.
 */
public class AscendNashvilleUtils {
        static final int MEMBER_ID_LENGTH = 6;

        private AscendNashvilleUtils() {
        }

        public static String generateMemberId() {
            return RandomStringUtils.randomNumeric(6);
        }
}
