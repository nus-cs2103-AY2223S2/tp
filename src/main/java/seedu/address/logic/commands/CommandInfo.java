package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.function.Function;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

/**
 * A class representing information about a command, including its command word and a mapping.
 */
public class CommandInfo {
    private final String commandWord;

    private final HashMap<Prefix, String> commandPrompts;

    private final Function<ArgumentMultimap, Boolean> commandValidator;

    /**
     * Constructs a new CommandInfo object with the given command word and command prompts.
     *
     * @param commandWord      the word used to invoke the command.
     * @param commandPrompts   a mapping of prefixes to prompts for the user.
     * @param commandValidator validator used to validate user args.
     */
    public CommandInfo(String commandWord, HashMap<Prefix, String> commandPrompts,
                       Function<ArgumentMultimap, Boolean> commandValidator) {
        this.commandWord = commandWord;
        this.commandPrompts = commandPrompts;
        this.commandValidator = commandValidator;
    }

    public String getCmdWord() {
        return commandWord;
    }

    public HashMap<Prefix, String> getCmdPrompts() {
        return commandPrompts;
    }

    public Function<ArgumentMultimap, Boolean> getCmdValidator() {
        return commandValidator;
    }
}
