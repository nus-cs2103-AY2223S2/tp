package seedu.address.logic.commands.resume;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobTitle;

/**
 * Adds links to a resume and/or cover letter to an application identified using it's displayed index
 * from the list of internship applications.
 */
public class AddDocumentsCommand extends Command {

    public static final String COMMAND_WORD = "add_docs";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds link to a resume and/or cover letter to the specified application from the "
            + "list of internships applied.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RESUME + "RESUME] "
            + "[" + PREFIX_COVER_LETTER + "COVER_LETTER] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RESUME + "https://docs.google.com/document/d/EXAMPLE_RESUME/edit "
            + PREFIX_COVER_LETTER + "https://docs.google.com/document/d/EXAMPLE_COVER_LETTER/edit";

    public static final String MESSAGE_ADD_RESUME_SUCCESS = "Resume and/or cover letter added to application: %1$s";

    private final Index targetIndex;

    private final String resumeLink;
    private final String coverLetterLink;

    /**
     * @param targetIndex of the internship application to add contact details
     * @param resumeLink resume to add
     * @param coverLetterLink cover letter to add
     */
    public AddDocumentsCommand(Index targetIndex, String resumeLink, String coverLetterLink) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
        this.resumeLink = resumeLink;
        this.coverLetterLink = coverLetterLink;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToAddDocuments = lastShownList.get(targetIndex.getZeroBased());
        InternshipApplication internshipWithContact = createInternshipWithContact(internshipToAddContact, toAdd);

        model.setApplication(internshipToAddContact, internshipWithContact);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_ADD_RESUME_SUCCESS, internshipToAddContact + "\n" + toAdd));
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
        InterviewDate interviewDate = internshipToAddContact.getInterviewDate();

        return new InternshipApplication(companyName, jobTitle, contact, interviewDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDocumentsCommand // instanceof handles nulls
                && targetIndex.equals(((AddDocumentsCommand) other).targetIndex)
                && toAdd.equals(((AddDocumentsCommand) other).toAdd)); // state check
    }
}
