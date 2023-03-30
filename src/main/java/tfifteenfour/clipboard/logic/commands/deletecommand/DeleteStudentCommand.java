package tfifteenfour.clipboard.logic.commands.deletecommand;

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
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Deletes a student from the roster.
 */
public class DeleteStudentCommand extends DeleteCommand {
    public static final String COMMAND_TYPE_WORD = "student";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Deletes a students."
            + "Parameters: "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + "1";

    public static final String MESSAGE_SUCCESS = "Deleted student in %1$s: %2$s";

    private final Index index;

    public DeleteStudentCommand(Index index) {
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to delete student");
        }

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Student> lastShownList = selectedGroup.getUnmodifiableFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(index.getZeroBased());
        Name studentNameToDelete = studentToDelete.getName();
        selectedGroup.deleteStudent(studentToDelete);

        return new CommandResult(this,
                String.format(MESSAGE_SUCCESS, selectedGroup, studentNameToDelete),
                willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
