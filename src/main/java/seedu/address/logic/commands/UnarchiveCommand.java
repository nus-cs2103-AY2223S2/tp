package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ONGOING_APPLICATIONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
 * Unarchives an internship application identified using it's displayed index from the list of internship applications.
 */
public class UnarchiveCommand extends Command {

    public static final String COMMAND_WORD = "unarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives the specified application from the list of internships applied.\n"
            + "Unarchives the application of internship at the specified INDEX.\n"
            + "The index refers to the index number shown in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer 1, 2, 3, ...)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_UNARCHIVE_APPLICATION_SUCCESS = "Unarchived Application: %1$s";
    public static final String MESSAGE_APPLICATION_NOT_ARCHIVED = "Application is not currently archived!";

    private final Index targetIndex;

    /**
     * Creates an UnarchiveCommand to unarchive the specified {@code targetIndex} internship
     *
     * @param targetIndex of the internship application to unarchive
     */
    public UnarchiveCommand(Index targetIndex) {
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

        InternshipApplication internshipToUnarchive = lastShownList.get(targetIndex.getZeroBased());

        if (!internshipToUnarchive.isArchived()) {
            throw new CommandException(MESSAGE_APPLICATION_NOT_ARCHIVED);
        }

        InternshipApplication archivedApplication = createdUnarchivedApplication(internshipToUnarchive);

        model.setApplication(internshipToUnarchive, archivedApplication);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ONGOING_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_UNARCHIVE_APPLICATION_SUCCESS, archivedApplication));
    }

    /**
     * Creates and returns an archived {@code InternshipApplication}
     */
    private static InternshipApplication createdUnarchivedApplication(InternshipApplication internshipApplication) {
        assert internshipApplication != null;

        CompanyName companyName = internshipApplication.getCompanyName();
        JobTitle jobTitle = internshipApplication.getJobTitle();
        Set<Review> reviews = internshipApplication.getReviews();
        Set<ProgrammingLanguage> programmingLanguages = internshipApplication.getProgrammingLanguages();
        Set<Qualification> qualifications = internshipApplication.getQualifications();
        Location location = internshipApplication.getLocation();
        Salary salary = internshipApplication.getSalary();
        Set<Note> notes = internshipApplication.getNotes();
        Rating rating = internshipApplication.getRating();
        Set<Reflection> reflections = internshipApplication.getReflections();
        Contact contact = internshipApplication.getContact();
        InternshipStatus status = internshipApplication.getStatus();
        InterviewDate interviewDate = internshipApplication.getInterviewDate();
        Documents documents = internshipApplication.getDocuments();

        return new InternshipApplication(companyName, jobTitle, reviews, programmingLanguages, qualifications, location,
                salary, notes, rating, reflections, contact, status, false, interviewDate,
                documents);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnarchiveCommand // instanceof handles nulls
                && targetIndex.equals(((UnarchiveCommand) other).targetIndex)); // state check
    }
}
