package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;

/**
 * AddDeliveryJobCommand.
 */
public class AddDeliveryJobCommand extends Command {

    public static final String COMMAND_WORD = "add_job";

    // public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the delivery job system. "
    //         + "Parameters: "
    //         + PREFIX_SENDER_ID + "SENDER ID "
    //         + PREFIX_RECEPIENT_ID + "RECEPIENT ID "
    //         + PREFIX_DELIVERY_DATE + "DELIVERY DATE "
    //         + PREFIX_DELIVERY_SLOT + "DELIVERY SLOT "
    //         + PREFIX_EARNING + "Earning ";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This job already exists in the delivery job system";

    private final DeliveryJob toAdd;

    /**
     * Creates an AddDeliveryJobCommand to add the specified {@code DeliveryJob}
     */
    public AddDeliveryJobCommand(DeliveryJob job) {
        requireNonNull(job);
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addDeliveryJob(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeliveryJobCommand // instanceof handles nulls
                && toAdd.equals(((AddDeliveryJobCommand) other).toAdd));
    }
}
