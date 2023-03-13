package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.score.Score;

/**
 * Deletes a score identified using it's displayed index a specified person's score list.
 */
public class DeleteScoreCommand extends Command {

    public static final String COMMAND_WORD = "deleteScore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the score identified by the index number used in the score list of the person specified.\n"
            + "Parameters: INDEX_OF_STUDENT INDEX_OF_SCORE (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_SCORE_SUCCESS = "Deleted Score for %1$s: %2$s";

    private final Index studentIndex;
    private final Index scoreIndex;

    /**
     * Creates a DeleteScoreCommand to delete the specified task from a specified person
     */
    public DeleteScoreCommand(Index studentIndex, Index scoreIndex) {
        this.studentIndex = studentIndex;
        this.scoreIndex = scoreIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDeleteScore = lastShownList.get(studentIndex.getZeroBased());

        List<Score> lastShownScoreList = personToDeleteScore.getFilteredScoreList();
        if (scoreIndex.getZeroBased() >= lastShownScoreList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCORE_DISPLAYED_INDEX);
        }

        Score scoreToDelete = lastShownScoreList.get(scoreIndex.getZeroBased());
        personToDeleteScore.removeScore(scoreToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_SCORE_SUCCESS,
                personToDeleteScore.getName(), scoreToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteScoreCommand // instanceof handles nulls
                && studentIndex.equals(((DeleteScoreCommand) other).studentIndex)
                && scoreIndex.equals(((DeleteScoreCommand) other).scoreIndex)); // state check
    }
}
