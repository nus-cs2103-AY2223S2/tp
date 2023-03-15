package seedu.address.model.tag;

/**
 * Represents a Module in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Module extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Module names should be alphanumeric";
    public final String moduleName;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleName A valid module name.
     */
    public Module(String moduleName) {
        super(moduleName, MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }
    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return isValidTagName(test);
    }
}
