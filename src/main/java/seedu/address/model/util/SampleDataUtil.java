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
import seedu.address.model.platform.Platform;
import seedu.address.model.platform.PlatformName;

/**
 * Contains utility methods for populating {@code ListingBook} with sample data.
 */
public class SampleDataUtil {
    public static Listing[] getSampleListings() {
        return new Listing[] {
            new Listing(new JobTitle("SE Summer Intern"),
                    new JobDescription("3 month internship specialising in Software Engineering."),
                    getApplicantSet("friends"),
                    getPlatformSet("linkedin")),
            new Listing(new JobTitle("UI Summer Intern"),
                    new JobDescription("3 month internship specialising in UI/UX"),
                    getApplicantSet("colleagues", "friends"),
                    getPlatformSet("indeed", "linkedin")),
            new Listing(new JobTitle("Computer Security Summer Intern"),
                    new JobDescription("3 month internship specialising in Computer Security."),
                    getApplicantSet("Charlotte Oliveiro"),
                    getPlatformSet("talentConnect")),
            new Listing(new JobTitle("AI Summer Intern"),
                    new JobDescription("3 month internship specialising in Artificial Intelligence"),
                    getApplicantSet("David Li"),
                    getPlatformSet("glints")),
            new Listing(new JobTitle("Algorithms Summer Intern"),
                    new JobDescription("3 month internship specialising in Algorithms."),
                    getApplicantSet("Irfan Ibrahim"),
                    getPlatformSet("jobstreet")),
            new Listing(new JobTitle("Parallel Computing Summer Intern"),
                    new JobDescription("3 month internship specialising in Parallel Computing."),
                    getApplicantSet("Roy Balakrishnan"),
                    getPlatformSet())
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
     * Returns an applicant set containing the list of strings given.
     */
    public static ArrayList<Applicant> getApplicantSet(String... strings) {
        return Arrays.stream(strings)
                .map((name) -> new Applicant(new Name(name)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a platform set containing the list of strings given.
     */
    public static ArrayList<Platform> getPlatformSet(String... strings) {
        return Arrays.stream(strings)
                .map((platformName) -> new Platform(new PlatformName(platformName)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
