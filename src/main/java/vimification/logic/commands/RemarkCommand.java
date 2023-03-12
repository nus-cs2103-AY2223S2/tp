package vimification.logic.commands;

import vimification.model.Model;

public class RemarkCommand extends Command {

    public static String COMMAND_WORD = "remark";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from remark");
    }
}
