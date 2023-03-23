package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelected;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;

/**
 * Views a student in the student list.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the selected item at the index number in the current displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
	private final CurrentSelected currentSelected;

    /**
     * Creates a SelectCommand to select a student at the specified index
     */
    public SelectCommand(Index targetIndex, CurrentSelected currentSelected) {
        super(false);
        this.targetIndex = targetIndex;
		this.currentSelected = currentSelected;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (currentSelected.getCurrenPage()) {
        case COURSE_PAGE:
            List<Course> courseList = model.getUnmodifiableFilteredCourseList();
            Course selectedCourse = courseList.get(targetIndex.getZeroBased());
            currentSelected.setSelectedCourse(selectedCourse);
            return new CommandResult(this, String.format("Viewing: %s", selectedCourse), willModifyState);
        case GROUP_PAGE:
            break;
        case STUDENT_PAGE:
            break;
        case SESSION_PAGE:
            break;
        case SESSION_STUDENT_PAGE:
            break;
        default:
            break;
        }
        return new CommandResult(this, "Placeholder commandresult. Code should not reach this point." , willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SelectCommand)) {
            return false;
        }

        // state check
        SelectCommand e = (SelectCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}
