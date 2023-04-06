package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Location;
import seedu.address.model.application.Note;
import seedu.address.model.application.ProgrammingLanguage;
import seedu.address.model.application.Qualification;
import seedu.address.model.application.Rating;
import seedu.address.model.application.Reflection;
import seedu.address.model.application.Review;
import seedu.address.model.application.Salary;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.documents.Documents;

/**
 * Edits the contact details of an existing application in the list of internship applications.
 */
public class EditContactCommand extends Command {
    public static final String COMMAND_WORD = "edit_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the contact of the application identified "
            + "by the index number used in the internship application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited contact details for application: %1$s";
    public static final String MESSAGE_CONTACT_NOT_FOUND =
            "No contact added";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditContactDescriptor editContactDescriptor;

    /**
     * @param index of the application in the filtered internship application list to edit
     * @param editContactDescriptor details to edit the contact with
     */
    public EditContactCommand(Index index, EditContactDescriptor editContactDescriptor) {
        requireNonNull(index);
        requireNonNull(editContactDescriptor);

        this.index = index;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToUpdateContact = lastShownList.get(index.getZeroBased());
        if (internshipToUpdateContact.getContact() == null) {
            throw new CommandException(MESSAGE_CONTACT_NOT_FOUND);
        }
        InternshipApplication internshipWithUpdatedContact =
                createInternshipWithUpdatedContact(internshipToUpdateContact, editContactDescriptor);

        model.setApplication(internshipToUpdateContact, internshipWithUpdatedContact);
        return new CommandResult(String.format(MESSAGE_EDIT_CONTACT_SUCCESS, internshipWithUpdatedContact));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code internshipToUpdateContact}
     * and contact edited with {@code editContactDescriptor}.
     */
    private static InternshipApplication createInternshipWithUpdatedContact(
            InternshipApplication internshipToUpdateContact, EditContactDescriptor editContactDescriptor) {
        assert internshipToUpdateContact != null;

        CompanyName companyName = internshipToUpdateContact.getCompanyName();
        JobTitle jobTitle = internshipToUpdateContact.getJobTitle();
        Set<Review> reviews = internshipToUpdateContact.getReviews();
        Set<ProgrammingLanguage> programmingLanguages = internshipToUpdateContact.getProgrammingLanguages();
        Set<Qualification> qualifications = internshipToUpdateContact.getQualifications();
        Location location = internshipToUpdateContact.getLocation();
        Salary salary = internshipToUpdateContact.getSalary();
        Set<Note> notes = internshipToUpdateContact.getNotes();
        Rating rating = internshipToUpdateContact.getRating();
        Set<Reflection> reflections = internshipToUpdateContact.getReflections();
        Phone phone = editContactDescriptor.getPhone()
                .orElse(internshipToUpdateContact.getContact().getPhone());
        Email email = editContactDescriptor.getEmail()
                .orElse(internshipToUpdateContact.getContact().getEmail());
        Contact newContact = new Contact(phone, email);
        InternshipStatus status = internshipToUpdateContact.getStatus();
        boolean isArchived = internshipToUpdateContact.isArchived();
        InterviewDate interviewDate = internshipToUpdateContact.getInterviewDate();
        Documents documents = internshipToUpdateContact.getDocuments();

        return new InternshipApplication(companyName, jobTitle, reviews, programmingLanguages, qualifications, location,
                salary, notes, rating, reflections, newContact, status, isArchived, interviewDate, documents);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
        return index.equals(e.index)
                && editContactDescriptor.equals(e.editContactDescriptor);
    }

    /**
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditContactDescriptor {
        private Phone phone;
        private Email email;

        public EditContactDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(phone, email);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditContactDescriptor)) {
                return false;
            }

            // state check
            EditContactDescriptor e = (EditContactDescriptor) other;

            return getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail());
        }
    }
}
