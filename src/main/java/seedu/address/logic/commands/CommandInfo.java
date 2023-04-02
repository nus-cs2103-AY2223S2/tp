package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.commons.util.CheckedFunction;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

/**
 * A class representing information about a command, including its command word and a mapping.
 */
public class CommandInfo {
    private final String commandWord;

    private final HashMap<Prefix, String> commandPrompts;

    private final CheckedFunction<ArgumentMultimap, Boolean> commandValidator;

    private boolean requiresPreamble = false;

    public boolean isRequiresPreamble() {
        return requiresPreamble;
    }

    /**
     * Constructs a new CommandInfo object with the given command word and command prompts.
     *
     * @param commandWord      the word used to invoke the command.
     * @param commandPrompts   a mapping of prefixes to prompts for the user.
     * @param commandValidator validator used to validate user args.
     */
    public CommandInfo(String commandWord, HashMap<Prefix, String> commandPrompts,
                       CheckedFunction<ArgumentMultimap, Boolean> commandValidator) {
        this.commandWord = commandWord;
        this.commandPrompts = commandPrompts;
        this.commandValidator = commandValidator;
    }

    public CommandInfo(String commandWord, HashMap<Prefix, String> commandPrompts,
                       CheckedFunction<ArgumentMultimap, Boolean> commandValidator, boolean requiresPreamble) {

        this(commandWord, commandPrompts, commandValidator);
        this.requiresPreamble = requiresPreamble;

    }

    public String getCmdWord() {
        return commandWord;
    }

    public HashMap<Prefix, String> getCmdPrompts() {
        return commandPrompts;
    }

    public CheckedFunction<ArgumentMultimap, Boolean> getCmdValidator() {
        return commandValidator;
    }
}
