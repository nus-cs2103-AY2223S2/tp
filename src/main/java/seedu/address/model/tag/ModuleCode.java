package seedu.address.model.tag;

/**
 * Represents a Module in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class ModuleCode extends Tag {

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode) {
        super(moduleCode);
    }

}
