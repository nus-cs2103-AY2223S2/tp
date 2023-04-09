package seedu.internship.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.internship.logic.commands.FindCommand.FilterInternshipDescriptor;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Description;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class FilterInternshipDescriptorBuilder {

    private FilterInternshipDescriptor descriptor;

    public FilterInternshipDescriptorBuilder() {
        descriptor = new FilterInternshipDescriptor();
    }

    public FilterInternshipDescriptorBuilder(FilterInternshipDescriptor descriptor) {
        this.descriptor = new FilterInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship}'s details
     */
    public FilterInternshipDescriptorBuilder(Internship internship) {
        descriptor = new FilterInternshipDescriptor();
        descriptor.setPosition(internship.getPosition());
        descriptor.setCompany(internship.getCompany());
        descriptor.setStatus(internship.getStatus());
        descriptor.setDescription(internship.getDescription());
        descriptor.setTags(internship.getTags());
    }

    /**
     * Sets the {@code Position} of the {@code EditInternshipDescriptor} that we are building.
     */
    public FilterInternshipDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code EditInternshipDescriptor} that we are building.
     */
    public FilterInternshipDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditInternshipDescriptor} that we are building.
     */
    public FilterInternshipDescriptorBuilder withStatus(Integer status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditInternshipDescriptor} that we are building.
     */
    public FilterInternshipDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public FilterInternshipDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public FilterInternshipDescriptor build() {
        return descriptor;
    }
}



