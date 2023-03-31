package vimification.internal.command.macro;

import java.util.stream.Collectors;

import vimification.internal.command.CommandResult;
import vimification.model.MacroMap;

public class ListMacroCommand extends MacroCommand {

    @Override
    public CommandResult execute(MacroMap macroMap) {
        return new CommandResult(
                macroMap.entrySet().stream().collect(Collectors.toSet()).toString());
    }



}
