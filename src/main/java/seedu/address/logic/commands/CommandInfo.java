package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.logic.parser.Prefix;

public class CommandInfo {
    private final String commandWord;

    private final HashMap<Prefix, String> commandPrompts;

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
