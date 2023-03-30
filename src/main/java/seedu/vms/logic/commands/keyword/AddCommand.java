package seedu.vms.logic.commands.keyword;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.core.Messages.MESSAGE_EXISTING_KEYWORD_EXISTS;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_KEYWORD_MAIN;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_KEYWORD_SUB;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.keyword.KeywordManager;

/**
 * Adds a keyword to the patient manager.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_GROUP = "keyword";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Adds a keyword to the keyword manager. "
            + "Parameters: "
            + DELIMITER + PREFIX_KEYWORD_MAIN + " MAIN KEYWORD "
            + DELIMITER + PREFIX_KEYWORD_SUB + " SUB KEYWORD ";

    public static final String MESSAGE_SUCCESS = "New keyword added: %1$s";
    public static final String MESSAGE_DUPLICATE_KEYWORD = "This keyword already exists in the keyword manager";

    private final Keyword toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Keyword}
     */
    public AddCommand(Keyword key) {
        requireNonNull(key);
        toAdd = key;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        String keyword = toAdd.getKeyword();
        if (KeywordManager.existingMappingExists(keyword)) {
            throw new CommandException(String.format(MESSAGE_EXISTING_KEYWORD_EXISTS, keyword, keyword));
        }
        model.addKeyword(toAdd);
        return new CommandMessage(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.vms.logic.commands.keyword.AddCommand // instanceof handles nulls
                && toAdd.equals(((seedu.vms.logic.commands.keyword.AddCommand) other).toAdd));
    }
}
