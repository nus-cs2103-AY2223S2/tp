package seedu.internship.testutil;

import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.internship.Internship;

/**
 * A utility class to help with building InternshipCatalogue objects.
 * Example usage: <br>
 *     {@code InternshipCatalogue ic = new InternshipCatalogueBuilder().withInternship("SE1", "SE2").build();}
 */
public class InternshipCatalogueBuilder {
    private InternshipCatalogue internshipCatalogue;

    public InternshipCatalogueBuilder() {
        this.internshipCatalogue = new InternshipCatalogue();
    }

    public InternshipCatalogueBuilder(InternshipCatalogue internshipCatalogue) {
        this.internshipCatalogue = internshipCatalogue;
    }

    /**
     * Adds a new {@code Internship} to the {@code InternshipCatalogue} that we are building.
     */
    public InternshipCatalogueBuilder withInternship(Internship internship) {
        internshipCatalogue.addInternship(internship);
        return this;
    }

    public InternshipCatalogue build() {
        return internshipCatalogue;
    }
}

