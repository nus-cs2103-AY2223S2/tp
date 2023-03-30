package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Sets delivered status for jobs in the delivery job system.
 */
public class CompleteDeliveryJobCommand extends DeliveryJobCommand {

    public static final String COMMAND_WORD_MARK = "com_job";
    public static final String COMMAND_WORD_UNMARK = "uncom_job";

    public static final String MESSAGE_SUCCESS = "Job now %s";

    public static final String MESSAGE_USAGE = "Sets the delivered status as not/complete.\n"
            + "Parameters: KEYWORD [JOB ID]...\n"
            + "Example: " + COMMAND_WORD_MARK + "|" + COMMAND_WORD_UNMARK + " <job_id>";

    private final String jobId;
    private final Boolean setDelivered;

    /**
     * CompleteDeliveryJobCommand。
     *
     * @param jobId
     * @param setDelivered
     */
    public CompleteDeliveryJobCommand(String jobId, Boolean setDelivered) {
        this.jobId = jobId;
        this.setDelivered = setDelivered;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<DeliveryJob> lastShownList = model.getDeliveryJobList();
        Optional<DeliveryJob> deliveryJobToEdit = lastShownList.stream()
                .filter(x -> x.getJobId().equals(jobId))
                .findAny();

        if (deliveryJobToEdit.isPresent()) {
            DeliveryJob.Builder toEdit = new DeliveryJob.Builder().copy(deliveryJobToEdit.get());
            toEdit.setDeliveredStatus(setDelivered);
            model.setDeliveryJob(deliveryJobToEdit.get(), toEdit.build());
            return new CommandResult(String.format(MESSAGE_SUCCESS, setDelivered ? "completed" : "not completed"));
        } else {
            return new CommandResult(Messages.MESSAGE_INVALID_JOB_ID);
        }
    }
}
