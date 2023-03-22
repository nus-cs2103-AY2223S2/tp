package seedu.address.model.person.fields;

import java.util.Set;

import seedu.address.model.person.fields.subfields.NusMod;


/**
 * Represents a Person's modules taken in the address book.
 */
public class Modules {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public final Set<NusMod> mods;

    public Modules(Set<NusMod> mods) {
        this.mods = mods;
    }

    /**
     * Returns true if test string is contained within the value of any of the NUSMods.
     */
    public boolean contains(String test) {
        if (!test.isEmpty() && this.mods.isEmpty()) {
            return false;
        }
        for (NusMod mod: this.mods) {
            if (mod.contains(test)) {
                return true;
            }
        }
        return false;
    }

    //todo: Update modules to only be able to include mods that are a part of NUSMods.
    public static boolean isValidModules(String trimmedModules) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Modules // instanceof handles nulls
                // Uses Java's definition of equality between Sets.
                && this.mods.equals(((Modules) other).mods)); // state check
    }
}
