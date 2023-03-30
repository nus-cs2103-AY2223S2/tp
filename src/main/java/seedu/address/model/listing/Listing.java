package seedu.address.model.listing;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.platform.Platform;

/**
 * Represents a Listing in GoodMatch.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Listing {
    // Compulsory fields
    private JobTitle jobTitle;
    private JobDescription jobDescription;
    private String uniqueID = UUID.randomUUID().toString();

    // Optional fields
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<Platform> platforms = new ArrayList<>();
    /**
     * Every field must be present and not null.
     */
    public Listing(JobTitle jobTitle, JobDescription jobDescription, ArrayList<Applicant> applicants,
                   ArrayList<Platform> platforms) {
        requireAllNonNull(jobTitle, jobDescription, applicants, platforms);
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.applicants.addAll(applicants);
        this.platforms.addAll(platforms);
    }

    public JobTitle getTitle() {
        return jobTitle;
    }

    public JobDescription getDescription() {
        return jobDescription;
    }

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public ArrayList<Platform> getPlatforms() { return platforms; }

    public int getPlatformCount() {
        return platforms.size();
    }

    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * Returns true if both listings have the same jobTitle.
     * This defines a weaker notion of equality between two listings.
     */
    public boolean isSameListing(Listing otherListing) {
        if (otherListing == this) {
            return true;
        }

        return otherListing != null
                && otherListing.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both listings have the same data fields.
     * This defines a stronger notion of equality between two listings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Listing)) {
            return false;
        }

        Listing otherListing = (Listing) other;
        return otherListing.getTitle().equals(getTitle())
                && otherListing.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(jobTitle, jobDescription, applicants, platforms, uniqueID);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nJob Title: " + getTitle())
                .append("\nJob Description: ")
                .append(getDescription());

        ArrayList<Applicant> applicants = getApplicants();
        if (!applicants.isEmpty()) {
            builder.append("\nApplicants: ");
            for (int index = 0; index < applicants.size(); index++) {
                String applicant = applicants.get(index).toString();
                //work on the element
                if (index != applicants.size() - 1) {
                    applicant += ", ";
                }
                builder.append(applicant);
            }
        }

        ArrayList<Platform> platforms = getPlatforms();
        if (!platforms.isEmpty()) {
            builder.append("\nPlatforms: ");
            for (int index = 0; index < platforms.size(); index++) {
                String platform = platforms.get(index).toString();
                //work on the element
                if (index != platforms.size() - 1) {
                    platform += ", ";
                }
                builder.append(platform);
            }
        }
        return builder.toString();
    }
}
