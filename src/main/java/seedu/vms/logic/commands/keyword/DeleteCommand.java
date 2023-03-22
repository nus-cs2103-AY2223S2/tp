package seedu.vms.logic.commands.keyword;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.keyword.Keyword;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_GROUP = "keyword";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the keyword list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_KEYWORD_SUCCESS = "Deleted keyword: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        Map<Integer, IdData<Keyword>> keywordList = model.getFilteredKeywordList();

        if (!keywordList.containsKey(targetIndex.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_KEYWORD_DISPLAYED_INDEX);
        }

        Keyword keywordToDelete = keywordList.get(targetIndex.getZeroBased()).getValue();
        model.deleteKeyword(targetIndex.getZeroBased());
        return new CommandMessage(String.format(MESSAGE_DELETE_KEYWORD_SUCCESS, keywordToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.vms.logic.commands.keyword.DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((seedu.vms.logic.commands.keyword.DeleteCommand) other).targetIndex)); // state check
    }
}
