package vimification.logic.commands;

import vimification.logic.commands.exceptions.CommandException;
import vimification.model.Model;

public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_SUCCESS = "Created a remark";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
