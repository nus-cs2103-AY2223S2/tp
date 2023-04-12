package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_VALUE;

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
 * Adds a score to a student identified using it's displayed index in the student list.
 */
public class AddScoreCommand extends Command {

    public static final String COMMAND_WORD = "addscore";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a score detail to the specified student. "
            + "Every score must have different date.\n"
            + "Parameters: INDEX (must be a positive number) "
            + PREFIX_SCORE_LABEL + "LABEL "
            + PREFIX_SCORE_VALUE + "VALUE "
            + PREFIX_SCORE_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SCORE_LABEL + "Midterm "
            + PREFIX_SCORE_VALUE + "99.8 "
            + PREFIX_SCORE_DATE + "2012-08-09 ";

    public static final String MESSAGE_SUCCESS = "Added score to Student %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_SCORE = "This score already exists in this student's score list. "
            + "Please recheck the score date entered.";

    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
    private final Index index;
    private final Score toAdd;

    /**
     * Creates an AddScoreCommand to add the specified {@code Score} to a student
     */
    public AddScoreCommand(Index index, Score score) {
        requireNonNull(index);
        requireNonNull(score);
        this.index = index;
        this.toAdd = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.info(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        if (studentToEdit.hasScore(toAdd)) {
            logger.info(MESSAGE_DUPLICATE_SCORE);
            throw new CommandException(MESSAGE_DUPLICATE_SCORE);
        }

        studentToEdit.addScore(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                studentToEdit.getName(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddScoreCommand // instanceof handles nulls
                && toAdd.equals(((AddScoreCommand) other).toAdd))
                && index.equals(((AddScoreCommand) other).index); // state check;
    }
}
