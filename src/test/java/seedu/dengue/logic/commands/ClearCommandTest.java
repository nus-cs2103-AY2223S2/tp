package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.DengueHotspotTracker;
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


public class ClearCommandTest {
    @Test
    public void execute_emptyDengueHotspotTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);

        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, referenceCopy.size());
        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_unfilteredNonEmptyDengueHotspotTracker_success() {
        Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        expectedModel.setDengueHotspotTracker(new DengueHotspotTracker());

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);

        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, referenceCopy.size());
        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredNonEmptyDengueHotspotTracker_success() throws ParseException {
        Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

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

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> referenceCopy = new ArrayList<>(lastShownList);
        for (Person person : referenceCopy) {
            expectedModel.deletePerson(person);
        }
        showNoPerson(expectedModel);

        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, referenceCopy.size());
        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
