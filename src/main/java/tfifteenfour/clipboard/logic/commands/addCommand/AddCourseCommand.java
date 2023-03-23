package tfifteenfour.clipboard.logic.commands.addCommand;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_COURSE;

import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;

public class AddCourseCommand extends AddCommand {
	public static final String COMMAND_TYPE_WORD = "course";
	public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
			+ ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_COURSE + "COURSECODE\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + PREFIX_COURSE + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New course added: %1$s";
    public static final String MESSAGE_DUPLICATE_COURSE = "This course already exists in the roster";

	private final Course courseToAdd;

	public AddCourseCommand(Course course) {
		this.courseToAdd = course;
	}

	public CommandResult execute(Model model) throws CommandException {
		requireNonNull(model);

		if (model.hasCourse(courseToAdd)) {
			throw new CommandException(MESSAGE_DUPLICATE_COURSE);
		}

		model.addCourse(courseToAdd);
		return new CommandResult(this, String.format(MESSAGE_SUCCESS, courseToAdd), willModifyState);
	}

	@Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && courseToAdd.equals(((AddCourseCommand) other).courseToAdd));
    }
}
