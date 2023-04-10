package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.score.Score;
import seedu.address.model.student.Student;

/**
 * Deletes a score identified using it's displayed index a specified student's score list.
 */
public class DeleteScoreCommand extends Command {

    public static final String COMMAND_WORD = "deletescore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the score identified by the index number used in the score list of "
            + "the student specified.\n"
            + "Parameters: INDEX_OF_STUDENT INDEX_OF_SCORE (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_SCORE_SUCCESS = "Deleted score for %1$s: %2$s";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
    private final Index studentIndex;
    private final Index scoreIndex;

    /**
     * Creates a DeleteScoreCommand to delete the specified score from a specified student
     */
    public DeleteScoreCommand(Index studentIndex, Index scoreIndex) {
        requireAllNonNull(studentIndex, scoreIndex);
        this.studentIndex = studentIndex;
        this.scoreIndex = scoreIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            logger.info(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDeleteScore = lastShownList.get(studentIndex.getZeroBased());

        List<Score> lastShownScoreList = studentToDeleteScore.getFilteredScoreList();
        if (scoreIndex.getZeroBased() >= lastShownScoreList.size()) {
            logger.info(Messages.MESSAGE_INVALID_SCORE_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_SCORE_DISPLAYED_INDEX);
        }

        Score scoreToDelete = lastShownScoreList.get(scoreIndex.getZeroBased());
        studentToDeleteScore.removeScore(scoreToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_SCORE_SUCCESS,
                studentToDeleteScore.getName(), scoreToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteScoreCommand // instanceof handles nulls
                && studentIndex.equals(((DeleteScoreCommand) other).studentIndex)
                && scoreIndex.equals(((DeleteScoreCommand) other).scoreIndex)); // state check
    }
}
