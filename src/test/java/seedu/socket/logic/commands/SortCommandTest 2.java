package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.model.person.Person.CATEGORY_ADDRESS;
import static seedu.socket.model.person.Person.CATEGORY_EMAIL;
import static seedu.socket.model.person.Person.CATEGORY_GITHUB;
import static seedu.socket.model.person.Person.CATEGORY_NAME;
import static seedu.socket.model.person.Person.CATEGORY_PHONE;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;

class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSocket(), new UserPrefs());
        expectedModel = new ModelManager(model.getSocket(), new UserPrefs());
    }
    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand(CATEGORY_NAME);
        SortCommand sortSecondCommand = new SortCommand(CATEGORY_ADDRESS);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(CATEGORY_NAME);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void execute_sortByName() {
        expectedModel.sortPersonList(CATEGORY_NAME);
        assertCommandSuccess(new SortCommand(CATEGORY_NAME), model, SortCommand.MESSAGE_SUCCESS
            + CATEGORY_NAME, expectedModel);
    }

    @Test
    public void execute_sortByPhone() {
        expectedModel.sortPersonList(CATEGORY_PHONE);
        assertCommandSuccess(new SortCommand(CATEGORY_PHONE), model, SortCommand.MESSAGE_SUCCESS
            + CATEGORY_PHONE, expectedModel);
    }

    @Test
    public void execute_sortByEmail() {
        expectedModel.sortPersonList(CATEGORY_EMAIL);
        assertCommandSuccess(new SortCommand(CATEGORY_EMAIL), model, SortCommand.MESSAGE_SUCCESS
            + CATEGORY_EMAIL, expectedModel);
    }

    @Test
    public void execute_sortByAddress() {
        expectedModel.sortPersonList(CATEGORY_ADDRESS);
        assertCommandSuccess(new SortCommand(CATEGORY_ADDRESS), model, SortCommand.MESSAGE_SUCCESS
            + CATEGORY_ADDRESS, expectedModel);
    }

    @Test
    public void execute_sortByGithub() {
        expectedModel.sortPersonList(CATEGORY_GITHUB);
        assertCommandSuccess(new SortCommand(CATEGORY_GITHUB), model, SortCommand.MESSAGE_SUCCESS
            + CATEGORY_GITHUB, expectedModel);
    }
}
