package seedu.modtrek.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setCode(module.getCode());
        descriptor.setCredit(module.getCredit());
        descriptor.setSemYear(module.getSemYear());
        descriptor.setGrade(module.getGrade());
        descriptor.setTags(module.getTags());
    }

    /**
     * Sets the {@code Code} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withCode(String code) {
        descriptor.setCode(new Code(code));
        return this;
    }

    /**
     * Sets the {@code Credit} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withCredit(String credit) {
        descriptor.setCredit(new Credit(credit));
        return this;
    }

    /**
     * Sets the {@code SemYear} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withSemYear(String semYear) {
        descriptor.setSemYear(new SemYear(semYear));
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
