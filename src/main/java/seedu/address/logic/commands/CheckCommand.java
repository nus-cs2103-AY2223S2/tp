package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Creates check command for user to find the respective student's information they want to check.
 */
public class CheckCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public static final String MESSAGE_CHECK_STUDENT_SUCCESS = "Check Student: %1$s";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Check the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Constructs a {@code CheckCommand} with the specified {@code Index}.
     *
     * @param targetIndex The index of the targeted student that going to be checked.
     */
    public CheckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the check command result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> studentList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= studentList.size()) {
            logger.info("The student index is exceeding the total number of students. Index is invalid.");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToCheck = studentList.get(targetIndex.getZeroBased());
        model.checkStudent(studentToCheck);

        logger.info("Check student success.");

        return new CommandResult(String.format(MESSAGE_CHECK_STUDENT_SUCCESS, studentToCheck));
    }

    /**
     * Checks if an object is equal to the check command.
     *
     * @param other The object that need to compare with.
     * @return A boolean value if the object and the check command are the same.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckCommand // instanceof handles nulls
                && targetIndex.equals(((CheckCommand) other).targetIndex)); // state check
    }
}
