package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DELIVERY_JOBS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all jobs in the delivery job system to the user.
 */
public class ListDeliveryJobCommand extends DeliveryJobCommand {

    public static final String COMMAND_WORD = "list_job";

    public static final String MESSAGE_SUCCESS = "Listed all jobs";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDeliveryJobList(PREDICATE_SHOW_ALL_DELIVERY_JOBS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, false, false, false, true, false);
    }
}
