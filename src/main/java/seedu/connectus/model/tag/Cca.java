package seedu.connectus.model.tag;

/**
 * Represents a Cca in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Cca extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "CCA names should be alphanumeric";
    public final String ccaName;

    /**
     * Constructs a {@code Module}.
     *
     * @param ccaName A valid CCA name.
     */
    public Cca(String ccaName) {
        super(ccaName, MESSAGE_CONSTRAINTS);
        this.ccaName = ccaName;
    }

    /**
     * Returns true if a given string is a valid cca name.
     */
    public static boolean isValidCcaName(String test) {
        return isValidTagName(test);
    }
}
