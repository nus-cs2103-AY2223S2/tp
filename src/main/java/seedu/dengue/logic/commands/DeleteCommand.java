package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.predicate.PersonContainsDatePredicate;
import seedu.dengue.model.predicate.RangeContainsPersonPredicate;
import seedu.dengue.model.range.Range;

/**
 * Deletes a person identified using its displayed index from the Dengue Hotspot Tracker.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    // TODO: update with new usage details
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    // TODO: change / add message for indexes
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_DATE_SUCCESS = "%1$s cases from %2$s deleted";
    public static final String MESSAGE_DELETE_RANGE_SUCCESS = "%1$s cases from %2$s to %3$s deleted";

    private final Optional<Index> targetIndex; // TODO: turn into list of indexes
    private final Optional<Date> date;
    private final Optional<Range<Date>> range;

    // possibly separate into three different commands? index, date, range

    /**
     * Deletes the person at the target {@code targetIndex} in the Dengue Hotspot Tracker
     *
     * @param targetIndex
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = Optional.of(targetIndex);
        this.date = Optional.empty();
        this.range = Optional.empty();
    }

    /**
     * Deletes people at the target {@code date} in the Dengue Hotspot Tracker
     *
     * @param date
     */
    public DeleteCommand(Date date) {
        this.targetIndex = Optional.empty();
        this.date = Optional.of(date);
        this.range = Optional.empty();
    }

    /**
     * Deletes people within the {@code range} dates in the Dengue Hotspot Tracker
     *
     * @param range
     */
    public DeleteCommand(Range<Date> range) {
        this.targetIndex = Optional.empty();
        this.date = Optional.empty();
        this.range = Optional.of(range);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert !(targetIndex.isPresent() & date.isPresent());
        assert !(date.isPresent() & range.isPresent());
        assert !(targetIndex.isPresent() & range.isPresent());

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.isPresent()) {
            return executeIndex(model, lastShownList);
        } else if (date.isPresent()) {
            return executeDate(model, lastShownList);
        } else {
            return executeRange(model, lastShownList);
        }
    }

    private CommandResult executeIndex(Model model, List<Person> lastShownList) throws CommandException {
        assert targetIndex.isPresent();
        // TODO: update for multiple indexes
        if (targetIndex.get().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.get().getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    private CommandResult executeDate(Model model, List<Person> lastShownList) {
        assert date.isPresent();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);
        int numDeleted = 0;
        PersonContainsDatePredicate predicate = new PersonContainsDatePredicate(date);
        for (Person person : referenceCopy) {
            if (predicate.test(person)) {
                model.deletePerson(person);
                numDeleted++;
            }
        }
        return new CommandResult(String.format(MESSAGE_DELETE_DATE_SUCCESS, numDeleted, date.get()));
    }

    private CommandResult executeRange(Model model, List<Person> lastShownList) {
        assert range.isPresent();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);
        int numDeleted = 0;
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(range.get());
        for (Person person : referenceCopy) {
            if (predicate.test(person)) {
                model.deletePerson(person);
                numDeleted++;
            }
        }
        return new CommandResult(String.format(MESSAGE_DELETE_RANGE_SUCCESS,
                numDeleted, range.get().getStart(), range.get().getEnd()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)
                && date.equals(((DeleteCommand) other).date)
                && range.equals(((DeleteCommand) other).range)); // state check
    }
}
