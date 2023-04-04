package taa.logic.commands;
import taa.model.Model;

/**
 * Changes the remark of an existing student in the class list.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from remark");
    }
}
