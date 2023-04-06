package vimification.internal.command.macro;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

public class ListMacroCommand extends MacroCommand {

    @Override
    public CommandResult execute(MacroMap macroMap) {
        return new CommandResult(macroMap.getMapping().toString());
    }
}
