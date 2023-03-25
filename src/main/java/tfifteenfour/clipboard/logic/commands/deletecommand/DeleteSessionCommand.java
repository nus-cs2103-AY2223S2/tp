package tfifteenfour.clipboard.logic.commands.deletecommand;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;

/**
 * Deletes a session from the roster.
 */
public class DeleteSessionCommand extends DeleteCommand {

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @param currentSelection of the {@code LogicManager}.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {

        // Command restriction example if on wrong page:
        // if (currentSelection.getCurrentPage() != PageType.COURSE_PAGE) {
        //   throw new CommandException("Wrong page. Navigate to course page to add course");
        // }

        return new CommandResult(this, "test", willModifyState);
    }
}
