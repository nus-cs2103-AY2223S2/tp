package seedu.connectus.model.socialmedia;

/**
 * Platforms that support pre-filled chat links
 */
public interface Chatable {
    /**
     * Gives a link that launches the application to desired user homepage
     * with message text filled
     */
    String getUserLinkWithPreparedMessage(String message);
}
