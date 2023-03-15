package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Deletes a group from the address book.
 */
public class GroupDeleteCommand extends GroupCommand {
    public static final String SUB_COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD
            + " : Deletes the group identified by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted group: %1$s";

    private final Index targetIndex;

    public GroupDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = model.getFilteredGroupList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group groupToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteGroup(groupToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((GroupDeleteCommand) other).targetIndex));
    }
}
