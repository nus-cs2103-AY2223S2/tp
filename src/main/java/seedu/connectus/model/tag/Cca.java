package seedu.connectus.model.tag;

public class Cca extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "CCA names should be alphanumeric";

    /**
     * Constructs a {@code Module}.
     *
     * @param ccaName A valid CCA name.
     */
    public Cca(String ccaName) {
        super(ccaName);
    }

    /**
     * {@inheritDoc}
     */
    public static boolean isValidCcaName(String test) {
        return isValidTagName(test);
    }
}
