package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.testutil.TypicalEvents.ART;
import static ezschedule.testutil.TypicalEvents.BOAT;
import static ezschedule.testutil.TypicalEvents.CARNIVAL;
import static ezschedule.testutil.TypicalEvents.DRAG;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.EventContainsKeywordsPredicate;
import ezschedule.model.event.EventMatchesDatePredicate;
import ezschedule.model.event.EventMatchesKeywordsAndDatePredicate;
import ezschedule.model.event.Name;
import ezschedule.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the {@code Model}) for {@code FindCommand}.
 */
public class FindCommandTest {

    private final Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalScheduler(), new UserPrefs());

    @Test
    public void execute_zeroMatchingNames_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("NO MATCHING NAME"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventContainsKeywordsPredicate predicate = prepareNamePredicate("NO MATCHING NAME");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_partiallyMatchingName_singleEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art cl"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventContainsKeywordsPredicate predicate = prepareNamePredicate("Art cl");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ART), model.getFilteredEventList());
    }

    @Test
    public void execute_partiallyMatchingNames_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("race"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventContainsKeywordsPredicate predicate = prepareNamePredicate("race");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOAT, DRAG), model.getFilteredEventList());
    }

    @Test
    public void execute_matchingName_singleEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art class"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventContainsKeywordsPredicate predicate = prepareNamePredicate("Art class");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ART), model.getFilteredEventList());
    }

    @Test
    public void execute_matchingNames_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art class Carnival"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventContainsKeywordsPredicate predicate = prepareNamePredicate("Art class Carnival");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ART, CARNIVAL), model.getFilteredEventList());
    }

    @Test
    public void execute_zeroMatchingDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setDate(new Date("2023-12-31"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesDatePredicate predicate = prepareDatePredicate("2023-12-31");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_matchingDate_singleEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setDate(new Date("2023-05-01"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesDatePredicate predicate = prepareDatePredicate("2023-05-01");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ART), model.getFilteredEventList());
    }

    @Test
    public void execute_matchingDate_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setDate(new Date("2023-05-01"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesDatePredicate predicate = prepareDatePredicate("2023-05-01");
        Event sameDateEvent = new EventBuilder().withDate("2023-05-01").build();
        model.addEvent(sameDateEvent);
        expectedModel.addEvent(sameDateEvent);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(sameDateEvent, ART), model.getFilteredEventList());
    }

    @Test
    public void execute_matchingNameAndZeroMatchingDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art class"));
        findEventDescriptor.setDate(new Date("2023-12-31"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesKeywordsAndDatePredicate predicate =
                prepareNameAndDatePredicate("Art class", "2023-12-31");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_zeroMatchingNameAndMatchingDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("NO MATCHING NAME"));
        findEventDescriptor.setDate(new Date("2023-05-01"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesKeywordsAndDatePredicate predicate =
                prepareNameAndDatePredicate("NO MATCHING NAME", "2023-05-01");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_zeroMatchingNameAndZeroMatchingDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("NO MATCHING NAME"));
        findEventDescriptor.setDate(new Date("2023-12-31"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesKeywordsAndDatePredicate predicate =
                prepareNameAndDatePredicate("NO MATCHING NAME", "2023-12-31");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_partiallyMatchingNameAndMatchingDate_singleEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art cl"));
        findEventDescriptor.setDate(new Date("2023-05-01"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesKeywordsAndDatePredicate predicate =
                prepareNameAndDatePredicate("Art cl", "2023-05-01");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ART), model.getFilteredEventList());
    }

    @Test
    public void execute_partiallyMatchingNameAndMatchingDate_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art"));
        findEventDescriptor.setDate(new Date("2023-05-01"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesKeywordsAndDatePredicate predicate =
                prepareNameAndDatePredicate("Art", "2023-05-01");
        Event event = new EventBuilder().withName("Art lesson").withDate("2023-05-01").build();
        model.addEvent(event);
        expectedModel.addEvent(event);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(event, ART), model.getFilteredEventList());
    }

    @Test
    public void execute_matchingNameAndMatchingDate_singleEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("Art class"));
        findEventDescriptor.setDate(new Date("2023-05-01"));
        FindCommand command = new FindCommand(findEventDescriptor);
        EventMatchesKeywordsAndDatePredicate predicate =
                prepareNameAndDatePredicate("Art class", "2023-05-01");
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ART), model.getFilteredEventList());
    }

    @Test
    public void equals() {
        FindEventDescriptor firstFindEventDescriptor = new FindEventDescriptor();
        firstFindEventDescriptor.setName(new Name("first"));
        firstFindEventDescriptor.setDate(new Date("2023-06-01"));
        FindCommand findFirstCommand = new FindCommand(firstFindEventDescriptor);

        FindEventDescriptor secondFindEventDescriptor = new FindEventDescriptor();
        secondFindEventDescriptor.setName(new Name("second"));
        secondFindEventDescriptor.setDate(new Date("2023-06-01"));
        FindCommand findSecondCommand = new FindCommand(secondFindEventDescriptor);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstFindEventDescriptor);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code EventContainsKeywordsPredicate}.
     */
    private EventContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new EventContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EventContainsKeywordsPredicate}.
     */
    private EventMatchesDatePredicate prepareDatePredicate(String userInput) {
        return new EventMatchesDatePredicate(new Date(userInput));
    }

    /**
     * Parses {@code nameInput} and {@code dateInput} into a {@code EventMatchesKeywordsAndDatePredicate}.
     */
    private EventMatchesKeywordsAndDatePredicate prepareNameAndDatePredicate(String nameInput, String dateInput) {
        return new EventMatchesKeywordsAndDatePredicate(
                Arrays.asList(nameInput.split("\\s+")), new Date(dateInput));
    }
}
