package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;

/**
 * Deletes a student identified using his or her class and index number from PowerConnect.
 */
public class StudentDeleteCommand extends StudentCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Deletes the student identified by their class and index number used.\n"
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER\n"
            + "Example: student 1A delete "
            + PREFIX_INDEXNUMBER + "25";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS =
            "Deleted Student: %1$s; Class: %2$s; Index Number: %3$s;";

    private final IndexNumber targetIndex;
    private final Class studentClass;

    /**
     * Creates a StudentDeleteCommand to delete the specified {@code Student}
     */
    public StudentDeleteCommand(IndexNumber targetIndex, Class studentClass) {
        requireAllNonNull(targetIndex, studentClass);
        this.targetIndex = targetIndex;
        this.studentClass = studentClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //@@author diatbbin-reused
        //Reused from https://github.com/4ndrelim/tp/blob/master/src/main
        // /java/seedu/sudohr/logic/commands/department/DeleteDepartmentCommand.java
        //with modifications
        Student studentToDelete = model.getStudent(targetIndex, studentClass);
        if (studentToDelete == null) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }
        model.deleteStudent(studentToDelete);

        Parent parentToUnbind = model.getParent(studentToDelete.getParentName(), studentToDelete.getParentNumber());

        if (parentToUnbind == null) {
            throw new CommandException(Messages.MESSAGE_PARENT_NOT_FOUND);
        }
        //@@author

        Parent updatedParent = parentToUnbind;
        updatedParent.removeStudent(studentToDelete); //unbind student from parent
        model.setParent(parentToUnbind, updatedParent); //update parent in parentList

        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete.getName(),
                studentToDelete.getStudentClass(), studentToDelete.getIndexNumber()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((StudentDeleteCommand) other).targetIndex)
                && studentClass.equals(((StudentDeleteCommand) other).studentClass)); // state check
    }
}
