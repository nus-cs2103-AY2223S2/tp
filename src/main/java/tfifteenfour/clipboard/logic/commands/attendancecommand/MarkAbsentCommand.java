package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentWithAttendance;


/**
 * Command to mark a student as absent in a selected session.
 */
public class MarkAbsentCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the selected student at the index number as absent. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 or " + COMMAND_WORD + " 1,2,3,4,5";

    public static final String MESSAGE_SUCCESS = "Marked student as absent in session %1$s: \n%2$s";

    private final Index[] targetIndex;

    /**
     * Creates a MarkAbsentCommand to mark the student at the specified index as absent.
     */
    public MarkAbsentCommand(Index... targetIndex) {
        super(true);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() == PageType.SESSION_PAGE) {
            throw new CommandException("Please select a session to start marking attendance.");
        } else if (currentSelection.getCurrentPage() != PageType.SESSION_STUDENT_PAGE) {
            throw new CommandException("Wrong page! Navigate to session page to mark attendance.");
        }

        Session session = currentSelection.getSelectedSession();
        ObservableList<StudentWithAttendance> studentList = session.getUnmodifiableStudentList();
        StringBuilder studentMarked = new StringBuilder();

        for (int i = 0; i < targetIndex.length; i++) {
            if (targetIndex[i].getZeroBased() >= studentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            } else {
                Student studentToMark = studentList.get(targetIndex[i].getZeroBased());
                session.markAbsent(studentToMark);
                studentMarked.append(studentToMark.getName());
                if (i != targetIndex.length - 1) {
                    studentMarked.append(", ");
                }
            }
        }
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, session, studentMarked), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkAbsentCommand)) {
            return false;
        }

        MarkAbsentCommand e = (MarkAbsentCommand) other;
        Arrays.sort(targetIndex);
        Arrays.sort(e.targetIndex);
        return Arrays.equals(targetIndex, e.targetIndex);
    }

}
