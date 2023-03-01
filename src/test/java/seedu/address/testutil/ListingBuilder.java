package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.listing.Description;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.Title;

/**
 * A utility class to help with building Person objects.
 */
public class ListingBuilder {

    public static final String DEFAULT_TITLE = "This is a default job title";
    public static final String DEFAULT_DESCRIPTION =
            "This is a default job description! "
            + "In this job you are expected to do these things: thing1, thing2 and thing3.";
    public static final ArrayList<String> DEFAULT_APPLICANTS = new ArrayList<>();

    private Title title;
    private Description description;
    private ArrayList<String> applicants;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ListingBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        applicants = new ArrayList<>(DEFAULT_APPLICANTS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code listingToCopy}.
     */
    public ListingBuilder(Listing listingToCopy) {
        title = listingToCopy.getTitle();
        description = listingToCopy.getDescription();
        applicants = new ArrayList<>(listingToCopy.getApplicants());
    }

    /**
     * Sets the {@code Title} of the {@code Listing} that we are building.
     */
    public ListingBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Listing} that we are building.
     */
    public ListingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code applicants} of the {@code Listing} that we are building.
     *
     */
    public ListingBuilder withApplicants(ArrayList<String> applicants) {
        this.applicants = new ArrayList<String>(applicants);
        return this;
    }

    /**
     * Buildings the Listing we are building.
     */
    public Listing build() {
        return new Listing(title, description, applicants);
    }

}
