package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.logic.parser.CliSyntax;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.person.Person;

/**
 * Marks a person with status.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark delivery status for a person. "
            + "Parameters: INDEX (must be a positive integer) "
            + CliSyntax.PREFIX_STATUS + "STATUS (pending/otw/done/failed/return)";
    public static final String MESSAGE_RETURN_STATUS_CHANGED = "Cannot change status of a return delivery!";
    public static final String MESSAGE_DONE_STATUS_CHANGED = "Cannot change status of a done delivery!";
    public static final String MESSAGE_SUCCESS = "Marked %1$s's delivery as: %2$s";

    private final Index targetIndex;
    private final DeliveryStatus newStatus;

    /**
     * Creates mark command.
     * @param targetIndex the index of the Person to modify
     * @param newStatus the new status for the Person
     */
    public MarkCommand(Index targetIndex, DeliveryStatus newStatus) {
        requireNonNull(targetIndex);
        requireNonNull(newStatus);

        this.targetIndex = targetIndex;
        this.newStatus = newStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());

        if (personToMark.getDeliveryStatus() == DeliveryStatus.RETURN) {
            throw new CommandException(MESSAGE_RETURN_STATUS_CHANGED);
        } else if (personToMark.getDeliveryStatus() == DeliveryStatus.DONE) {
            throw new CommandException(MESSAGE_DONE_STATUS_CHANGED);
        }

        int noOfDeliveryAttemptsToSet = personToMark.getNoOfDeliveryAttempts();
        DeliveryStatus deliveryStatusToSet = newStatus;
        if (newStatus == DeliveryStatus.FAILED) {
            noOfDeliveryAttemptsToSet = noOfDeliveryAttemptsToSet + 1;
            if (noOfDeliveryAttemptsToSet == DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN) {
                deliveryStatusToSet = DeliveryStatus.RETURN;
            }
        }

        Person updatedPersonToMark = new Person(personToMark.getName(), personToMark.getPhone(),
                personToMark.getEmail(), personToMark.getAddress(), personToMark.getParcels(),
                deliveryStatusToSet, noOfDeliveryAttemptsToSet);

        model.setPerson(personToMark, updatedPersonToMark);

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedPersonToMark.getName(), newStatus.toString()),
                true, updatedPersonToMark, model.getIndexOf(updatedPersonToMark));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;

        return targetIndex.equals(e.targetIndex) && newStatus.equals(e.newStatus);
    }
}
