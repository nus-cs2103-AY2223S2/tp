package tfifteenfour.clipboard.logic.commands.deleteCommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;

public class DeleteCourseCommand extends DeleteCommand {
	public static final String COMMAND_TYPE_WORD = "course";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ " " + COMMAND_TYPE_WORD
			+ ": Deletes a course and ALL its students."
			+ "Parameters: "
			+ "COURSE_CODE\n"
			+ "Example: " + COMMAND_WORD
			+ " " + COMMAND_TYPE_WORD
			+ " " + "CS2103T ";

	public static final String MESSAGE_SUCCESS = "Course course deleted: %1$s";

	private final Course courseToDelete;

	public DeleteCourseCommand(Course course) {
		this.courseToDelete= course;
	}

	public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
		requireNonNull(model);

		if (currentSelection.getCurrentPage() != PageType.COURSE_PAGE) {
			throw new CommandException("Wrong page. Navigate to course page to add course");
		}

		model.deleteCourse(courseToDelete);
		return new CommandResult(this, String.format(MESSAGE_SUCCESS, courseToDelete), willModifyState);
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof DeleteCourseCommand // instanceof handles nulls
				&& courseToDelete.equals(((DeleteCourseCommand) other).courseToDelete));
	}
}
