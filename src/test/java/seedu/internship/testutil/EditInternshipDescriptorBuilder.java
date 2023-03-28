package seedu.internship.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.internship.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditInternshipDescriptor descriptor) {
        this.descriptor = new EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setCompanyName(internship.getCompanyName());
        descriptor.setRole(internship.getRole());
        descriptor.setStatus(internship.getStatus());
        descriptor.setDate(internship.getDate());
        descriptor.setComment(internship.getComment());
        descriptor.setTags(internship.getTags());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withComment(String comment) {
        descriptor.setComment(new Comment(comment));
        return this;
    }



    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditInternshipDescriptor}
     * that we are building.
     */
    public EditInternshipDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditInternshipDescriptor build() {
        return descriptor;
    }
}
