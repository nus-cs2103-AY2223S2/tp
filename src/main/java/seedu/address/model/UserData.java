package seedu.address.model;

import static java.util.Objects.requireNonNull;

/**
 * Represents user's data
 */
public class UserData implements ReadOnlyUserData {

    private String hashedPassword;

    private int numberOfTimesUsed = 0;

    public UserData() {

    }

    /**
     * Creates a user data with the previous read only user data
     * @param userData
     */
    public UserData(ReadOnlyUserData userData) {
        this();
        resetData(userData);
    }

    /**
     * Creates a UserData with given hashedPassword and numberOfTimesUsed
     * @param hashedPassword Hashed password
     * @param numberOfTimesUsed number of times the user has used the application
     */
    public UserData(String hashedPassword, int numberOfTimesUsed) {
        this.hashedPassword = hashedPassword;
        this.numberOfTimesUsed = numberOfTimesUsed;
    }

    /**
     * Resets the existing data
     */
    public void resetData(ReadOnlyUserData userData) {
        requireNonNull(userData);
        this.setHashedPassword(userData.getHashedPassword());
        this.setNumberOfTimesUsed(userData.getNumberOfTimesUsed());
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setNumberOfTimesUsed(int numberOfTimesUsed) {
        this.numberOfTimesUsed = numberOfTimesUsed;
    }

    public int getNumberOfTimesUsed() {
        return this.numberOfTimesUsed;
    }
}
