package ascendnashville.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class AscendNashvilleUtils {
        static final int MEMBER_ID_LENGTH = 6;

        private AscendNashvilleUtils() {
        }

        public static String generateMemberId() {
            return RandomStringUtils.randomNumeric(6);
        }
}
