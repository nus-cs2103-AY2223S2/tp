package vimification.internal.command.macro;

import vimification.model.MacroMap;

public class DeleteMacroCommand {

    // :del_macro <macro keyword> ?
    // make it simple LOL

    private final String macro;

    public DeleteMacroCommand(String macro) {
        this.macro = macro;
    }

    public void execute(MacroMap macroMap) {
        macroMap.remove(macro);
    }

}
