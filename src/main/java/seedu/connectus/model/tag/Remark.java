package seedu.connectus.model.tag;

import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;

/**
 * Represents a Remark in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Remark extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Remark names should be alphanumeric and may contain spaces\n"
            + "Format: " + PREFIX_REMARK + "REMARK";
    public final String remarkName;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remarkName A valid remark name.
     */
    public Remark(String remarkName) {
        super(remarkName, MESSAGE_CONSTRAINTS);
        this.remarkName = remarkName;
    }
    /**
     * Returns true if a given string is a valid remarkName.
     */
    public static boolean isValidRemarkName(String test) {
        return isValidTagName(test);
    }
}
