package seedu.address.logic.commands.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.Comment;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;

/**
 * A StudentCommentCommand class for comment "student CLASSNAME comment"
 */
public class StudentCommentCommand extends StudentCommand {
    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_ADD_COMMENT_SUCCESS = "Added comment to Person: %1$s";
    public static final String MESSAGE_DELETE_COMMENT_SUCCESS = "Removed comment from Person: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment to a person in the address book. "
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "IndexNumber "
            + PREFIX_COMMENT + "Good Student\n"
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + PREFIX_INDEXNUMBER + "1 "
            + PREFIX_COMMENT + "Good Student";

    private final IndexNumber index;
    private final Comment comment;

    private final Class studentClass;
    /**
     * Creates an AddCommand to add the specified {@code Person}
     *
     * @param index of the person in the filtered person list to edit
     * @param comment of the person to be updated to
     */
    public StudentCommentCommand(Class studentClass, IndexNumber index, Comment comment) {
        requireAllNonNull(index, comment);

        this.index = index;
        this.comment = comment;
        this.studentClass = studentClass;
    }

    /**
     * Implements the execution of the command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return  the CommandResult of the command execution
     * @throws CommandException If invalid person index is given
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();
        Student studentToEdit = null;

        for (int i = 0; i < lastShownList.size(); i++) {
            Student curr = lastShownList.get(i);
            if (i == lastShownList.size() - 1 && !curr.getIndexNumber().equals(index)) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            if (curr.getIndexNumber().equals(index) && curr.getStudentClass().equals(studentClass)) {
                studentToEdit = curr;
                break;
            }
        }
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getStudentClass(),
                studentToEdit.getIndexNumber(), studentToEdit.getSex(), studentToEdit.getParentName(),
                studentToEdit.getAge(),
                studentToEdit.getImage(), studentToEdit.getEmail(), studentToEdit.getPhone(),
                studentToEdit.getCca(), studentToEdit.getAddress(), studentToEdit.getAttendance(),
                studentToEdit.getHomework(), studentToEdit.getTest(), studentToEdit.getTags(), comment);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }
    /**
     * Generates a command execution success message based on whether
     * the comment is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !comment.value.isEmpty() ? MESSAGE_ADD_COMMENT_SUCCESS : MESSAGE_DELETE_COMMENT_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StudentCommentCommand)) {
            return false;
        }
        StudentCommentCommand e = (StudentCommentCommand) other;
        return index.equals(e.index)
                && comment.equals((e.comment));
    }

}
