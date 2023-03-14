package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.TypicalPersons;

class UndoManagerTest {
    private static final String TEST_COMMAND_ONE = "TEST COMMAND ONE";
    private static final String TEST_COMMAND_TWO = "TEST COMMAND TWO";
    private static final String TEST_COMMAND_THREE = "TEST COMMAND THREE";
    private UndoManager undoManager = new UndoManager(new AddressBook(), 5);

    @Test
    void addToHistory() {
        AddressBook ab = TypicalPersons.getTypicalAddressBook();
        undoManager.addToHistory(ab, TEST_COMMAND_ONE);
        assertEquals(undoManager.getCurrentState(), ab);
        assertNotSame(undoManager.getCurrentState(), ab);

        ab.addPerson(TypicalPersons.IDA);
        undoManager.addToHistory(ab, TEST_COMMAND_TWO);
        assertEquals(undoManager.getCurrentState(), ab);
        assertNotSame(undoManager.getCurrentState(), ab);
    }

    @Test
    void deleteUntrackedHead() {
    }

    @Test
    void hasUndoableCommand_undoableCommandPresent_returnTrue() {
        undoManager.addToHistory(TypicalPersons.getTypicalAddressBook(), TEST_COMMAND_ONE);
        assertTrue(undoManager.hasUndoableCommand());
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.CARL).build(), TEST_COMMAND_ONE);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build(), TEST_COMMAND_TWO);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.BOB).build(), TEST_COMMAND_THREE);
        assertTrue(undoManager.hasUndoableCommand());
    }
    @Test
    void hasUndoableCommand_previousHistoryGotten_returnTrue() {
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.CARL).build(), TEST_COMMAND_ONE);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build(), TEST_COMMAND_TWO);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.BOB).build(), TEST_COMMAND_THREE);
        undoManager.getPreviousHistory();
        assertTrue(undoManager.hasUndoableCommand());
    }

    @Test
    void hasUndoableCommand_undoableCommandNotPresent_returnFalse() {
        assertFalse(undoManager.hasUndoableCommand());
        undoManager.addToHistory(TypicalPersons.getTypicalAddressBook(), TEST_COMMAND_ONE);
        undoManager.getPreviousHistory();
        assertFalse(undoManager.hasUndoableCommand());
    }
    @Test
    void hasUndoableCommand_previousHistoryGotten_returnFalse() {
        undoManager.addToHistory(TypicalPersons.getTypicalAddressBook(), TEST_COMMAND_ONE);
        undoManager.getPreviousHistory();
        assertFalse(undoManager.hasUndoableCommand());
    }

    @Test
    void hasRedoableCommand_redoableCommandNotPresent_returnFalse() {
        assertFalse(undoManager.hasRedoableCommand());
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.CARL).build(), TEST_COMMAND_ONE);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build(), TEST_COMMAND_TWO);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.BOB).build(), TEST_COMMAND_THREE);
        assertFalse(undoManager.hasRedoableCommand());
    }
    @Test
    void hasRedoableCommand_redoableCommandPresent_returnTrue() {
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.CARL).build(), TEST_COMMAND_ONE);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build(), TEST_COMMAND_TWO);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.BOB).build(), TEST_COMMAND_THREE);
        undoManager.getPreviousHistory();
        assertTrue(undoManager.hasRedoableCommand());
    }

    @Test
    void getPreviousHistory() {
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.CARL).build(), TEST_COMMAND_ONE);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build(), TEST_COMMAND_TWO);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.BOB).build(), TEST_COMMAND_THREE);
        Pair<AddressBook, String> previousHistory = undoManager.getPreviousHistory();
        assertEquals(previousHistory.getKey(), new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build());
        assertEquals(previousHistory.getValue(), TEST_COMMAND_THREE);
    }

    @Test
    void getNextHistory() {
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.CARL).build(), TEST_COMMAND_ONE);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.ALICE).build(), TEST_COMMAND_TWO);
        undoManager.addToHistory(new AddressBookBuilder().withPerson(TypicalPersons.BOB).build(), TEST_COMMAND_THREE);
        undoManager.getPreviousHistory();
        Pair<AddressBook, String> previousHistory = undoManager.getNextHistory();
        assertEquals(previousHistory.getKey(), new AddressBookBuilder().withPerson(TypicalPersons.BOB).build());
        assertEquals(previousHistory.getValue(), TEST_COMMAND_THREE);
    }
}
