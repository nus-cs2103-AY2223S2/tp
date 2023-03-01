package seedu.address.model.listing;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;


public class Listing {
    // Compulsory fields
    private Title title;
    private Description description;
    private String uniqueID = UUID.randomUUID().toString();

    // Optional fields
    private ArrayList<String> applicants = new ArrayList<>(); // TODO: Update when Applicant class is added

    /**
     * Every field must be present and not null.
     */
    public Listing(Title title, Description description, ArrayList<String> applicants) {
        requireAllNonNull(title, description, applicants);
        this.title = title;
        this.description = description;
        this.applicants.addAll(applicants);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public ArrayList<String> getApplicants() {
        return applicants;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * Returns true if both listings have the same title.
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
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
        return Objects.hash(title, description, applicants, uniqueID);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        ArrayList<String> applicants = getApplicants();
        if (!applicants.isEmpty()) {
            builder.append("; Applicants: ");
            applicants.forEach(builder::append);
        }
        return builder.toString();
    }
}
