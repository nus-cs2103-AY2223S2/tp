package seedu.address.model.person.fields.subfields;

/**
 * Represents a Person's singular mod taken in the address book.
 */
public class NusMod {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public final String name;

    public NusMod(String modsString) {
        this.name = modsString;
    }

    //todo: Update modules to only be able to include mods that are a part of NUSMods.
    public static boolean isValidModName(String trimmedTag) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof NusMod
                && this.name.equals(((NusMod) other).name));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
