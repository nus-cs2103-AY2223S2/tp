package tfifteenfour.clipboard.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.EditCommandParser.EditStudentDescriptor;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Edits the information of a student.
 */
public class EditStudentCommand extends EditCommand {
    public static final String COMMAND_TYPE_WORD = "student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + COMMAND_TYPE_WORD
            + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_STUDENTID + "STUDENTID] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the student roster.";



    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * Constructor for EditStudentCommand.
     * @param index Index of the student in the displayed list to be edited.
     * @param editStudentDescriptor Descriptor containing the new student's details.
     */
    public EditStudentCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = editStudentDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Student> lastShownList = selectedGroup.getUnmodifiableFilteredStudentList();
        List<Session> sessions = selectedGroup.getModifiableSessionList();
        List<Task> tasks = selectedGroup.getModifiableTaskList();

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to edit a student.");
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent)
            && currentSelection.getSelectedGroup().hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }


        for (Session session : sessions) {
            session.replaceStudent(studentToEdit, editedStudent);
        }

        for (Task task : tasks) {
            task.replaceStudent(studentToEdit, editedStudent);
        }

        selectedGroup.setStudent(studentToEdit, editedStudent);
        return new CommandResult(this, String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent), willModifyState);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        StudentId updatedStudentId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());
        Remark remark = studentToEdit.getRemark();

        return new Student(updatedName, updatedPhone, updatedEmail, updatedStudentId,
                remark);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

}
