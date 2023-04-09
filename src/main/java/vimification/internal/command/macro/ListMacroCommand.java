package vimification.internal.command.macro;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

/**
 * Shows all macros that is currently registered in the application.
 */
public class ListMacroCommand extends MacroCommand {

    /**
     * Creates a new {@code ListMacroCommand} instance.
     */
    public ListMacroCommand() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(MacroMap macroMap) {
        return new CommandResult(macroMap.getMapping().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListMacroCommand;
    }
}
