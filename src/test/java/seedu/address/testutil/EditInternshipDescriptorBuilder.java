package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Location;
import seedu.address.model.application.Note;
import seedu.address.model.application.ProgrammingLanguage;
import seedu.address.model.application.Qualification;
import seedu.address.model.application.Rating;
import seedu.address.model.application.Reflection;
import seedu.address.model.application.Review;
import seedu.address.model.application.Salary;

/**
 * A utility class to help with building EditPersonDescriptor objects.
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
    public EditInternshipDescriptorBuilder(InternshipApplication internship) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setCompanyName(internship.getCompanyName());
        descriptor.setJobTitle(internship.getJobTitle());
        descriptor.setLocation(internship.getLocation());
        descriptor.setSalary(internship.getSalary());
        descriptor.setRating(internship.getRating());
        descriptor.setQualifications(internship.getQualifications());
        descriptor.setProgrammingLanguages(internship.getProgrammingLanguages());
        descriptor.setReviews(internship.getReviews());
        descriptor.setNotes(internship.getNotes());
        descriptor.setReflections(internship.getReflections());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withCompanyName(String name) {
        descriptor.setCompanyName(new CompanyName(name));
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditJobTitleDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Parses the {@code qualifications} into a {@code Set<Qualification>} and set it to the
     * {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withQualifications(String... qualifications) {
        Set<Qualification> qualificationSet = Stream.of(qualifications).map(Qualification::new)
                                                                                    .collect(Collectors.toSet());
        descriptor.setQualifications(qualificationSet);
        return this;
    }

    /**
     * Parses the {@code programming languages} into a {@code Set<ProgrammingLanguage>} and set it to the
     * {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withProgrammingLanguages(String... programmingLanguages) {
        Set<ProgrammingLanguage> programmingLanguageSet = Stream.of(programmingLanguages).map(ProgrammingLanguage::new)
                .collect(Collectors.toSet());
        descriptor.setProgrammingLanguages(programmingLanguageSet);
        return this;
    }

    /**
     * Parses the {@code reviews} into a {@code Set<Review>} and set it to the
     * {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withReviews(String... reviews) {
        Set<Review> reviewSet = Stream.of(reviews).map(Review::new)
                .collect(Collectors.toSet());
        descriptor.setReviews(reviewSet);
        return this;
    }

    /**
     * Parses the {@code notes} into a {@code Set<Note>} and set it to the
     * {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withNotes(String... notes) {
        Set<Note> noteSet = Stream.of(notes).map(Note::new)
                .collect(Collectors.toSet());
        descriptor.setNotes(noteSet);
        return this;
    }

    /**
     * Parses the {@code reflections} into a {@code Set<Reflection>} and set it to the
     * {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withReflections(String... reflections) {
        Set<Reflection> reflectionSet = Stream.of(reflections).map(Reflection::new)
                .collect(Collectors.toSet());
        descriptor.setReflections(reflectionSet);
        return this;
    }

    public EditInternshipDescriptor build() {
        return descriptor;
    }
}
