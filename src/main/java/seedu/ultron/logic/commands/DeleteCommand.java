package seedu.ultron.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.model.Model.PREDICATE_SHOW_ALL_OPENINGS;

import java.util.List;

import seedu.ultron.commons.core.Messages;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.model.Model;
import seedu.ultron.model.opening.Opening;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the opening identified by the index number used in the displayed opening list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_OPENING_SUCCESS = "Deleted Opening: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Opening> lastShownList = model.getFilteredOpeningList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_OPENING_DISPLAYED_INDEX);
        }

        Opening openingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOpening(openingToDelete);
        if (model.hasSelectedIndex() && model.getSelectedIndex().equals(targetIndex)) {
            model.setSelectedIndex(null);
        }
        model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        model.setSelectedIndex(null);
        return new CommandResult(String.format(MESSAGE_DELETE_OPENING_SUCCESS, openingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
