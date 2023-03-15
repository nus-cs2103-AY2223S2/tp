package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.student.Attendance;
import taa.model.student.Student;

/**
 * Unmarks the attendance of an existing student in the address book.
 */
public class UnmarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "unmarkAtd";
    public static final String SUCCESS_MSG = "Attendance unmarked successfully!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance of the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "[" + PREFIX_WEEK + "WeekToMark] \n"
            + "Example: " + COMMAND_WORD + " 1 w/1 ";
    private final Index index;
    private final Index week;

    /**
     * Constructor for UnarkAttendanceCommand
     * @param index index of student to update
     * @param week week to update
     */
    public UnmarkAttendanceCommand(Index index, Index week) {
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

        studentAtd.unmarkAttendance(this.week.getZeroBased());

        model.updateStudent(studentToEdit);
        return new CommandResult(SUCCESS_MSG);
    }
}
