package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.dengue.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.dengue.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.exceptions.CommandException;
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
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

    // single index

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 1);

        Model expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of Dengue Hotspot Tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDengueHotspotTracker().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // multi-index

    @Test
    public void execute_validMultiIndexUnfilteredListNoDuplicate_success() {

        Person firstPersonToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToDelete = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 2);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(firstPersonToDelete);
        expectedModel.deletePerson(secondPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultiIndexUnfilteredListWithDuplicates_success() {

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMultiIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON, outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validMultiIndexFilteredListNoDuplicate_success() {

        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> testName = Optional.of(new Name("Meier"));
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> emptyDateRange = ContinuousData.generateRange(new StartDate(emptyDate), new EndDate(emptyDate));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        Person firstPersonToDelete = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToDelete = expectedModel.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 2);

        expectedModel.deletePerson(firstPersonToDelete);
        expectedModel.deletePerson(secondPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultiIndexFilteredListWithDuplicates_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INDEX_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMultiIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of Dengue Hotspot Tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDengueHotspotTracker().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON, outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // miscellaneous tests

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
