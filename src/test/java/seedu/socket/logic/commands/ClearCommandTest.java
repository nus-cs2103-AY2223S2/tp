package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.Socket;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.person.predicate.TagContainsKeywordsPredicate;

public class ClearCommandTest {

    @Test
    public void execute_clearTagNotExist_throwCommandException() {
        Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
        List<String> tagKeywords = Arrays.asList("CS2103", "Haha");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(tagKeywords);
        ClearCommand clearCommand = new ClearCommand(predicate);
        assertThrows(CommandException.class, ClearCommand.MESSAGE_NOT_SUCCESS, () -> clearCommand.execute(model));
    }

    @Test
    public void execute_nonEmptyClearSocket_success() {
        Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSocket(), new UserPrefs());
        expectedModel.setSocket(new Socket());
        TagContainsKeywordsPredicate emptyPredicate = new TagContainsKeywordsPredicate(new ArrayList<>());
        assertCommandSuccess(new ClearCommand(emptyPredicate), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void testEqual() {
        List<String> tagKeywords = Arrays.asList("CS2103", "Haha");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(tagKeywords);

        ClearCommand clearCommand = new ClearCommand(predicate);
        // same tag predicate -> returns true
        assertTrue(clearCommand.equals(new ClearCommand(predicate)));

        // same object -> returns true
        assertTrue(clearCommand.equals(clearCommand));

        // different type -> returns false
        assertFalse(clearCommand.equals(1));

        // null -> returns false
        assertFalse(clearCommand.equals(null));
    }
}
