package vimification.internal.command.macro;

import java.util.Objects;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

/**
 * Adds a new macro to the application.
 */
public class AddMacroCommand extends MacroCommand {

    public static final String COMMAND_KEYWORD = "macro";
    public static final String SUCCESS_MESSAGE = "New macro has been added.";

    private final String macro;
    private final String commandString;

    /**
     * Creates a new instance of {@code AddMacroCommand}.
     *
     * @param macro the new macro to be added
     * @param commandString the string that the macro will expand to
     */
    public AddMacroCommand(String macro, String commandString) {
        this.macro = macro;
        this.commandString = commandString;
    }

    @Override
    public CommandResult execute(MacroMap macroMap) {
        macroMap.put(macro, commandString);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddMacroCommand)) {
            return false;
        }
        AddMacroCommand otherCommand = (AddMacroCommand) other;
        return Objects.equals(macro, otherCommand.macro)
                && Objects.equals(commandString, otherCommand.commandString);
    }
}
