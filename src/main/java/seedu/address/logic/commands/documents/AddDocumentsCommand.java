package seedu.address.logic.commands.documents;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
import seedu.address.model.documents.Documents;

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
            + PREFIX_RESUME + "RESUME "
            + PREFIX_COVER_LETTER + "COVER_LETTER\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RESUME + "https://docs.google.com/document/d/EXAMPLE_RESUME/edit "
            + PREFIX_COVER_LETTER + "https://docs.google.com/document/d/EXAMPLE_COVER_LETTER/edit";

    public static final String MESSAGE_ADD_DOCUMENTS_SUCCESS = "Resume and/or cover letter added to application: %1$s";

    private final Index targetIndex;


    private final Documents toAdd;

    /**
     * @param targetIndex of the internship application to add documents
     * @param documents documents to be added
     */
    public AddDocumentsCommand(Index targetIndex, Documents documents) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
        this.toAdd = documents;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToAddDocuments = lastShownList.get(targetIndex.getZeroBased());
        InternshipApplication internshipWithDocuments = createInternshipWithDocuments(internshipToAddDocuments, toAdd);

        model.setApplication(internshipToAddDocuments, internshipWithDocuments);
        return new CommandResult(String.format(MESSAGE_ADD_DOCUMENTS_SUCCESS, internshipToAddDocuments + "\n" + toAdd));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code internshipToAddDocuments}
     * added with the documents {@code toAdd}.
     */
    private static InternshipApplication createInternshipWithDocuments(InternshipApplication internshipToAddDocuments,
                                                                     Documents documents) {
        assert internshipToAddDocuments != null;

        CompanyName companyName = internshipToAddDocuments.getCompanyName();
        JobTitle jobTitle = internshipToAddDocuments.getJobTitle();
        Set<Review> reviews = internshipToAddDocuments.getReviews();
        Set<ProgrammingLanguage> programmingLanguages = internshipToAddDocuments.getProgrammingLanguages();
        Set<Qualification> qualifications = internshipToAddDocuments.getQualifications();
        Location location = internshipToAddDocuments.getLocation();
        Salary salary = internshipToAddDocuments.getSalary();
        Set<Note> notes = internshipToAddDocuments.getNotes();
        Rating rating = internshipToAddDocuments.getRating();
        Set<Reflection> reflections = internshipToAddDocuments.getReflections();
        Contact contact = internshipToAddDocuments.getContact();
        boolean isArchived = internshipToAddDocuments.isArchived();
        InternshipStatus status = internshipToAddDocuments.getStatus();
        InterviewDate interviewDate = internshipToAddDocuments.getInterviewDate();

        return new InternshipApplication(companyName, jobTitle, reviews, programmingLanguages, qualifications, location,
                salary, notes, rating, reflections, contact, status, isArchived, interviewDate, documents);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDocumentsCommand // instanceof handles nulls
                && targetIndex.equals(((AddDocumentsCommand) other).targetIndex)
                && toAdd.equals(((AddDocumentsCommand) other).toAdd)); // state check
    }
}
