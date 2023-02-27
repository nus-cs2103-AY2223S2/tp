package seedu.address.model.person.fields;

import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Set;

public class Modules {


    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public Set<NusMod> mods;

    public Modules(Set<NusMod> mods) {
        this.mods = mods;
    }

    //todo: Update modules to only be able to include mods that are a part of NUSMods.
    public static boolean isValidModules(String trimmedModules) {
        return true;
    }
}
