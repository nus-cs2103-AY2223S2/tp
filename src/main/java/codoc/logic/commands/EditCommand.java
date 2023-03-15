package codoc.logic.commands;

import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_NEW;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_OLD;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_NEW;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_OLD;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;
import static codoc.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import codoc.commons.util.CollectionUtil;
import codoc.logic.commands.exceptions.CommandException;
import codoc.model.Model;
import codoc.model.module.Module;
import codoc.model.person.Course;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
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
            + "[" + PREFIX_LINKEDIN + "LINKEDIN] "
            + "[" + PREFIX_SKILL_ADD + "SKILL] "
            + "[" + PREFIX_SKILL_DELETE + "SKILL] "
            + "[" + PREFIX_SKILL_OLD + "SKILL " + PREFIX_SKILL_NEW + "SKILL]...\n"
            + "[" + PREFIX_MOD_ADD + "MOD] "
            + "[" + PREFIX_MOD_DELETE + "MOD] "
            + "[" + PREFIX_MOD_OLD + "MOD " + PREFIX_MOD_NEW + "MOD]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GITHUB + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    public static final String MESSAGE_NOT_EDITED = "The person was not edited.";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "This person already exists in the address book.";
    public static final String MESSAGE_SKILL_DOES_NOT_EXIST =
            "There is a skill you are trying to delete/update that does not exist";
    public static final String MESSAGE_MOD_DOES_NOT_EXIST =
            "There is a module you are trying to delete/update that does not exist";
    public static final String MESSAGE_INCORRECT_OLD_NEW_SKILL_PREFIX =
            "To update existing skills, the prefixes so/ and sn/ must be present";
    public static final String MESSAGE_INCORRECT_OLD_NEW_MOD_PREFIX =
            "To update existing modules, the prefixes mo/ and mn/ must be present";
    public static final String MESSAGE_UNEQUAL_OLD_NEW_SKILLS =
            "The number of old skills not equal to number of new skills";
    public static final String MESSAGE_UNEQUAL_OLD_NEW_MODS =
            "The number of old modules not equal to number of new modules";
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
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit = model.getProtagonist();
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (personToEdit.equals(editedPerson)
                || (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson))) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.setProtagonist(editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Github updatedGithub = editPersonDescriptor.getGithub().orElse(personToEdit.getGithub());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Course updatedCourse = editPersonDescriptor.getCourse().orElse(personToEdit.getCourse());
        Year updatedYear = editPersonDescriptor.getYear().orElse(personToEdit.getYear());
        Linkedin updatedLinkedin = editPersonDescriptor.getLinkedin().orElse(personToEdit.getLinkedin());
        Set<Skill> updatedSkills = editPersonDescriptor.getSkills().orElse(personToEdit.getSkills());
        Set<Module> updatedModules = editPersonDescriptor.getModules().orElse(personToEdit.getModules());

        return new Person(
                updatedName,
                updatedCourse,
                updatedYear,
                updatedGithub,
                updatedEmail,
                updatedLinkedin,
                updatedSkills,
                updatedModules

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
        private Name name;
        private Github github;
        private Email email;
        private Course course;
        private Year year;
        private Linkedin linkedin;
        private Set<Skill> skills;
        private Set<Module> modules;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * @param person The person that we want to edit.
         */
        public EditPersonDescriptor(Person person) {
            this.name = person.getName();
            this.github = person.getGithub();
            this.email = person.getEmail();
            this.course = person.getCourse();
            this.year = person.getYear();
            this.linkedin = person.getLinkedin();
            this.skills = person.getSkills();
            this.modules = person.getModules();

        }

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setGithub(toCopy.github);
            setEmail(toCopy.email);
            setYear(toCopy.year);
            setCourse(toCopy.course);
            setLinkedin(toCopy.linkedin);
            setSkills(toCopy.skills);
            setModules(toCopy.modules);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, github, email, linkedin, skills, modules);
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
         * Sets {@code skills} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Module> modules) {
            this.modules = (modules != null) ? new HashSet<>(modules) : null;
        }

        /**
         * Adds {@code skills} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void addSkills(Set<Skill> skills) {
            Set<Skill> set = new HashSet<>();
            if (skills != null && this.skills != null) {
                set.addAll(this.skills);
                set.addAll(skills);
                this.skills = set;
            } else {
                this.skills = skills;
            }
        }

        /**
         * Deletes {@code skills} from this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void deleteSkills(Set<Skill> skills) {
            if (skills != null && skills.size() == 0) {
                this.skills = skills;
            } else if (skills != null && this.skills != null) {
                if (hasSkills(skills)) {
                    deleteSkillsHelper(skills);
                } else {
                    this.skills = null;
                }
            }
        }

        private void deleteSkillsHelper(Set<Skill> skills) {
            Set<Skill> result = new HashSet<>();
            for (Skill s : this.skills) {
                if (!skills.contains(s)) {
                    result.add(s);
                }
            }
            this.skills = result;
        }

        /**
         * Updates {@code skills} from this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void updateSkills(Set<Skill> oldSkills, Set<Skill> newSkills) {
            if (oldSkills != null && newSkills != null && this.skills != null) {
                if (hasSkills(oldSkills)) {
                    updateSkillsHelper(oldSkills, newSkills);
                } else {
                    this.skills = null;
                }
            }
        }

        private void updateSkillsHelper(Set<Skill> oldSkills, Set<Skill> newSkills) {
            Set<Skill> result = new HashSet<>();
            for (Skill s : this.skills) {
                if (oldSkills.contains(s)) {
                    Skill newSkill = newSkills.iterator().next();
                    result.add(newSkill);
                    newSkills.remove(newSkill);
                } else {
                    result.add(s);
                }
            }
            this.skills = result;
        }

        private boolean hasSkills(Set<Skill> oldSkills) {
            for (Skill s : oldSkills) {
                if (!this.skills.contains(s)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Adds {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void addMods(Set<Module> modules) {
            Set<Module> set = new HashSet<>();
            if (modules != null && this.modules != null) {
                set.addAll(this.modules);
                set.addAll(modules);
                System.out.println(set);
                this.modules = set;
            } else {
                this.modules = modules;
            }
        }
        //edit m+/AY2223S1 CS2108

        /**
         * Deletes {@code modules} from this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void deleteMods(Set<Module> modules) {
            if (modules != null && modules.size() == 0) {
                this.modules = modules;
            } else if (modules != null && this.modules != null) {
                if (hasMods(modules)) {
                    deleteModsHelper(modules);
                } else {
                    this.modules = null;
                }
            }
        }

        private void deleteModsHelper(Set<Module> modules) {
            Set<Module> result = new HashSet<>();
            for (Module m : this.modules) {
                if (!modules.contains(m)) {
                    result.add(m);
                }
            }
            this.modules = result;
        }

        /**
         * Updates {@code modules} from this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void updateMods(Set<Module> oldMods, Set<Module> newMods) {
            if (oldMods != null && newMods != null && this.modules != null) {
                if (hasMods(oldMods)) {
                    updateModsHelper(oldMods, newMods);
                } else {
                    this.modules = null;
                }
            }
        }

        private void updateModsHelper(Set<Module> oldMods, Set<Module> newMods) {
            Set<Module> result = new HashSet<>();
            for (Module m : this.modules) {
                if (oldMods.contains(m)) {
                    Module newMod = newMods.iterator().next();
                    result.add(newMod);
                    newMods.remove(newMod);
                } else {
                    result.add(m);
                }
            }
            this.modules = result;
        }

        private boolean hasMods(Set<Module> oldMods) {
            for (Module m : oldMods) {
                if (!this.modules.contains(m)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable module list, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
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

            return getName().equals(e.getName())
                    && getGithub().equals(e.getGithub())
                    && getEmail().equals(e.getEmail())
                    && getCourse().equals(e.getCourse())
                    && getYear().equals(e.getYear())
                    && getLinkedin().equals(e.getLinkedin())
                    && getSkills().equals(e.getSkills())
                    && getModules().equals(e.getModules());
        }
    }
}
