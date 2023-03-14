package codoc.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import codoc.logic.commands.EditCommand.EditPersonDescriptor;
import codoc.model.module.Module;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.skill.Skill;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setGithub(person.getGithub());
        descriptor.setEmail(person.getEmail());
        descriptor.setLinkedin(person.getLinkedin());
        descriptor.addSkills(person.getSkills());
        descriptor.addMods(person.getModules());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGithub(String github) {
        descriptor.setGithub(new Github(github));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Linkedin} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withLinkedin(String linkedin) {
        descriptor.setLinkedin(new Linkedin(linkedin));
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.addSkills(skillSet);
        return this;
    }
    /**
     * Parses the {@code modules} into a {@code Set<Module>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withModules(String... modules) {
        Set<Module> moduleSet = Stream.of(modules).map(Module::new).collect(Collectors.toSet());
        descriptor.addMods(moduleSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
