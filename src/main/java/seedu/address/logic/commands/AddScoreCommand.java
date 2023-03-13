package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE_VALUE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.score.Score;

/**
 * Adds a score to a person identified using it's display index in the address book.
 */
public class AddScoreCommand extends Command {

    public static final String COMMAND_WORD = "addScore";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a score detail to the student. \n"
            + "Parameters: INDEX (must be a positive number) "
            + PREFIX_SCORE_LABEL + "LABEL "
            + PREFIX_SCORE_VALUE + "VALUE "
            + PREFIX_SCORE_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SCORE_LABEL + "Midterm "
            + PREFIX_SCORE_VALUE + "99.8 "
            + PREFIX_SCORE_DATE + "2012-08-09 ";

    public static final String MESSAGE_SUCCESS = "Added score to Student %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This score already exists in this student's score list";

    private final Index index;
    private final Score toAdd;

    /**
     * Creates an AddScoreCommand to add the specified {@code Score} to a person
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

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.hasScore(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        personToEdit.addScore(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                personToEdit.getName(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddScoreCommand // instanceof handles nulls
                && toAdd.equals(((AddScoreCommand) other).toAdd));
    }
}
