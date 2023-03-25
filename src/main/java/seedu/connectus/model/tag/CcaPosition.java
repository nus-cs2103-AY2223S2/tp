package seedu.connectus.model.tag;

public class CcaPosition extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "CCA Position should be alphanumeric";

    /**
     * Constructs a {@code Module}.
     *
     * @param ccaPosition A valid CCA position.
     */
    public CcaPosition(String ccaPosition) {
        super(ccaPosition);
    }

    /**
     * {@inheritDoc}
     */
    public static boolean isValidCcaPositionName(String test) {
        return isValidTagName(test);
    }
}
