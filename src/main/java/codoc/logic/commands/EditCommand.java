package codoc.logic.commands;

import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;
import static codoc.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static codoc.model.Model.PREDICATE_SHOW_ALL_PERSONS_INPUT;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import codoc.commons.util.CollectionUtil;
import codoc.logic.commands.exceptions.CommandException;
import codoc.model.Model;
import codoc.model.course.Course;
import codoc.model.module.Module;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.person.ProfilePicture;
import codoc.model.person.Year;
import codoc.model.skill.Skill;

/**
 * Edits the details of an existing person in CoDoc.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person in the view panel\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GITHUB + "GITHUB] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_COURSE + "COURSE] "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_LINKEDIN + "LINKEDIN]\n"
            + "[" + PREFIX_SKILL_ADD + "SKILL] "
            + "[" + PREFIX_SKILL_DELETE + "SKILL] "
            + "[" + PREFIX_MOD_ADD + "MOD] "
            + "[" + PREFIX_MOD_DELETE + "MOD]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COURSE + "2 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_MOD_DELETE + "AY2223S1 CS1101S "
            + PREFIX_SKILL_ADD + "python";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    public static final String MESSAGE_INPUT_NO_CHANGE = "The person already has these attributes";

    public static final String MESSAGE_NOT_EDITED = "The person was not edited.";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "This person already exists in the address book.";
    public static final String MESSAGE_SKILL_DOES_NOT_EXIST =
            "There is a skill you are trying to delete/update that does not exist";
    public static final String MESSAGE_MOD_DOES_NOT_EXIST =
            "There is a module you are trying to delete/update that does not exist";
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.getProtagonist();

        if (editPersonDescriptor.getSkillsRemoved().isPresent()) {
            Set<Skill> original = personToEdit.getSkills();
            Set<Skill> edited = editPersonDescriptor.getSkillsRemoved().get();
            if (!original.containsAll(edited)) { // Can be improved by specifying which skill is not present
                throw new CommandException(MESSAGE_SKILL_DOES_NOT_EXIST);
            }
        }

        if (editPersonDescriptor.getModulesRemoved().isPresent()) {
            Set<Module> original = personToEdit.getModules();
            Set<Module> edited = editPersonDescriptor.getModulesRemoved().get();
            if (!original.containsAll(edited)) { // Can be improved by specifying which module is not present
                throw new CommandException(MESSAGE_MOD_DOES_NOT_EXIST);
            }
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (personToEdit.equals(editedPerson)) {
            throw new CommandException(MESSAGE_INPUT_NO_CHANGE);
        } else if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.setProtagonist(editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_PERSONS_INPUT);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        ProfilePicture updatedProfilePicture =
                editPersonDescriptor.getProfilePicture().orElse(personToEdit.getProfilePicture());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Github updatedGithub = editPersonDescriptor.getGithub().orElse(personToEdit.getGithub());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Course updatedCourse = editPersonDescriptor.getCourse().orElse(personToEdit.getCourse());
        Year updatedYear = editPersonDescriptor.getYear().orElse(personToEdit.getYear());
        Linkedin updatedLinkedin = editPersonDescriptor.getLinkedin().orElse(personToEdit.getLinkedin());
        Set<Skill> removedSkills = editPersonDescriptor.getSkillsRemoved().orElse(new HashSet<>());
        Set<Skill> addedSkills = editPersonDescriptor.getSkillsAdded().orElse(new HashSet<>());
        Set<Skill> updatedSkills = new HashSet<>(personToEdit.getSkills()); // Copy off original
        updatedSkills.removeAll(removedSkills); // Remove takes priority
        updatedSkills.addAll(addedSkills);
        Set<Skill> finalSkills = editPersonDescriptor.getSkillsFinal().orElse(updatedSkills);
        Set<Module> removedModules = editPersonDescriptor.getModulesRemoved().orElse(new HashSet<>());
        Set<Module> addedModules = editPersonDescriptor.getModulesAdded().orElse(new HashSet<>());
        Set<Module> updatedModules = new HashSet<>(personToEdit.getModules()); // Copy off original
        updatedModules.removeAll(removedModules); // Remove takes priority
        updatedModules.addAll(addedModules);
        Set<Module> finalModules = editPersonDescriptor.getModulesFinal().orElse(updatedModules);

        return new Person(
                updatedProfilePicture,
                updatedName,
                updatedCourse,
                updatedYear,
                updatedGithub,
                updatedEmail,
                updatedLinkedin,
                finalSkills,
                finalModules
        );
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {

        private ProfilePicture profilePicture;
        private Name name;
        private Github github;
        private Email email;
        private Course course;
        private Year year;
        private Linkedin linkedin;
        private Set<Skill> skillsRemoved;
        private Set<Skill> skillsAdded;
        private Set<Skill> skillsFinal;
        private Set<Module> modulesRemoved;
        private Set<Module> modulesAdded;
        private Set<Module> modulesFinal;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copies of {@code skills} and {@Code modules} are used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setProfilePicture(toCopy.profilePicture);
            setName(toCopy.name);
            setGithub(toCopy.github);
            setEmail(toCopy.email);
            setYear(toCopy.year);
            setCourse(toCopy.course);
            setLinkedin(toCopy.linkedin);
            setSkillsRemoved(toCopy.skillsRemoved);
            setSkillsAdded(toCopy.skillsAdded);
            setSkillsFinal(toCopy.skillsFinal);
            setModulesRemoved(toCopy.modulesRemoved);
            setModulesAdded(toCopy.modulesAdded);
            setModulesFinal(toCopy.modulesFinal);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, github, email, course, year, linkedin, skillsRemoved, skillsAdded,
                    skillsFinal, modulesRemoved, modulesAdded, modulesFinal);
        }

        public void setProfilePicture(ProfilePicture profilePicture) {
            this.profilePicture = profilePicture;
        }

        public Optional<ProfilePicture> getProfilePicture() {
            return Optional.ofNullable(profilePicture);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setGithub(Github github) {
            this.github = github;
        }

        public Optional<Github> getGithub() {
            return Optional.ofNullable(github);
        }
        public void setCourse(Course course) {
            this.course = course;
        }
        public Optional<Course> getCourse() {
            return Optional.ofNullable(course);
        }
        public void setYear(Year year) {
            this.year = year;
        }
        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }
        public void setEmail(Email email) {
            this.email = email;
        }
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setLinkedin(Linkedin linkedin) {
            this.linkedin = linkedin;
        }

        public Optional<Linkedin> getLinkedin() {
            return Optional.ofNullable(linkedin);
        }

        /**
         * Sets {@code skillsRemoved} to this object's {@code skillsRemoved}.
         * A defensive copy of {@code skillsRemoved} is used internally.
         */
        public void setSkillsRemoved(Set<Skill> skillsRemoved) {
            this.skillsRemoved = (skillsRemoved != null) ? new HashSet<>(skillsRemoved) : null;
        }

        /**
         * Sets {@code skillsAdded} to this object's {@code skillsAdded}.
         * A defensive copy of {@code skillsAdded} is used internally.
         */
        public void setSkillsAdded(Set<Skill> skillsAdded) {
            this.skillsAdded = (skillsAdded != null) ? new HashSet<>(skillsAdded) : null;
        }

        /**
         * Sets {@code skillsFinal} to this object's {@code skillsFinal}.
         * A defensive copy of {@code skillsFinal} is used internally.
         */
        public void setSkillsFinal(Set<Skill> skillsFinal) {
            this.skillsFinal = (skillsFinal != null) ? new HashSet<>(skillsFinal) : null;
        }

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModulesRemoved(Set<Module> modulesRemoved) {
            this.modulesRemoved = (modulesRemoved != null) ? new HashSet<>(modulesRemoved) : null;
        }

        /**
         * Sets {@code modulesAdded} to this object's {@code modulesAdded}.
         * A defensive copy of {@code modulesAdded} is used internally.
         */
        public void setModulesAdded(Set<Module> modulesAdded) {
            this.modulesAdded = (modulesAdded != null) ? new HashSet<>(modulesAdded) : null;
        }

        /**
         * Sets {@code modulesFinal} to this object's {@code modulesFinal}.
         * A defensive copy of {@code modulesFinal} is used internally.
         */
        public void setModulesFinal(Set<Module> modulesFinal) {
            this.modulesFinal = (modulesFinal != null) ? new HashSet<>(modulesFinal) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skillsRemoved} is null.
         */
        public Optional<Set<Skill>> getSkillsRemoved() {
            return (skillsRemoved != null) ? Optional.of(Collections.unmodifiableSet(skillsRemoved)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skillsAdded} is null.
         */
        public Optional<Set<Skill>> getSkillsAdded() {
            return (skillsAdded != null) ? Optional.of(Collections.unmodifiableSet(skillsAdded)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skillsFinal} is null.
         */
        public Optional<Set<Skill>> getSkillsFinal() {
            return (skillsFinal != null) ? Optional.of(Collections.unmodifiableSet(skillsFinal)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable module set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modulesRemoved} is null.
         */
        public Optional<Set<Module>> getModulesRemoved() {
            return (modulesRemoved != null) ? Optional.of(Collections.unmodifiableSet(modulesRemoved))
                    : Optional.empty();
        }

        /**
         * Returns an unmodifiable module set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modulesAdded} is null.
         */
        public Optional<Set<Module>> getModulesAdded() {
            return (modulesAdded != null) ? Optional.of(Collections.unmodifiableSet(modulesAdded)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable module set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modulesFinal} is null.
         */
        public Optional<Set<Module>> getModulesFinal() {
            return (modulesFinal != null) ? Optional.of(Collections.unmodifiableSet(modulesFinal)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getProfilePicture().equals(e.getProfilePicture())
                    && getName().equals(e.getName())
                    && getGithub().equals(e.getGithub())
                    && getEmail().equals(e.getEmail())
                    && getCourse().equals(e.getCourse())
                    && getYear().equals(e.getYear())
                    && getLinkedin().equals(e.getLinkedin())
                    && getSkillsRemoved().equals(e.getSkillsRemoved())
                    && getSkillsAdded().equals(e.getSkillsAdded())
                    && getSkillsFinal().equals(e.getSkillsFinal())
                    && getModulesRemoved().equals(e.getModulesRemoved())
                    && getModulesAdded().equals(e.getModulesAdded())
                    && getModulesFinal().equals(e.getModulesFinal());
        }
    }
}
