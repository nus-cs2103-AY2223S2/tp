package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Deletes a job identified using it's displayed index from the job system.
 */
public class DeleteDeliveryJobCommand extends DeliveryJobCommand {

    public static final String COMMAND_WORD = "delete_job";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the job identified by the job ID.\n"
            + "Parameters: STRING job ID\n"
            + "Example: " + COMMAND_WORD + " ALBE29E66F\n"
            + "Tip: you can copy the job ID by\n"
            + "     selecting the job and press\n"
            + "     Ctrl+C\n"
            + "Tip: you can delete job directly by\n"
            + "     selecting and press DEL key";
    public static final String MESSAGE_DELETE_JOB_SUCCESS = "Deleted Job: %1$s";

    private final String jobId;

    /**
     * Constructs a DeleteDeliveryJobCommand
     * @param jobId of which job needs to be deleted
     */
    public DeleteDeliveryJobCommand(String jobId) {
        requireNonNull(jobId);
        this.jobId = jobId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<DeliveryJob> lastShownList = model.getDeliveryJobList();
        Optional<DeliveryJob> deliveryJobToDelete = lastShownList.stream()
                .filter(x -> x.getJobId().equals(jobId))
                .findAny();

        if (deliveryJobToDelete.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_ID);
        }

        model.deleteDeliveryJob(deliveryJobToDelete.get());
        return new CommandResult(String.format(MESSAGE_DELETE_JOB_SUCCESS, deliveryJobToDelete.get().getJobId()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDeliveryJobCommand // instanceof handles nulls
                && jobId.equals(((DeleteDeliveryJobCommand) other).jobId)); // state check
    }
}
