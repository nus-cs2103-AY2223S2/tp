package seedu.fitbook.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbook.testutil.client.TypicalClients.CARL;
import static seedu.fitbook.testutil.client.TypicalClients.ELLE;
import static seedu.fitbook.testutil.client.TypicalClients.FIONA;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.predicate.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the FitBookModel) for {@code FindCommand}.
 */
public class FindCommandTest {
    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());
    private FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstPredicate = Arrays.asList("first");
        List<Predicate<Client>> firstPredicates = new ArrayList<>();
        firstPredicates.add(new NameContainsKeywordsPredicate(firstPredicate));
        FindCommand findFirstCommand = new FindCommand(firstPredicates);

        List<String> secondPredicate = Arrays.asList("second");
        List<Predicate<Client>> secondPredicates = new ArrayList<>();
        secondPredicates.add(new NameContainsKeywordsPredicate(secondPredicate));
        FindCommand findSecondCommand = new FindCommand(secondPredicates);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicates);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Predicate<Client>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedFitBookModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedFitBookModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Predicate<Client>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedFitBookModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedFitBookModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
