package seedu.connectus.model.tag;

import static seedu.connectus.commons.util.AppUtil.checkArgument;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;

/**
 * Represents a Module in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Module extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Module names should be alphanumeric "
            + "and not contain any spaces\n"
            + "Example: " + PREFIX_MODULE + "CS2103T";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public final String moduleName;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleName A valid module name.
     */
    public Module(String moduleName) {
        super(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }
    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
