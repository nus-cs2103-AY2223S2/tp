package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.FilterPredicate;
import seedu.dengue.model.person.Person;


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
    public void execute_filteredNonEmptyDengueHotspotTracker_success() {
        Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

        FilterPredicate predicate = preparePredicate("Kurz Elle Kunz");
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

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FilterPredicate preparePredicate(String userInput) {
        return new FilterPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
