package tfifteenfour.clipboard.logic.commands.attendance;

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

import java.util.List;

import static java.util.Objects.requireNonNull;

public class MarkPresentCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the selected student at the index number as absent / present. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Marked student as present: %1$s";

    private final Index targetIndex;

    public MarkPresentCommand(Index targetIndex) {
        super(true);
        this.targetIndex = targetIndex;
    }

    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        if (currentSelection.getCurrentPage() != PageType.SESSION_STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to session page to mark attendance");
        }

        Session session = currentSelection.getSelectedSession();
        List<Student> studentList = session.getUnmodifiableStudentList();
        if (targetIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToMark = studentList.get(targetIndex.getZeroBased());
        session.markPresent(studentToMark);

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, studentToMark), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }
}
