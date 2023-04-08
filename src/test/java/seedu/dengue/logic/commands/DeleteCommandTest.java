package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_DANIEL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_FIONA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_GEORGE;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.dengue.testutil.TypicalPersons.ALICE;
import static seedu.dengue.testutil.TypicalPersons.DANIEL;
import static seedu.dengue.testutil.TypicalPersons.FIONA;
import static seedu.dengue.testutil.TypicalPersons.GEORGE;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.commons.core.index.Index;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.predicate.DeleteDatePredicate;
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

    // delete-by-date tests

    @Test
    public void execute_validDateUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Date dateToDelete = personToDelete.getDate();
        DeleteDatePredicate predicate = new DeleteDatePredicate(Optional.of(dateToDelete));

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_DATE_SUCCESS, 1, dateToDelete);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Date dateToDelete = personToDelete.getDate();
        DeleteDatePredicate predicate = new DeleteDatePredicate(Optional.of(dateToDelete));

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_DATE_SUCCESS, 1, dateToDelete);

        Model expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateNoMatchUnfilteredList_success() {
        Date dateToDelete = new Date("9999-12-31");
        DeleteDatePredicate predicate = new DeleteDatePredicate(Optional.of(dateToDelete));

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_DATE_SUCCESS, 0, dateToDelete);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateNoMatchFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Date dateToDelete = new Date("9999-12-31");
        DeleteDatePredicate predicate = new DeleteDatePredicate(Optional.of(dateToDelete));

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_DATE_SUCCESS, 0, dateToDelete);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    // delete-by-range tests

    @Test
    public void execute_completeRangeUnfilteredList_success() {

        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_DANIEL))),
                new EndDate(Optional.of(new Date(VALID_DATE_GEORGE))));

        DeleteDatePredicate predicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 2,
                dateRange.getStart(), dateRange.getEnd());

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(DANIEL);
        expectedModel.deletePerson(GEORGE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_completeRangeFilteredList_success() {

        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> emptyName = Optional.empty();
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_DANIEL))),
                new EndDate(Optional.of(new Date(VALID_DATE_GEORGE))));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, emptyAgeRange);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        DeleteDatePredicate deletePredicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(deletePredicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 2,
                dateRange.getStart(), dateRange.getEnd());

        expectedModel.deletePerson(DANIEL);
        expectedModel.deletePerson(GEORGE);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_partialRangeStartGivenUnfilteredList_success() {

        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.empty()));

        DeleteDatePredicate predicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 1,
                dateRange.getStart(), dateRange.getEnd());

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(ALICE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_partialRangeStartGivenFilteredList_success() {

        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> emptyName = Optional.empty();
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.empty()));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, emptyAgeRange);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        DeleteDatePredicate deletePredicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(deletePredicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 1,
                dateRange.getStart(), dateRange.getEnd());

        expectedModel.deletePerson(ALICE);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_partialRangeEndGivenUnfilteredList_success() {

        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.empty()),
                new EndDate(Optional.of(new Date(VALID_DATE_FIONA))));

        DeleteDatePredicate predicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 1,
                dateRange.getStart(), dateRange.getEnd());

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(FIONA);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_partialRangeEndGivenFilteredList_success() {

        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> emptyName = Optional.empty();
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.empty()),
                new EndDate(Optional.of(new Date(VALID_DATE_FIONA))));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, emptyAgeRange);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        DeleteDatePredicate deletePredicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(deletePredicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 1,
                dateRange.getStart(), dateRange.getEnd());

        expectedModel.deletePerson(FIONA);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_completeRangeSameDatesUnfilteredList_success() {

        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));

        DeleteDatePredicate predicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(predicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 1,
                dateRange.getStart(), dateRange.getEnd());

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        expectedModel.deletePerson(ALICE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_partialRangeSameDatesFilteredList_success() {

        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> emptyName = Optional.empty();
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> dateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, emptyAgeRange);

        ModelManager expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        DeleteDatePredicate deletePredicate = new DeleteDatePredicate(dateRange);

        DeleteCommand deleteCommand = new DeleteCommand(deletePredicate);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RANGE_SUCCESS, 1,
                dateRange.getStart(), dateRange.getEnd());

        expectedModel.deletePerson(ALICE);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
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
