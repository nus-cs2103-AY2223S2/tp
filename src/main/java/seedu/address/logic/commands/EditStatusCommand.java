package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.JobTitle;

/**
 * Edits the status of an application identified using it's displayed index from the list of internship applications.
 */
public class EditStatusCommand extends Command {

    public static final String COMMAND_WORD = "edit_status";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the status the specified application from the list of internships applied.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_STATUS + "STATUS] (must be one of NA, PENDING, RECEIVED, REJECTED or NO)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS + "NA";

    public static final String MESSAGE_UPDATE_STATUS_SUCCESS = "Status of application updated: %1$s";

    private final Index targetIndex;

    private final InternshipStatus toUpdate;

    /**
     * @param targetIndex of the internship application to update status
     * @param internshipStatus Internship status to update
     */
    public EditStatusCommand(Index targetIndex, InternshipStatus internshipStatus) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
        toUpdate = internshipStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToUpdateStatus = lastShownList.get(targetIndex.getZeroBased());
        InternshipApplication updatedApplication = createdUpdatedApplication(internshipToUpdateStatus, toUpdate);

        model.setApplication(internshipToUpdateStatus, updatedApplication);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UPDATE_STATUS_SUCCESS, updatedApplication));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the status of {@code InternshipStatus}
     */
    private static InternshipApplication createdUpdatedApplication(InternshipApplication internshipApplication,
                                                    InternshipStatus status) {
        assert internshipApplication != null;

        CompanyName companyName = internshipApplication.getCompanyName();
        JobTitle jobTitle = internshipApplication.getJobTitle();

        return new InternshipApplication(companyName, jobTitle, status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditStatusCommand // instanceof handles nulls
                && targetIndex.equals(((EditStatusCommand) other).targetIndex)
                && toUpdate.equals(((EditStatusCommand) other).toUpdate)); // state check
    }
}
