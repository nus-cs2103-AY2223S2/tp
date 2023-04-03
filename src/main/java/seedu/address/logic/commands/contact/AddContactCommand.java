package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.documents.Documents;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Location;
import seedu.address.model.person.Note;
import seedu.address.model.person.ProgrammingLanguage;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Reflection;
import seedu.address.model.person.Review;
import seedu.address.model.person.Salary;

/**
 * Adds a contact to an application identified using it's displayed index from the list of internship applications.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "add_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds contact details to the specified application from the list of internships applied.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com ";

    public static final String MESSAGE_ADD_CONTACT_SUCCESS = "Contact details added to application: %1$s";

    private final Index targetIndex;

    private final Contact toAdd;

    /**
     * @param targetIndex of the internship application to add contact details
     * @param contact Contact to add
     */
    public AddContactCommand(Index targetIndex, Contact contact) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
        toAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToAddContact = lastShownList.get(targetIndex.getZeroBased());
        InternshipApplication internshipWithContact = createInternshipWithContact(internshipToAddContact, toAdd);

        model.setApplication(internshipToAddContact, internshipWithContact);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_ADD_CONTACT_SUCCESS, internshipToAddContact + "\n" + toAdd));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code internshipToAddContact}
     * added with the contact {@code toAdd}.
     */
    private static InternshipApplication createInternshipWithContact(InternshipApplication internshipToAddContact,
                                                                     Contact contact) {
        assert internshipToAddContact != null;

        CompanyName companyName = internshipToAddContact.getCompanyName();
        JobTitle jobTitle = internshipToAddContact.getJobTitle();
        Set<Review> reviews = internshipToAddContact.getReviews();
        Set<ProgrammingLanguage> programmingLanguages = internshipToAddContact.getProgrammingLanguages();
        Set<Qualification> qualifications = internshipToAddContact.getQualifications();
        Location location = internshipToAddContact.getLocation();
        Salary salary = internshipToAddContact.getSalary();
        Set<Note> notes = internshipToAddContact.getNotes();
        Rating rating = internshipToAddContact.getRating();
        Set<Reflection> reflections = internshipToAddContact.getReflections();
        InternshipStatus status = internshipToAddContact.getStatus();
        boolean isArchived = internshipToAddContact.isArchived();
        InterviewDate interviewDate = internshipToAddContact.getInterviewDate();
        Documents documents = internshipToAddContact.getDocuments();

        return new InternshipApplication(companyName, jobTitle, reviews, programmingLanguages, qualifications, location,
                salary, notes, rating, reflections, contact, status, isArchived, interviewDate, documents);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && targetIndex.equals(((AddContactCommand) other).targetIndex)
                && toAdd.equals(((AddContactCommand) other).toAdd)); // state check
    }
}
