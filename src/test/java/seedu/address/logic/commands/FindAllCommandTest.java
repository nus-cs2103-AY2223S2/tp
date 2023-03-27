package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FindContainsAnythingPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAllCommand}.
 */
public class FindAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindContainsAnythingPredicate firstPredicate =
                new FindContainsAnythingPredicate(Collections.singletonList("first"));
        FindContainsAnythingPredicate secondPredicate =
                new FindContainsAnythingPredicate(Collections.singletonList("second"));

        FindAllCommand findFirstCommand = new FindAllCommand(firstPredicate);
        FindAllCommand findSecondCommand = new FindAllCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAllCommand findFirstCommandCopy = new FindAllCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindContainsAnythingPredicate predicate = preparePredicate(" ");
        FindAllCommand command = new FindAllCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    // @Test
    // public void execute_multipleKeywords_multiplePersonsFound() {
    //     String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //     FindContainsAnythingPredicate predicate = preparePredicate("Kurz Elle Kunz");
    //     FindAllCommand command = new FindAllCommand(predicate);
    //     expectedModel.updateFilteredPersonList(predicate);
    //     assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //     assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    // }

    /**
     * Parses {@code userInput} into a {@code FindContainsAnythingPredicate}.
     */
    private FindContainsAnythingPredicate preparePredicate(String userInput) {
        return new FindContainsAnythingPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
