package vimification.internal.command.macro;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

/**
 * Common class for {@link Command} that modifies the macros in the application.
 */
public abstract class MacroCommand implements Command {

    /**
     * Executes this command.
     *
     * @param macroMap the structure that stores all macros of the application
     * @return a structure that contains relevant information about the execution of this command
     */
    public abstract CommandResult execute(MacroMap macroMap);
}
