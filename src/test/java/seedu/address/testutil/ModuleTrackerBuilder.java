package seedu.address.testutil;

import seedu.address.model.ModuleTracker;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Moduletracker objects.
 * Example usage: <br>
 *     {@code ModuleTracker mt = new ModuleTrackerBuilder().withModule("John", "Doe").build();}
 */
public class ModuleTrackerBuilder {

    private ModuleTracker moduleTracker;

    public ModuleTrackerBuilder() {
        moduleTracker = new ModuleTracker();
    }

    public ModuleTrackerBuilder(ModuleTracker moduleTracker) {
        this.moduleTracker = moduleTracker;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleTracker} that we are building.
     */
    public ModuleTrackerBuilder withModule(Module module) {
        moduleTracker.addModule(module);
        return this;
    }

    public ModuleTracker build() {
        return moduleTracker;
    }
}
