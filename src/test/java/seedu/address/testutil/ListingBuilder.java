package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;

/**
 * A utility class to help with building Listing objects.
 */
public class ListingBuilder {

    public static final String DEFAULT_TITLE = "This is a default job jobTitle";
    public static final String DEFAULT_DESCRIPTION =
            "This is a default job jobDescription! "
            + "In this job you are expected to do these things: thing1, thing2 and thing3.";
    public static final ArrayList<String> DEFAULT_APPLICANTS = new ArrayList<>();

    private JobTitle jobTitle;
    private JobDescription jobDescription;
    private ArrayList<String> applicants;

    /**
     * Creates a {@code ListingBuilder} with the default details.
     */
    public ListingBuilder() {
        jobTitle = new JobTitle(DEFAULT_TITLE);
        jobDescription = new JobDescription(DEFAULT_DESCRIPTION);
        applicants = new ArrayList<>(DEFAULT_APPLICANTS);
    }

    /**
     * Initializes the ListingBuilder with the data of {@code listingToCopy}.
     */
    public ListingBuilder(Listing listingToCopy) {
        jobTitle = listingToCopy.getTitle();
        jobDescription = listingToCopy.getDescription();
        applicants = new ArrayList<>(listingToCopy.getApplicants());
    }

    /**
     * Sets the {@code JobTitle} of the {@code Listing} that we are building.
     */
    public ListingBuilder withTitle(String title) {
        this.jobTitle = new JobTitle(title);
        return this;
    }

    /**
     * Sets the {@code JobDescription} of the {@code Listing} that we are building.
     */
    public ListingBuilder withDescription(String description) {
        this.jobDescription = new JobDescription(description);
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
        return new Listing(jobTitle, jobDescription, applicants);
    }

}
