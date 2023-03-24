package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;

/**
 * Views a student in the student list.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the selected item at the index number in the current displayed list.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Creates a BackCommand to select a student at the specified index
     */
    public BackCommand() {
        super(false);
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        switch (currentSelection.getCurrentPage()) {
        case COURSE_PAGE: // back not possible
			throw new CommandException("Cannot go back any further");

        case GROUP_PAGE:
            // if you are on group page now, go back to course page
			currentSelection.navigateBackFromGroupPage();
            return new CommandResult(this, String.format("Back to course page"), willModifyState);

        case STUDENT_PAGE:
            currentSelection.navigateBackFromStudentPage();
            return new CommandResult(this, String.format("Back to group page of %s",
					currentSelection.getSelectedCourse()), willModifyState);
        default:
            throw new CommandException("Unable to select");
        }
    }
}
