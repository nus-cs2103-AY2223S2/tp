package seedu.address.testutil;

import seedu.address.model.InternshipBook;
import seedu.address.model.application.Application;

/**
 * A utility class to help with building InternshipBook objects.
 * Example usage: <br>
 *     {@code InternshipBook ab = new InternshipBookBuilder().withApplication("SWE Intern", "Meta", "abc@gmail.com",
 *     "applied").build();}
 */
public class InternshipBookBuilder {

    private InternshipBook internshipBook;

    public InternshipBookBuilder() {
        internshipBook = new InternshipBook();
    }

    public InternshipBookBuilder(InternshipBook internshipBook) {
        this.internshipBook = internshipBook;
    }

    /**
     * Adds a new {@code Application} to the {@code InternshipBook} that we are building.
     */
    public InternshipBookBuilder withApplication(Application application) {
        internshipBook.addApplication(application);
        return this;
    }

    public InternshipBook build() {
        return internshipBook;
    }
}
