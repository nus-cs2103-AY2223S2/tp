package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.model.Model;

/**
 * Views a student in the student list.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the selected item at the index number in the current displayed list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS_BACK_TO_COURSE = "Back to course page";
    public static final String MESSAGE_SUCCESS_BACK_TO_GROUP = "Back to group page of %s";
    public static final String MESSAGE_SUCCESS_BACK_TO_SESSION = "Back to session page of %s";
    public static final String MESSAGE_SUCCESS_BACK_TO_TASK = "Back to task page of %s";

    private CurrentSelection currentSelection;

    /**
     * Creates a BackCommand to select a student at the specified index
     */
    public BackCommand(CurrentSelection currentSelection) {
        super(true);
        this.currentSelection = currentSelection;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (currentSelection.getCurrentPage()) {
        case COURSE_PAGE: // back not possible
            throw new CommandException("Cannot go back any further");

        case GROUP_PAGE:
            // if you are on group page now, go back to course page
            ShowAllListedPredicate.resetGroupsFilter(currentSelection);
            currentSelection.navigateBackFromGroupPage();
            return new CommandResult(this, String.format(MESSAGE_SUCCESS_BACK_TO_COURSE), willModifyState);

        case STUDENT_PAGE:
            ShowAllListedPredicate.resetStudentsFilter(currentSelection);
            currentSelection.navigateBackFromStudentPage();
            return new CommandResult(this, String.format(MESSAGE_SUCCESS_BACK_TO_GROUP,
                    currentSelection.getSelectedCourse()), willModifyState);

        case SESSION_PAGE:
            ShowAllListedPredicate.resetSessionsFilter(currentSelection);
            currentSelection.navigateBackFromSessionPage();
            return new CommandResult(this, String.format(MESSAGE_SUCCESS_BACK_TO_GROUP,
                    currentSelection.getSelectedCourse()), willModifyState);

        case SESSION_STUDENT_PAGE:
            currentSelection.navigateBackFromSessionStudentPage();
            return new CommandResult(this, String.format(MESSAGE_SUCCESS_BACK_TO_SESSION,
                    currentSelection.getSelectedGroup()), willModifyState);

        case TASK_PAGE:
            ShowAllListedPredicate.resetTasksFilter(currentSelection);
            currentSelection.navigateBackFromTaskPage();
            return new CommandResult(this, String.format(MESSAGE_SUCCESS_BACK_TO_GROUP,
                    currentSelection.getSelectedCourse()), willModifyState);

        case TASK_STUDENT_PAGE:
            currentSelection.navigateBackFromTaskStudentPage();
            return new CommandResult(this, String.format(MESSAGE_SUCCESS_BACK_TO_TASK,
                    currentSelection.getSelectedGroup()), willModifyState);

        default:
            throw new CommandException("Unable to select");
        }
    }
}
