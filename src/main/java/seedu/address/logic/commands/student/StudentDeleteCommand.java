package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class StudentDeleteCommand extends StudentCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Deletes the student identified by their index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: student 1A delete " + PREFIX_INDEXNUMBER + "25";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final IndexNumber targetIndex;
    private final Class studentClass;

    /**
     * Creates a StudentDeleteCommand to delete the specified {@code Student}
     */
    public StudentDeleteCommand(IndexNumber targetIndex, Class studentClass) {
        this.targetIndex = targetIndex;
        this.studentClass = studentClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> students = model.getFilteredStudentList();

        for (Student student : students) {
            if (student.getIndexNumber().equals(targetIndex)
                    && student.getStudentClass().equals(studentClass)) {
                model.deleteStudent(student);
                ObservableList<Parent> parents = model.getFilteredParentList();
                setParent(parents, student, model);
                return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, student));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * A method that unbinds the Student's Parent / NOK to the Student
     *
     * @param parents List of existing Parents / NOK.
     * @param student Student that needs the binding of Parent/NOK to.
     * @param model {@code Model} which the command should operate on.
     */
    public void setParent(ObservableList<Parent> parents, Student student, Model model) {
        Phone parentNumber = student.getParentNumber();
        Name parentName = student.getParentName();
        for (Parent p : parents) {
            if ((p.getPhone().equals(parentNumber)) && (p.getName().equals(parentName))) {
                Parent newParent = p;
                newParent.removeStudent(student); //bind student to parent
                model.setParent(p, newParent); //update parent in parents
                return;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((StudentDeleteCommand) other).targetIndex)
                && studentClass.equals(((StudentDeleteCommand) other).studentClass)); // state check
    }
}
