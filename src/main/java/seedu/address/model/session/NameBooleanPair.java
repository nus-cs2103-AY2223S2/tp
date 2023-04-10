package seedu.address.model.session;
/**
 * A pair of a name and a boolean indicating whether the person is present.
 */
public class NameBooleanPair {
    private String name;
    private boolean isPresent;
    /**
     * Constructs a {@code NameBooleanPair} object with the given name and presence status.
     * @param name The name of the person.
     * @param isPresent The presence status of the person.
     */
    public NameBooleanPair(String name, boolean isPresent) {
        this.name = name;
        this.isPresent = isPresent;
    }
    /**
     * Returns the name of the person in this pair.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the presence status of the person in this pair.
     * @return True if the person is present, false otherwise.
     */
    public boolean isPresent() {
        return isPresent;
    }
    @Override
    public String toString() {
        if (isPresent) {
            return name + ": present";
        } else {
            return name + ": absent";
        }
    }
}
