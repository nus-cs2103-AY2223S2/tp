package vimification.internal.command.macro;

import vimification.model.MacroMap;

public class CreateMacroCommand {

    // :add_macro <keyword> "command"/'command'/command

    private final String macro;
    private final String command;

    public CreateMacroCommand(String macro, String command) {
        this.macro = macro;
        this.command = command;
    }

    public void execute(MacroMap macroMap) {
        macroMap.put(macro, command);
    }
}
