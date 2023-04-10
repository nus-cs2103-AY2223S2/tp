package seedu.vms.logic.commands.keyword;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.core.Messages.MESSAGE_KEYWORD_DOES_NOT_EXIST;

import java.util.Objects;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.keyword.KeywordManager;

/**
 * Deletes a keyword identified using it's displayed index from the keyword manager.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_GROUP = "keyword";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Deletes the keyword identified by the string used in the keyword map.\n"
            + "Parameters: KEYWORD (must be a string)\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " appt";

    public static final String MESSAGE_DELETE_KEYWORD_SUCCESS = "Deleted keyword: %1$s";

    private final String targetKeyword;

    public DeleteCommand(String targetKeyword) {
        this.targetKeyword = Objects.requireNonNull(targetKeyword);
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!KeywordManager.existingMappingExists(targetKeyword)) {
            throw new CommandException(String.format(MESSAGE_KEYWORD_DOES_NOT_EXIST, targetKeyword));
        }
        Keyword keywordToDelete = model.deleteKeyword(targetKeyword);
        return new CommandMessage(String.format(MESSAGE_DELETE_KEYWORD_SUCCESS, keywordToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.vms.logic.commands.keyword.DeleteCommand // instanceof handles nulls
                && targetKeyword.equals(((DeleteCommand) other)
                .targetKeyword)); // state check
    }
}
