package arb.logic.commands.client;

import static arb.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalClients.CARL;
import static arb.testutil.TypicalClients.ELLE;
import static arb.testutil.TypicalClients.FIONA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.client.Client;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.testutil.PredicateUtil;

/**
 * Contains integration tests (interaction with the Model) for {@code FindClientCommand}.
 */
public class FindClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate("second");

        FindClientCommand findFirstCommand = new FindClientCommand(firstPredicate);
        FindClientCommand findSecondCommand = new FindClientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClientCommand findFirstCommandCopy = new FindClientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void execute_zeroKeywords_noClientFound() {
        NameContainsKeywordsPredicate predicate = PredicateUtil.getNameContainsKeywordsPredicate();
        CombinedPredicate<Client> finalPredicate = PredicateUtil.getCombinedPredicate(predicate);
        FindClientCommand command = new FindClientCommand(finalPredicate);
        expectedModel.updateFilteredClientList(finalPredicate);
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 0) + "\n" + finalPredicate;
        assertCommandSuccess(command, ListType.CLIENT, ListType.CLIENT, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void execute_multipleKeywords_multipleClientsFound() {
        NameContainsKeywordsPredicate predicate =
                PredicateUtil.getNameContainsKeywordsPredicate("Kurz", "Elle", "Kunz");
        CombinedPredicate<Client> finalPredicate = PredicateUtil.getCombinedPredicate(predicate);
        FindClientCommand command = new FindClientCommand(finalPredicate);
        expectedModel.updateFilteredClientList(finalPredicate);
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3) + "\n" + finalPredicate;
        assertCommandSuccess(command, ListType.CLIENT, ListType.CLIENT, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

}
