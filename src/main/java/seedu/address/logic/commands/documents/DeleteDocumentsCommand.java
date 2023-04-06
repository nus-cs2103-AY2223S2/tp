package seedu.address.logic.commands.documents;

import static java.util.Objects.requireNonNull;

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

/**
 * Delete links to the resume and/or cover letter of an application identified using it's displayed index
 * from the list of internship applications.
 */
public class DeleteDocumentsCommand extends Command {

    public static final String COMMAND_WORD = "delete_docs";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes link to the resume and/or cover letter of the specified application from the "
            + "list of internships applied.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DOCUMENTS_NOT_FOUND =
            "No resume and/or cover letter added";

    public static final String MESSAGE_DELETE_DOCUMENTS_SUCCESS =
            "Resume and/or cover letter deleted from application: %1$s";

    private final Index targetIndex;

    /**
     * @param targetIndex of the internship application to delete documents
     */
    public DeleteDocumentsCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToDeleteDocuments = lastShownList.get(targetIndex.getZeroBased());
        if (internshipToDeleteDocuments.getDocuments() == null) {
            throw new CommandException(MESSAGE_DOCUMENTS_NOT_FOUND);
        }
        InternshipApplication internshipWithDocumentsDeleted =
                createInternshipWithDocumentsDeleted(internshipToDeleteDocuments);

        model.setApplication(internshipToDeleteDocuments, internshipWithDocumentsDeleted);
        return new CommandResult(String.format(MESSAGE_DELETE_DOCUMENTS_SUCCESS, internshipToDeleteDocuments));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code internshipToDeleteDocuments}
     * and documents removed.
     */
    private static InternshipApplication createInternshipWithDocumentsDeleted(
            InternshipApplication internshipToDeleteDocuments) {
        assert internshipToDeleteDocuments != null;

        CompanyName companyName = internshipToDeleteDocuments.getCompanyName();
        JobTitle jobTitle = internshipToDeleteDocuments.getJobTitle();
        Set<Review> reviews = internshipToDeleteDocuments.getReviews();
        Set<ProgrammingLanguage> programmingLanguages = internshipToDeleteDocuments.getProgrammingLanguages();
        Set<Qualification> qualifications = internshipToDeleteDocuments.getQualifications();
        Location location = internshipToDeleteDocuments.getLocation();
        Salary salary = internshipToDeleteDocuments.getSalary();
        Set<Note> notes = internshipToDeleteDocuments.getNotes();
        Rating rating = internshipToDeleteDocuments.getRating();
        Set<Reflection> reflections = internshipToDeleteDocuments.getReflections();
        Contact contact = internshipToDeleteDocuments.getContact();
        InternshipStatus status = internshipToDeleteDocuments.getStatus();
        boolean isArchived = internshipToDeleteDocuments.isArchived();
        InterviewDate interviewDate = internshipToDeleteDocuments.getInterviewDate();

        return new InternshipApplication(companyName, jobTitle, reviews, programmingLanguages, qualifications, location,
                salary, notes, rating, reflections, contact, status, isArchived, interviewDate, null);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDocumentsCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDocumentsCommand) other).targetIndex));
    }
}
