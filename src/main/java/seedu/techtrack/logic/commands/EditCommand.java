package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.techtrack.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.commons.util.CollectionUtil;
import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;


/**
 * Edits the details of an existing role in the role book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the role "
            + "by the index number used in the displayed role list. "
            + "Existing values will be overwritten by the input values.\n \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_CONTACT + "CONTACT] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_COMPANY + "ADDRESS] "
            + "[" + PREFIX_JOBDESCRIPTION + "JOBDESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_WEBSITE + "WEBSITE] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_EXPERIENCE + "EXPERIENCE] \n \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_WEBSITE + "www.google.com "
            + PREFIX_CONTACT + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_JOBDESCRIPTION + "AWS Cloud Infrastructure team "
            + PREFIX_SALARY + "4000 "
            + PREFIX_DEADLINE + "2023-10-20 "
            + PREFIX_EXPERIENCE + "Javascript - 1 Year";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Role: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This role already exists in the company book.";

    private final Index index;
    private final EditRoleDescriptor editRoleDescriptor;

    /**
     * @param index              of the role in the filtered role list to edit
     * @param editRoleDescriptor details to edit the role with
     */
    public EditCommand(Index index, EditRoleDescriptor editRoleDescriptor) {
        requireNonNull(index);
        requireNonNull(editRoleDescriptor);

        this.index = index;
        this.editRoleDescriptor = new EditRoleDescriptor(editRoleDescriptor);
    }

    @Override
    public CommandResult<String> execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Role> lastShownList = model.getFilteredRoleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Role roleToEdit = lastShownList.get(index.getZeroBased());
        Role editedRole = createEditedRole(roleToEdit, editRoleDescriptor);

        if (!roleToEdit.isSameRole(editedRole) && model.hasRole(editedRole)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setRole(roleToEdit, editedRole);
        model.updateFilteredRoleList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult<>(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedRole));
    }

    /**
     * Creates and returns a {@code Role} with the details of {@code roleToEdit}
     * edited with {@code editRoleDescriptor}.
     */
    private static Role createEditedRole(Role roleToEdit, EditRoleDescriptor editRoleDescriptor) {
        assert roleToEdit != null;

        Website updatedWebsite = editRoleDescriptor.getWebsite().orElse(roleToEdit.getWebsite());
        Name updatedName = editRoleDescriptor.getName().orElse(roleToEdit.getName());
        Contact updatedContact = editRoleDescriptor.getPhone().orElse(roleToEdit.getContact());
        Email updatedEmail = editRoleDescriptor.getEmail().orElse(roleToEdit.getEmail());
        Company updatedCompany = editRoleDescriptor.getCompany().orElse(roleToEdit.getCompany());
        JobDescription updatedJd = editRoleDescriptor.getJobDescription().orElse(roleToEdit.getJobDescription());
        Set<Tag> updatedTags = editRoleDescriptor.getTags().orElse(roleToEdit.getTags());
        Salary updatedSalary = editRoleDescriptor.getSalary().orElse(roleToEdit.getSalary());
        Deadline updatedDeadline = editRoleDescriptor.getDeadline().orElse(roleToEdit.getDeadline());
        Experience updatedExperience = editRoleDescriptor.getExperience().orElse(roleToEdit.getExperience());
        return new Role(updatedName, updatedContact, updatedEmail, updatedCompany, updatedJd, updatedTags,
                updatedWebsite, updatedSalary, updatedDeadline, updatedExperience);
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
        return index.equals(e.index)
                && editRoleDescriptor.equals(e.editRoleDescriptor);
    }

    /**
     * Stores the details to edit the role with. Each non-empty field value will replace the
     * corresponding field value of the role.
     */
    public static class EditRoleDescriptor {
        private Name name;
        private Contact contact;
        private Email email;
        private Company company;
        private JobDescription jd;
        private Set<Tag> tags;
        private Website website;
        private Salary salary;
        private Deadline deadline;

        private Experience experience;

        public EditRoleDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRoleDescriptor(EditRoleDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.contact);
            setEmail(toCopy.email);
            setCompany(toCopy.company);
            setJobDescription(toCopy.jd);
            setTags(toCopy.tags);
            setWebsite(toCopy.website);
            setSalary(toCopy.salary);
            setDeadline(toCopy.deadline);
            setExperience(toCopy.experience);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, contact, email, company, tags, website, jd,
                    salary, deadline, experience);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Contact contact) {
            this.contact = contact;
        }

        public Optional<Contact> getPhone() {
            return Optional.ofNullable(contact);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setJobDescription(JobDescription jd) {
            this.jd = jd;
        }

        public Optional<JobDescription> getJobDescription() {
            return Optional.ofNullable(jd);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public void setWebsite(Website website) {
            this.website = website;
        }

        public Optional<Website> getWebsite() {
            return Optional.ofNullable(website);
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setExperience(Experience experience) {
            this.experience = experience;
        }

        public Optional<Experience> getExperience() {
            return Optional.ofNullable(experience);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRoleDescriptor)) {
                return false;
            }

            // state check
            EditRoleDescriptor e = (EditRoleDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getCompany().equals(e.getCompany())
                    && getTags().equals(e.getTags())
                    && getWebsite().equals(e.getWebsite())
                    && getJobDescription().equals(e.getJobDescription())
                    && getTags().equals(e.getTags())
                    && getSalary().equals(e.getSalary())
                    && getDeadline().equals(e.getDeadline())
                    && getExperience().equals(e.getExperience());
        }
    }
}
