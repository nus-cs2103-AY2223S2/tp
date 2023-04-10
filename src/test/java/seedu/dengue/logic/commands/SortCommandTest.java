package seedu.dengue.logic.commands;

import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.comparators.PersonAgeComparator;
import seedu.dengue.logic.comparators.PersonDateComparator;
import seedu.dengue.logic.comparators.PersonNameComparator;
import seedu.dengue.logic.comparators.PersonPostalComparator;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.predicate.FindPredicate;
import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.model.variant.Variant;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

    @Test
    public void execute_sortByName_success() {

        SortCommand command = new SortCommand(new PersonNameComparator(), "NAME");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "NAME");

        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(new PersonNameComparator());
        expectedModel.sort(toSort);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByAge_success() {

        SortCommand command = new SortCommand(new PersonAgeComparator(), "AGE");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "AGE");

        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(new PersonAgeComparator());
        expectedModel.sort(toSort);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByPostal_success() {

        SortCommand command = new SortCommand(new PersonPostalComparator(), "POSTAL");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "POSTAL");

        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(new PersonPostalComparator());
        expectedModel.sort(toSort);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByDate_success() {

        SortCommand command = new SortCommand(new PersonDateComparator(), "DATE");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "DATE");

        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(new PersonDateComparator());
        expectedModel.sort(toSort);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmptyDengueHotspotTracker_success() {

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        SortCommand command = new SortCommand(new PersonNameComparator(), "NAME");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "NAME");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortAlreadySortedDengueHotspotTracker_success() {

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        SortCommand command = new SortCommand(new PersonNameComparator(), "NAME");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "NAME");

        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(new PersonNameComparator());

        model.sort(toSort);
        expectedModel.sort(toSort);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortFilteredDengueHotspotTracker_success() throws ParseException {

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        SortCommand command = new SortCommand(new PersonNameComparator(), "NAME");
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "NAME");

        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> testName = Optional.of(new Name("Kurz"));
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> emptyDateRange = ContinuousData.generateRange(new StartDate(emptyDate), new EndDate(emptyDate));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        List<Person> lastShownList = model.getDengueHotspotTracker().getPersonList();
        List<Person> toSort = new ArrayList<>(lastShownList);

        toSort.sort(new PersonNameComparator());
        expectedModel.sort(toSort);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
