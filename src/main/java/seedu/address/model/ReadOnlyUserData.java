package seedu.address.model;

/**
 * Unmodifiable view of user data.
 */
public interface ReadOnlyUserData {

    public String getHashedPassword();
    public int getNumberOfTimesUsed();
}
