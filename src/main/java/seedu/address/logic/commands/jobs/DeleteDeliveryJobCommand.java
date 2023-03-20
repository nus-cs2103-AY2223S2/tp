package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteDeliveryJobCommand extends Command {

    public static final String COMMAND_WORD = "delete_job";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the job identified by the job id.\n"
            + "Parameters: STRING job id\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_JOB_SUCCESS = "Deleted Job: %1$s";

    private final String jobId;

    public DeleteDeliveryJobCommand(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyDeliveryJobSystem jobSystem = model.getDeliveryJobSystem();
        FilteredList<DeliveryJob> list = jobSystem.getDeliveryJobList().filtered(x -> x.getJobId().equals(jobId));

        if (list.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_ID);
        }

        DeliveryJob jobToDelete = list.get(0);
        model.deleteDeliveryJob(jobToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_JOB_SUCCESS, jobToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDeliveryJobCommand // instanceof handles nulls
                && jobId.equals(((DeleteDeliveryJobCommand) other).jobId)); // state check
    }
}
