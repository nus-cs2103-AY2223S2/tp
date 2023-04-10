package seedu.internship.testutil;

import seedu.internship.model.InternBuddy;
import seedu.internship.model.internship.Internship;

/**
 * A utility class to help with building InternBuddy objects.
 * Example usage: <br>
 *     {@code InternBuddy ab = new InternBuddyBuilder().withInternship("Apple", "Google").build();}
 */
public class InternBuddyBuilder {

    private InternBuddy internBuddy;

    public InternBuddyBuilder() {
        internBuddy = new InternBuddy();
    }

    public InternBuddyBuilder(InternBuddy internBuddy) {
        this.internBuddy = internBuddy;
    }

    /**
     * Adds a new {@code Internship} to the {@code InternBuddy} that we are building.
     */
    public InternBuddyBuilder withInternship(Internship internship) {
        internBuddy.addInternship(internship);
        return this;
    }

    public InternBuddy build() {
        return internBuddy;
    }
}
