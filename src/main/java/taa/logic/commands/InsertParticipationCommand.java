package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_PARTICIPATION_POINTS;
import static taa.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.student.Attendance;
import taa.model.student.Student;

/**
 * Class to insert participation points to an existing student in taa
 * for a specified week
 */
public class InsertParticipationCommand extends Command {
    public static final String COMMAND_WORD = "insertPP";
    public static final String SUCCESS_MSG = "Participation points inserted successfully!";
    public static final String ATTENDANCE_NOT_MARKED = "Mark the attendance of the student "
            + "first before inserting points!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Inserts attendance points to the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "[" + PREFIX_WEEK + "WeekToMark] \n"
            + "[" + PREFIX_PARTICIPATION_POINTS + "ParticipationPoints] \n"
            + "Example: " + COMMAND_WORD + " 1 w/1 pp/200";
    private final Index index;
    private final Index week;

    private final int points;

    /**
     * Constructor for MarkAttendanceCommand
     * @param index index of student to update
     * @param week week to update
     */
    public InsertParticipationCommand(Index index, Index week, int points) {
        this.index = index;
        this.week = week;
        this.points = points;
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
        if (!studentAtd.isMarkedWeek(this.week.getZeroBased())) {
            return new CommandResult(ATTENDANCE_NOT_MARKED);
        }

        studentAtd.insertParticipationPoints(this.week.getZeroBased(), this.points);

        model.updateStudent(studentToEdit);
        return new CommandResult(SUCCESS_MSG);
    }
}
