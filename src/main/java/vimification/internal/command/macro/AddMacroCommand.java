package vimification.internal.command.macro;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

public class AddMacroCommand extends MacroCommand {

    // :add_macro <keyword> "command"/'command'/command

    private final String macro;
    private final String command;

    public AddMacroCommand(String macro, String command) {
        this.macro = macro;
        this.command = command;
    }

    public CommandResult execute(MacroMap macroMap) {
        macroMap.put(macro, command);
        return new CommandResult("New macro has been added");
    }
}
