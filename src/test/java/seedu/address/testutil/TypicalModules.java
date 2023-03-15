package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Tracker;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2105 = new ModuleBuilder().withCode("CS2105").withName("network").build();
    public static final Module CS2030 = new ModuleBuilder().withCode("CS2030").withName("pm2").build();
    public static final Module CS2040 = new ModuleBuilder().withCode("CS2040").withName("dsa").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Tracker getTypicalTracker() {
        Tracker tracker = new Tracker();
        for (Module module : getTypicalModules()) {
            tracker.addModule(module);
        }
        return tracker;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2105, CS2030, CS2040));
    }
}
