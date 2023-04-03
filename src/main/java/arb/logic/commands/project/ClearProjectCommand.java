package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;

/**
 * Clears the project list of the address book.
 */
public class ClearProjectCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Project list has been cleared!";

    private static final String MAIN_COMMAND_WORD = "clear-project";
    private static final String ALIAS_COMMAND_WORD = "cp";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (currentListBeingShown != ListType.PROJECT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_PROJECT);
        }

        model.resetProjectList();

        return new CommandResult(MESSAGE_SUCCESS, ListType.PROJECT);
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }
}
