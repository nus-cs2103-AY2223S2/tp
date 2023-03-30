package vimification.internal.command.macro;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

public class DeleteMacroCommand extends MacroCommand {

    private final String macro;

    public DeleteMacroCommand(String macro) {
        this.macro = macro;
    }

    public CommandResult execute(MacroMap macroMap) {
        macroMap.remove(macro);
        return new CommandResult("Your macro has been removed");
    }
}
