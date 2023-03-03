package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.NricMatchesKeywordsPredicate;

/**
 * Deletes an elderly identified using its NRIC from the FriendlyLink database.
 */
public class DeleteElderlyCommand extends Command {
    public static final String COMMAND_WORD = "delete_elderly";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the elderly identified by the nric linked with the elderly.\n"
            + "Parameters: NRIC (must be a valid NRIC number)\n"
            + "Example: " + COMMAND_WORD + " S1234567C";

    public static final String MESSAGE_DELETE_ELDERLY_SUCCESS = "Deleted Elderly: %1$s";

    private final String targetNric;

    public DeleteElderlyCommand(String targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // first filter with NRIC, then get filtered list
        NricMatchesKeywordsPredicate predicate =
                new NricMatchesKeywordsPredicate(targetNric);
        model.updateFilteredElderlyList(predicate);
        List<Elderly> lastShownList = model.getFilteredElderlyList();

        // should asset lastShownList.size() = 0 or 1, cannot be > 1

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NRIC_NOT_EXIST);
        }
        Elderly elderlyToDelete = lastShownList.get(0);
        model.deleteElderly(elderlyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ELDERLY_SUCCESS, elderlyToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteElderlyCommand
                && targetNric.equals(((DeleteElderlyCommand) other).targetNric));
    }

}
