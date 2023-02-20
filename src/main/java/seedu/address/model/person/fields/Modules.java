package seedu.address.model.person.fields;

import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Set;

public class Modules {

    public static final String MESSAGE_CONSTRAINTS = "stonks";
    public Set<NusMod> mods;

    public Modules(Set<NusMod> mods) {
        this.mods = mods;
    }

    public static boolean isValidModules(String trimmedModules) {
        return true;
    }
}
