package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 Likes to swim.";


    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to student: %1$s, Remark: %2$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from student: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the student in the filtered student list to add a remark
     * @param remark that will be added to the student
     */
    public RemarkCommand(Index index, Remark remark) {
        super(true);
        requireAllNonNull(index, remark);
        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Student> lastShownList = selectedGroup.getUnmodifiableFilteredStudentList();
        List<Session> sessions = selectedGroup.getModifiableSessionList();
        List<Task> tasks = selectedGroup.getModifiableTaskList();


        if (index.getZeroBased() >= lastShownList.size() || index.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getStudentId(), remark);


        for (Session session : sessions) {
            session.replaceStudent(studentToEdit, editedStudent);
        }

        for (Task task : tasks) {
            task.replaceStudent(studentToEdit, editedStudent);
        }

        selectedGroup.setStudent(studentToEdit, editedStudent);
        return new CommandResult(this, generateSuccessMessage(editedStudent), true);
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, studentToEdit.getName().fullName, studentToEdit.getRemark().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
