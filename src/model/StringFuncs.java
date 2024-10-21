package model;

/**
 * Utility class providing custom string-related functions.
 */
public class StringFuncs {

    /**
     * Custom method to calculate the length of a string without using .length().
     * 
     * @param str The string whose length is to be calculated.
     * @return The length of the string.
     */
    public static int getLength(String str) {
        if (str == null) {
            return 0; // Return 0 for null strings
        }

        int length = 0;

        // Increment length until the end of the string is reached
        while (true) {
            try {
                char c = str.charAt(length);
                length++;
            } catch (IndexOutOfBoundsException e) {
                break; // When an exception is thrown, we have reached the end of the string
            }
        }

        return length;
    }

    /**
     * Compares two strings for equality in a case-insensitive manner without using
     * any external functions like Character.toLowerCase.
     * 
     * @param str1 The first string to compare.
     * @param str2 The second string to compare.
     * @return true if both strings are equal (ignoring case), false otherwise.
     */
    public static boolean customStringEquals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        // Compare lengths of both strings using custom length method
        if (getLength(str1) != getLength(str2)) {
            return false; // If lengths are not equal, strings are not equal.
        }

        // Iterate over both strings simultaneously
        for (int i = 0; i < getLength(str1); i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);

            // Check if characters are equal, accounting for case difference
            if (char1 != char2) {
                // Convert uppercase to lowercase by adjusting ASCII values manually
                if (char1 >= 'A' && char1 <= 'Z') {
                    char1 = (char) (char1 + 32);
                }
                if (char2 >= 'A' && char2 <= 'Z') {
                    char2 = (char) (char2 + 32);
                }

                // After adjusting, if characters still don't match, return false
                if (char1 != char2) {
                    return false;
                }
            }
        }

        return true; // All characters match, so the strings are equal.
    }
}
