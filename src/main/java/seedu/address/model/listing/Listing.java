package seedu.address.model.listing;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import seedu.address.model.applicant.Applicant;

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
    /**
     * Every field must be present and not null.
     */
    public Listing(JobTitle jobTitle, JobDescription jobDescription, ArrayList<Applicant> applicants) {
        requireAllNonNull(jobTitle, jobDescription, applicants);
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.applicants.addAll(applicants);
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
        return Objects.hash(jobTitle, jobDescription, applicants, uniqueID);
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
        return builder.toString();
    }
}
