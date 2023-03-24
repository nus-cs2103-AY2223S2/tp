package tfifteenfour.clipboard.logic.commands.deleteCommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;

public class DeleteStudentCommand extends DeleteCommand {
	public static final String COMMAND_TYPE_WORD = "student";
	public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
			+ ": Deletes a students."
            + "Parameters: "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + "1";

    public static final String MESSAGE_SUCCESS = "Student deleted in %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This student already exists in the group";

	private final Index index;

	public DeleteStudentCommand(Index index) {
		this.index = index;
	}

	public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
		requireNonNull(model);

		if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
			throw new CommandException("Wrong page. Navigate to student page to delete student");
		}

		Group selectedGroup = currentSelection.getSelectedGroup();
		List<Student> lastShownList = selectedGroup.getUnmodifiableStudentList();

		if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

		Student studentToDelete = lastShownList.get(index.getZeroBased());

		return new CommandResult(this, String.format(MESSAGE_SUCCESS, studentToDelete, selectedGroup), willModifyState);
	}

	@Override
    public boolean equals(Object other) {
        return other == this;
    }
}
