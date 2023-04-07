package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.predicate.DeleteDatePredicate;

/**
 * Deletes a person identified using its displayed index from the Dengue Hotspot Tracker.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: any number of INDEXes (must be a positive integer less than or equal to 2147483647)\n"
            + "[" + PREFIX_DATE + "DATE]...\n"
            + "[" + PREFIX_STARTDATE + "STARTDATE]...\n"
            + "[" + PREFIX_ENDDATE + "ENDDATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2001-10-10 \n"
            + "Indexes cannot occur with dates, and a single date cannot occur with a start or end date.";

    public static final String MESSAGE_DELETE_INDEX_SUCCESS = "%1$s cases deleted";
    public static final String MESSAGE_DELETE_DATE_SUCCESS = "%1$s cases from %2$s deleted";
    public static final String MESSAGE_DELETE_RANGE_SUCCESS = "%1$s cases from %2$s to %3$s deleted";

    private final Optional<List<Index>> targetIndexes;
    private final Optional<DeleteDatePredicate> predicate;

    /**
     * Deletes the cases at the targets {@code targetIndexes} in the Dengue Hotspot Tracker
     * @param targetIndexes
     */
    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = Optional.of(targetIndexes);
        this.predicate = Optional.empty();
    }

    /**
     * Deletes the case at the target {@code targetIndex} in the Dengue Hotspot Tracker
     * @param targetIndex
     */
    public DeleteCommand(Index targetIndex) {
        this((List.<Index>of(targetIndex)));
    }

    /**
     * Deletes cases found by the {@code predicate} in the Dengue Hotspot Tracker
     * @param predicate A predicate.
     */
    public DeleteCommand(DeleteDatePredicate predicate) {
        this.targetIndexes = Optional.empty();
        this.predicate = Optional.of(predicate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert !(targetIndexes.isPresent() & predicate.isPresent());

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndexes.isPresent()) {
            return executeIndexes(model, lastShownList);
        } else {
            return executeDate(model, lastShownList);
        }
    }

    private CommandResult executeIndexes(Model model, List<Person> lastShownList) throws CommandException {
        assert targetIndexes.isPresent();
        Set<Index> indexesSet = new HashSet<>(targetIndexes.get());

        for (Index idx : indexesSet) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
        List<Person> referenceCopy = new ArrayList<>(lastShownList);
        List<Person> toDelete = new ArrayList<>();

        for (Index idx : indexesSet) {
            Person personToDelete = referenceCopy.get(idx.getZeroBased());
            toDelete.add(personToDelete);
        }

        deleteAll(model, toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INDEX_SUCCESS, indexesSet.size()));
    }


    private CommandResult executeDate(Model model, List<Person> lastShownList) {
        assert predicate.isPresent();

        List<Person> referenceCopy = new ArrayList<>(lastShownList);
        DeleteDatePredicate pred = predicate.get();

        List<Person> toDelete = getPersonsToDelete(referenceCopy, pred);

        deleteAll(model, toDelete);

        if (pred.date.isPresent()) {
            return new CommandResult(String.format(MESSAGE_DELETE_DATE_SUCCESS, toDelete.size(), pred.date.get()));
        } else {
            return new CommandResult(String.format(MESSAGE_DELETE_RANGE_SUCCESS,
                    toDelete.size(), pred.range.getStart(), pred.range.getEnd()));
        }
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
                && predicate.equals(((DeleteCommand) other).predicate));
    }
}
