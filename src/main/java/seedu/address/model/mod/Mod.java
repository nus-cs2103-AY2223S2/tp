package seedu.address.model.mod;

/**
 * Skeleton of Mod class required for reading and writing to Json file.
 */
public class Mod {

    public static final String MESSAGE_CONSTRAINTS = "Module names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String modName;

    public Mod(String modName) {
        this.modName = modName;
    }

    public static boolean isValidSkillName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getModName() {
        return this.modName;
    }
}
