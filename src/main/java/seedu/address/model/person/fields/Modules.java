package seedu.address.model.person.fields;

import java.util.Set;

import seedu.address.model.person.fields.subfields.NusMod;

/**
 * Represents a Person's modules taken in the address book.
 */
public class Modules extends SuperField<NusMod> {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";

    public Modules(Set<NusMod> mods) {
        super(mods);
    }


    //todo: Update modules to only be able to include mods that are a part of NUSMods.
    /**
     * Checks whether all the mods are valid NUSMods
     *
     * @param mods the set of modules to be checked
     * @return false if there is at least one invalid module
     */
    public static boolean isValidModules(Set<NusMod> mods) {
        for (NusMod mod : mods) {
            if (!NusMod.isModuleCodeValid(mod.value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Modules // instanceof handles nulls
                // Uses Java's definition of equality between Sets.
                && this.values.equals(((Modules) other).values)); // state check
    }
}
