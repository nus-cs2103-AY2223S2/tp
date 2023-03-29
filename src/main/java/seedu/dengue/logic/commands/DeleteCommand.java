package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: any number of INDEXes (must be a positive integer)\n"
            + "[" + PREFIX_DATE + "DATE]...\n"
            + "[" + PREFIX_STARTDATE + "STARTDATE]...\n"
            + "[" + PREFIX_ENDDATE + "ENDDATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2001-10-10"
            + "Indexes cannot occur with dates, and a single date cannot occur with a start or end date.";

    public static final String MESSAGE_DELETE_INDEX_SUCCESS = "%1$s cases deleted";
    public static final String MESSAGE_DELETE_DATE_SUCCESS = "%1$s cases from %2$s deleted";
    public static final String MESSAGE_DELETE_RANGE_SUCCESS = "%1$s cases from %2$s to %3$s deleted";

    private final Optional<List<Index>> targetIndexes;
    private final Optional<Date> date;
    private final Optional<Range<Date>> range;

    // possibly separate into three different commands? index, date, range

    /**
     * Deletes the person at the targets {@code targetIndexes} in the Dengue Hotspot Tracker
     * @param targetIndexes
     */
    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = Optional.of(targetIndexes);
        this.date = Optional.empty();
        this.range = Optional.empty();
    }

    // for test cases to be happy, to delete

    /**
     * Deletes the person at the target {@code targetIndex} in the Dengue Hotspot Tracker
     * @param targetIndex
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndexes = Optional.of(List.<Index>of(targetIndex));
        this.date = Optional.empty();
        this.range = Optional.empty();
    }

    /**
     * Deletes people at the target {@code date} in the Dengue Hotspot Tracker
     * @param date
     */
    public DeleteCommand(Date date) {
        this.targetIndexes = Optional.empty();
        this.date = Optional.of(date);
        this.range = Optional.empty();
    }

    /**
     * Deletes people within the {@code range} dates in the Dengue Hotspot Tracker
     * @param range
     */
    public DeleteCommand(Range<Date> range) {
        this.targetIndexes = Optional.empty();
        this.date = Optional.empty();
        this.range = Optional.of(range);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert !(targetIndexes.isPresent() & date.isPresent());
        assert !(date.isPresent() & range.isPresent());
        assert !(targetIndexes.isPresent() & range.isPresent());

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndexes.isPresent()) {
            return executeIndexes(model, lastShownList);
        } else if (date.isPresent()) {
            return executeDate(model, lastShownList);
        } else {
            return executeRange(model, lastShownList);
        }
    }

    private CommandResult executeIndexes(Model model, List<Person> lastShownList) throws CommandException {
        assert targetIndexes.isPresent();
        List<Index> indexes = targetIndexes.get();

        for (Index idx : indexes) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        List<Person> referenceCopy = new ArrayList<>(lastShownList);
        List<Person> toDelete = new ArrayList<>();

        for (Index idx : indexes) {
            Person personToDelete = referenceCopy.get(idx.getZeroBased());
            toDelete.add(personToDelete);
        }

        deleteAll(model, toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INDEX_SUCCESS, indexes.size()));
    }


    private CommandResult executeDate(Model model, List<Person> lastShownList) {
        assert date.isPresent();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);

        PersonContainsDatePredicate predicate = new PersonContainsDatePredicate(date);
        List<Person> toDelete = getPersonsToDelete(referenceCopy, predicate);

        deleteAll(model, toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DATE_SUCCESS, toDelete.size(), date.get()));
    }

    private CommandResult executeRange(Model model, List<Person> lastShownList) {
        assert range.isPresent();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);

        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(range.get());
        List<Person> toDelete = getPersonsToDelete(referenceCopy, predicate);

        deleteAll(model, toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_RANGE_SUCCESS,
                toDelete.size(), range.get().getStart(), range.get().getEnd()));
    }

    private List<Person> getPersonsToDelete(List<Person> reference, Predicate<Person> predicate) {
        List<Person> toDelete = new ArrayList<>();
        for (Person person : reference) {
            if (predicate.test(person)) {
                toDelete.add(person);
            }
        }
        return toDelete;
    }

    private static void deleteAll(Model model, List<Person> toDelete) {
        List<Person> fullList = model
                .getDengueHotspotTracker()
                .getPersonList();

        List<Person> remainingPersons = fullList
                .stream()
                .filter(p -> !toDelete.contains(p))
                .collect(Collectors.toList());

        model.setPersons(remainingPersons);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)
                && date.equals(((DeleteCommand) other).date)
                && range.equals(((DeleteCommand) other).range)); // state check
    }
}
