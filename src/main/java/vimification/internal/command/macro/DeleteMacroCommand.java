package vimification.internal.command.macro;

import java.util.Objects;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

/**
 * Deletes a macro from the application.
 */
public class DeleteMacroCommand extends MacroCommand {

    public static final String COMMAND_KEYWORD = "macro";
    public static final String SUCCESS_MESSAGE_FORMAT = "Macro [%s] has been removed.";

    private final String macro;

    /**
     * Creates a new instance of {@code DeleteMacroCommand}.
     *
     * @param macro the macro to be deleted
     */
    public DeleteMacroCommand(String macro) {
        this.macro = macro;
    }

    @Override
    public CommandResult execute(MacroMap macroMap) {
        macroMap.remove(macro);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, macro));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteMacroCommand)) {
            return false;
        }
        DeleteMacroCommand otherCommand = (DeleteMacroCommand) other;
        return Objects.equals(macro, otherCommand.macro);
    }
}
