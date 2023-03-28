package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

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

    public static final String MESSAGE_SENDER_CONSTRAINT = "Sender ID should contain "
            + "numeric and alphabetical characters\"\n"
            + "+ \"and it should not be blank.\\n\"\n";

    public static final String MESSAGE_RECIPIENT_CONSTRAINT = "Recipient ID should contain numeric"
            + "and alphabetical characters\"\n"
            + "            + \"and it should not be blank.\\n\"\n";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the delivery job system. "
            + "Parameters: "
            + PREFIX_SENDER_ID + "SENDER ID "
            + PREFIX_RECIPIENT_ID + "RECIPIENT ID "
            + PREFIX_DELIVERY_DATE + "DELIVERY DATE "
            + PREFIX_DELIVERY_SLOT + "DELIVERY SLOT "
            + PREFIX_EARNING + "Earning ";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the delivery job system";
    public static final String MESSAGE_INVALID_SENDER = "Sender not found.";
    public static final String MESSAGE_INVALID_RECIPIENT = "Recipient not found.";
    public static final String MESSAGE_SAME_SENDER_RECIPIENT = "Sender and Recipient cannot be the same.";

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

        if (toAdd.getRecipientId().isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_RECIPIENT);
        }

        if (model.getPersonById(toAdd.getRecipientId()).isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_RECIPIENT);
        }

        if (toAdd.getSenderId().isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_SENDER);
        }

        if (model.getPersonById(toAdd.getSenderId()).isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_SENDER);
        }

        if (toAdd.getSenderId().equals(toAdd.getRecipientId())) {
            throw new CommandException(MESSAGE_SAME_SENDER_RECIPIENT);
        }

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
