package tfifteenfour.clipboard.logic.commands.addCommand;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;

public class AddSessionCommand extends AddCommand {

	public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {

		// Command restriction example if on wrong page:
		// if (currentSelection.getCurrentPage() != PageType.COURSE_PAGE) {
		// 	throw new CommandException("Wrong page. Navigate to course page to add course");
		// }

		return new CommandResult(this, "test", willModifyState);
	}

}
