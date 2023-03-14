package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.model.ListingBook;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;

/**
 * Contains utility methods for populating {@code ListingBook} with sample data.
 */
public class SampleDataUtil {
    public static Listing[] getSampleListings() {
        return new Listing[] {
            new Listing(new JobTitle("SE Summer Intern"),
                    new JobDescription("3 month internship specialising in Software Engineering."),
                    getApplicantSet("friends")),
            new Listing(new JobTitle("UI Summer Intern"),
                    new JobDescription("3 month internship specialising in UI/UX"),
                    getApplicantSet("colleagues", "friends")),
            new Listing(new JobTitle("Computer Security Summer Intern"),
                    new JobDescription("3 month internship specialising in Computer Security."),
                    getApplicantSet("Charlotte Oliveiro")),
            new Listing(new JobTitle("AI Summer Intern"),
                    new JobDescription("3 month internship specialising in Artificial Intelligence"),
                    getApplicantSet("David Li")),
            new Listing(new JobTitle("Algorithms Summer Intern"),
                    new JobDescription("3 month internship specialising in Algorithms."),
                    getApplicantSet("Irfan Ibrahim")),
            new Listing(new JobTitle("Parallel Computing Summer Intern"),
                    new JobDescription("3 month internship specialising in Parallel Computing."),
                    getApplicantSet("Roy Balakrishnan"))
        };
    }

    public static ReadOnlyListingBook getSampleListingBook() {
        ListingBook sampleLb = new ListingBook();
        for (Listing sampleListing : getSampleListings()) {
            sampleLb.addListing(sampleListing);
        }
        return sampleLb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static ArrayList<Applicant> getApplicantSet(String... strings) {
        return Arrays.stream(strings)
                .map((name) -> new Applicant(new Name(name)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
