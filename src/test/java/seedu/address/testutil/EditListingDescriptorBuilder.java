package seedu.address.testutil;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditListingDescriptor;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;

/**
 * A utility class to help with building EditListingDescriptor objects.
 */
public class EditListingDescriptorBuilder {

    private EditListingDescriptor descriptor;

    public EditListingDescriptorBuilder() {
        descriptor = new EditListingDescriptor();
    }

    public EditListingDescriptorBuilder(EditListingDescriptor descriptor) {
        this.descriptor = new EditListingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditListingDescriptor} with fields containing {@code listing}'s details
     */
    public EditListingDescriptorBuilder(Listing listing) {
        descriptor = new EditListingDescriptor();
        descriptor.setJobTitle(listing.getTitle());
        descriptor.setJobDescription(listing.getDescription());
        descriptor.setApplicants(listing.getApplicants());
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditListingDescriptor} that we are building.
     */
    public EditListingDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }

    /**
     * Sets the {@code JobDescription} of the {@code EditListingDescriptor} that we are building.
     */
    public EditListingDescriptorBuilder withJobDescription(String jobDescription) {
        descriptor.setJobDescription(new JobDescription(jobDescription));
        return this;
    }

    /**
     * Parses the {@code applicants} into a {@code ArrayList<Applicant>} and set it to the {@code EditListingDescriptor}
     * that we are building.
     */
    public EditListingDescriptorBuilder withApplicants(String... applicants) {
        ArrayList<Applicant> applicantArrayList = new ArrayList<>(Stream.of(applicants).map((name) ->
                        new Applicant(new Name(name))).collect(Collectors.toList()));
        descriptor.setApplicants(applicantArrayList);
        return this;
    }

    public EditListingDescriptor build() {
        return descriptor;
    }
}
