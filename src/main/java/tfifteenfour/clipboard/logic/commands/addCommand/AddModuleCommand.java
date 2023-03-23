package tfifteenfour.clipboard.logic.commands.addCommand;

import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;

public class AddModuleCommand extends AddCommand {
	public CommandResult execute(Model model) throws CommandException {
		return new CommandResult(this, "test", willModifyState);
	}
}
