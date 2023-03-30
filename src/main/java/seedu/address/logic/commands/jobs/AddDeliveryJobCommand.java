package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Adds a delivery job to the delivery job system.
 */
public class AddDeliveryJobCommand extends DeliveryJobCommand {

    public static final String COMMAND_WORD = "add_job";

    public static final String MESSAGE_SENDER_CONSTRAINT = "Sender ID should contain "
            + "numeric and alphabetical characters."
            + "\nAnd it should not be blank.";

    public static final String MESSAGE_RECIPIENT_CONSTRAINT = "Recipient ID should contain numeric"
            + "and alphabetical characters"
            + "\nand it should not be blank.";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the delivery job system. "
            + "\nParameters: "
            + PREFIX_SENDER_ID + "SENDER ID "
            + PREFIX_RECIPIENT_ID + "RECIPIENT ID ["
            + PREFIX_DELIVERY_DATE + "DELIVERY DATE] ["
            + PREFIX_DELIVERY_SLOT + "DELIVERY SLOT] ["
            + PREFIX_EARNING + "Earning]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SENDER_ID + "ALE874 "
            + PREFIX_RECIPIENT_ID + "DAV910 "
            + PREFIX_DELIVERY_DATE + "2023-03-03 "
            + PREFIX_DELIVERY_SLOT + "3 "
            + PREFIX_EARNING + "20";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_SUCCESS_WITH_NO_DATE = "New job added, with"
            + " unspecified delivery date: \n%1$s";
    public static final String MESSAGE_SUCCESS_WITH_NO_SLOT = "New job added, "
            + "with unspecified delivery slot: \n%1$s";
    public static final String MESSAGE_SUCCESS_WITH_MISSING_2_ARGS = "New job added, with unspecified "
            + "%s and %s: \n%s";
    public static final String MESSAGE_SUCCESS_LATE_SLOT = "New job added with "
            + "slot outside of working hours: \n%1$s"
            + "\nValid slot should only be in the range [1,5]";
    public static final String MESSAGE_SUCCESS_LATE_SLOT_NO_DATE = "New job added with: "
            + "\nslot outside of working hours and unspecified delivery date: \n%1$s"
            + "\nValid slot should only be in the range [1,5]";

    public static final String MESSAGE_SUCCESS_LATE_SLOT_NO_EARN = "New job added with: "
            + "\nslot outside of working hours and unspecified earning: \n%1$s"
            + "\nValid slot should only be in the range [1,5]";
    public static final String MESSAGE_SUCCESS_LATE_SLOT_NO_DATE_EARN = "New job added with: "
            + "\nslot outside of working hours, unspecified delivery date and earning: \n%1$s"
            + "\nValid slot should only be in the range [1,5]";


    public static final String MESSAGE_SUCCESS_WITH_NO_EARN = "New job added with unspecified earning: \n%1$s";
    public static final String MESSAGE_SUCCESS_WITH_NO_DATE_SLOT_EARN = "New job added with unspecified"
            + " delivery date, slot and earning: \n%1$s";


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

        if (toAdd.hasInvalidSlot()) {
            if (toAdd.hasEarning() && toAdd.hasDate()) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_LATE_SLOT, toAdd));
            } else if (toAdd.hasEarning() && (!toAdd.hasDate())) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_LATE_SLOT_NO_DATE, toAdd));
            } else if ((!toAdd.hasEarning()) && toAdd.hasDate()) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_LATE_SLOT_NO_EARN, toAdd));
            } else {
                return new CommandResult(String.format(MESSAGE_SUCCESS_LATE_SLOT_NO_DATE_EARN, toAdd));
            }
        }

        return getMessageForMissingArgs(toAdd.hasDate(), toAdd.hasSlot(), toAdd.hasEarning());
    }

    private CommandResult getMessageForMissingArgs(boolean hasDate, boolean hasSlot, boolean hasEarn) {
        if (hasDate && hasSlot && hasEarn) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }

        int numberOfMissingArgs = 0;
        if (!hasDate) {
            numberOfMissingArgs++;
        }
        if (!hasSlot) {
            numberOfMissingArgs++;
        }
        if (!hasEarn) {
            numberOfMissingArgs++;
        }

        if (numberOfMissingArgs == 1) {
            if (!hasDate) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_NO_DATE, toAdd));
            } else if (!hasEarn) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_NO_EARN, toAdd));
            } else if (!hasSlot) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_NO_SLOT, toAdd));
            }
        } else if (numberOfMissingArgs == 2) {
            if ((!hasDate) && (!hasSlot)) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_MISSING_2_ARGS,
                        "delivery date", "delivery slot", toAdd));
            } else if ((!hasDate) && (!hasEarn)) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_MISSING_2_ARGS,
                        "delivery date", "earning", toAdd));
            } else if ((!hasSlot) && (!hasEarn)) {
                return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_MISSING_2_ARGS,
                        "delivery slot", "earning", toAdd));
            }
        } else if (numberOfMissingArgs == 3) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_NO_DATE_SLOT_EARN, toAdd));

        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeliveryJobCommand // instanceof handles nulls
                && toAdd.equals(((AddDeliveryJobCommand) other).toAdd));
    }
}
