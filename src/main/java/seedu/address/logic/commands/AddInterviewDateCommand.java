package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Review;

/**
 * Adds an interview date to an application identified using it's displayed index from the list of internship
 * applications.
 */
public class AddInterviewDateCommand extends Command {

    public static final String COMMAND_WORD = "add_date";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds interview date to the specified application from the list of internships applied.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] (DATE must be in 'MM/dd/yyyy hh:mm a' format, where a can be AM or PM)"
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "06/07/2023 12:00 PM";


    public static final String MESSAGE_ADD_INTERVIEW_DATE_SUCCESS = "Interview date added to application: %1$s";

    private final Index targetIndex;
    private final InterviewDate toAdd;

    /**
     * @param targetIndex of the internship application to add interview date
     * @param interviewDate InterviewDate to add
     */
    public AddInterviewDateCommand(Index targetIndex, InterviewDate interviewDate) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        toAdd = interviewDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToAddInterviewDate = lastShownList.get(targetIndex.getZeroBased());
        InternshipApplication internshipWithInterviewDate = createInternshipWithInterviewDate(
                internshipToAddInterviewDate, toAdd);

        model.setApplication(internshipToAddInterviewDate, internshipWithInterviewDate);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_ADD_INTERVIEW_DATE_SUCCESS, internshipToAddInterviewDate
                + "\n" + toAdd));
    }

    private static InternshipApplication createInternshipWithInterviewDate(
            InternshipApplication internshipToAddInterviewDate, InterviewDate interviewDate) {
        assert internshipToAddInterviewDate != null;

        CompanyName companyName = internshipToAddInterviewDate.getCompanyName();
        JobTitle jobTitle = internshipToAddInterviewDate.getJobTitle();
        Set<Review> reviews = internshipToAddInterviewDate.getReviews();
        Contact contact = internshipToAddInterviewDate.getContact();
        InternshipStatus status = internshipToAddInterviewDate.getStatus();

        return new InternshipApplication(companyName, jobTitle, reviews, contact, status, interviewDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewDateCommand // instanceof handles nulls
                && targetIndex.equals(((AddInterviewDateCommand) other).targetIndex)
                && toAdd.equals(((AddInterviewDateCommand) other).toAdd)); // state check
    }

}
