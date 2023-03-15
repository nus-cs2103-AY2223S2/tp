package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

public class DeleteSingleIndexCommand extends DeleteCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteSingleIndexCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
}
