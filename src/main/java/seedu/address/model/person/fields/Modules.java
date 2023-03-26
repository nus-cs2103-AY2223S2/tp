package seedu.address.model.person.fields;

import java.util.Collections;
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
     * Checks whether all the mods are valid NUSMods
     *
     * @param mods the set of modules to be checked
     * @return false if there is at least one invalid module
     */
    public static boolean isValidModules(Set<NusMod> mods) {
        for (NusMod mod : mods) {
            if (!NusMod.isValidModName(mod.name)) {
                return false;
            }
        }
        return true;
    }

    public Set<NusMod> getMods() {
        return Collections.unmodifiableSet(mods);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Modules // instanceof handles nulls
                // Uses Java's definition of equality between Sets.
                && this.mods.equals(((Modules) other).mods)); // state check
    }
}
