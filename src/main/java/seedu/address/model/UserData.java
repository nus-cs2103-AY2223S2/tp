package seedu.address.model;

public class UserData implements ReadOnlyUserData {

    private String hashedPassword;

    private int numberOfTimesUsed = 0;

    public UserData() {

    }

    public UserData(String hashedPassword, int numberOfTimesUsed) {
        this.hashedPassword = hashedPassword;
        this.numberOfTimesUsed = numberOfTimesUsed;
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
