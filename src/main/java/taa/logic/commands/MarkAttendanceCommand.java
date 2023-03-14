package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_WEEK;

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
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "[" + PREFIX_WEEK + "WeekToMark] \n"
            + "Example: " + COMMAND_WORD + " 1 w/1 ";
    private final Index index;
    private final Index week;

    /**
     * Constructor for MarkAttendanceCommand
     * @param index index of student to update
     * @param week week to update
     */
    public MarkAttendanceCommand(Index index, Index week) {
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
        if (studentAtd.isMarkedWeek(this.week.getZeroBased())) {
            return new CommandResult(MESSAGE_DUPLICATE_MARKING);
        }
        studentAtd.markAttendance(this.week.getZeroBased());

        model.updateStudent(studentToEdit);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(SUCCESS_MSG);
    }
}
