package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Person;

/**
 * Marks / unmarks a person as needing follow-up.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a person if needing followup action.\n"
            + "Parameters: INDEX (must be a postive integer) "
            + PREFIX_MARK + "NEED FOLLOWUP: yes/no\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MARK + "no";

    public static final String MESSAGE_MARK_SUCCESS = "Marked as needing follow-up.";
    public static final String MESSAGE_UNMARK_SUCCESS = "Unmarked as don't need follow-up.";
    public static final String MESSAGE_PERSON_ALR_MARKED = "This person is already marked.";
    public static final String MESSAGE_PERSON_ALR_UNMARKED = "This person is already unmarked.";

    private final Mark mark;
    private final Index targetIndex;

    /**
     * Creates a MarkCommand to mark the person at given {@code index} as needing followup depending on {@code mark}.
     */
    public MarkCommand(Index targetIndex, Mark mark) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.mark = mark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // my code here

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person toMark = lastShownList.get(targetIndex.getZeroBased());

        if (toMark.getMark().equals(mark)) {
            throw new CommandException(mark.isMark ? MESSAGE_PERSON_ALR_MARKED : MESSAGE_PERSON_ALR_UNMARKED);
        }

        toMark.changeMark();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(mark.isMark ? MESSAGE_MARK_SUCCESS : MESSAGE_UNMARK_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndex.equals(((MarkCommand) other).targetIndex))
                && mark == ((MarkCommand) other).mark;
    }
}
