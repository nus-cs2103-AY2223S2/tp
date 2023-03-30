package vimification.internal.command.macro;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

public abstract class MacroCommand implements Command {

    public abstract CommandResult execute(MacroMap macroMap);
}
