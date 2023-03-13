package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.student.Attendance;
import taa.model.student.Student;

/**
 * Marks the attendance of an existing student in the address book.
 */
public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "markAtd";
    public static final String SUCCESS_MSG = "Attendance marked successfully!";
    public static final String MESSAGE_DUPLICATE_MARKING = "This student's attendance has already been marked.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";
    private final Index index;
    private final int week;

    public MarkAttendanceCommand(Index index, int week) {
        this.index = index;
        this.week = week;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Attendance studentAtd = studentToEdit.getAtd();
        if (studentAtd.isMarkedWeek(this.week)) {
            return new CommandResult(MESSAGE_DUPLICATE_MARKING);
        }
        studentAtd.markAttendance(this.week);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(SUCCESS_MSG);
    }
}
