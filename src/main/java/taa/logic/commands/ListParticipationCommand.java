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
 * Class to List the participation points of an existing student in the taa.
 */
public class ListParticipationCommand extends Command {
    public static final String COMMAND_WORD = "listPP";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the participation of the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "Example: " + COMMAND_WORD + " 1";
    private final Index index;

    /**
     * Constructor for ListParticipationCommand
     * @param index index of student to update
     */
    public ListParticipationCommand(Index index) {
        this.index = index;
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
        return new CommandResult(studentAtd.listPpString());
    }
}
