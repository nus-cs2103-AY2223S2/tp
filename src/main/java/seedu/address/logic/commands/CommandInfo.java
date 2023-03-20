package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.logic.parser.Prefix;

/**
 * A class representing information about a command, including its command word and a mapping.
 */
public class CommandInfo {
    private final String commandWord;

    private final HashMap<Prefix, String> commandPrompts;

    /**
     * Constructs a new CommandInfo object with the given command word and command prompts.
     *
     * @param commandWord    the word used to invoke the command.
     * @param commandPrompts a mapping of prefixes to prompts for the user.
     */
    public CommandInfo(String commandWord, HashMap<Prefix, String> commandPrompts) {
        this.commandWord = commandWord;
        this.commandPrompts = commandPrompts;
    }

    public String getCmdWord() {
        return commandWord;
    }

    public HashMap<Prefix, String> getCmdPrompts() {
        return commandPrompts;
    }
}
