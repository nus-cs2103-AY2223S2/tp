package seedu.modtrek.testutil;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.module.Module;

/**
 * A utility class to help with building Module objects.
 * Example usage: <br>
 *     {@code DegreeProgression dp = new DegreeProgressionBuilder().withModule("CS1101s", "MA2002").build();}
 */
public class DegreeProgressionBuilder {

    private DegreeProgression degreeProgression;

    public DegreeProgressionBuilder() {
        degreeProgression = new DegreeProgression();
    }

    public DegreeProgressionBuilder(DegreeProgression degreeProgression) {
        this.degreeProgression = degreeProgression;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public DegreeProgressionBuilder withModule(Module module) {
        degreeProgression.addModule(module);
        return this;
    }

    public DegreeProgression build() {
        return degreeProgression;
    }
}
