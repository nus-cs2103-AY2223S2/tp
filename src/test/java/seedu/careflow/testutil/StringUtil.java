package seedu.careflow.testutil;

import java.util.Random;

/**
 * A utility class for handling strings.
 */
public class StringUtil {
    /**
     * Generate a random string with given length.
     * @param len length of string
     * @return random string with given length
     */
    public static String generateRandomString(int len) {
        //@@author Jiayan-Lim-reused
        //Reused from https://www.programiz.com/java-programming/examples/generate-random-string
        // with minor modifications
        StringBuilder sb = new StringBuilder(); // create random string builder
        Random random = new Random(); // create an object of Random class

        // list all character and space
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";

        for (int i = 0; i < len; i++) {
            // generate random character
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }

    /**
     * Generates random string that contain character and number with specific length given.
     * @param len length of string
     * @return random alphanumeric string
     */
    public static String generateRandomAlphaNumericString(int len) {
        //@@author Jiayan-Lim-reused
        //Reused from https://www.programiz.com/java-programming/examples/generate-random-string
        // with minor modifications
        StringBuilder sb = new StringBuilder(); // create random string builder
        Random random = new Random(); // create an object of Random class

        // list all character and space
        String alphabet = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";

        for (int i = 0; i < len; i++) {
            // generate random character
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }

    /**
     * Generates random string that contain only number with specific length given.
     * @param len length of string
     * @return random numeric string
     */
    public static String generateRandomNumericString(int len) {
        //@@author Jiayan-Lim-reused
        //Reused from https://www.programiz.com/java-programming/examples/generate-random-string
        // with minor modifications
        StringBuilder sb = new StringBuilder(); // create random string builder
        Random random = new Random(); // create an object of Random class

        // list all character and space
        String alphabet = "1234567890";

        for (int i = 0; i < len; i++) {
            // generate random numeric character
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
