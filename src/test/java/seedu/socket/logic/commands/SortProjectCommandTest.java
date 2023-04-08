package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.model.project.Project.PROJ_DEADLINE;
import static seedu.socket.model.project.Project.PROJ_NAME;
import static seedu.socket.model.project.Project.PROJ_REPO_HOST;
import static seedu.socket.model.project.Project.PROJ_REPO_NAME;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;

class SortProjectCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSocket(), new UserPrefs());
        expectedModel = new ModelManager(model.getSocket(), new UserPrefs());
    }
    @Test
    public void equals() {
        SortProjectCommand sortProjectFirstCommand = new SortProjectCommand(PROJ_NAME);
        SortProjectCommand sortProjectSecondCommand = new SortProjectCommand(PROJ_DEADLINE);

        // same object -> returns true
        assertTrue(sortProjectFirstCommand.equals(sortProjectFirstCommand));

        // same values -> returns true
        SortProjectCommand sortProjectFirstCommandCopy = new SortProjectCommand(PROJ_NAME);
        assertTrue(sortProjectFirstCommand.equals(sortProjectFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortProjectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortProjectFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortProjectFirstCommand.equals(sortProjectSecondCommand));
    }

    @Test
    public void execute_sortByName() {
        expectedModel.sortProjectList(PROJ_NAME);
        assertCommandSuccess(new SortProjectCommand(PROJ_NAME), model, SortProjectCommand.MESSAGE_SUCCESS
            + PROJ_NAME, expectedModel);
    }

    @Test
    public void execute_sortByDeadline() {
        expectedModel.sortProjectList(PROJ_DEADLINE);
        assertCommandSuccess(new SortProjectCommand(PROJ_DEADLINE), model, SortProjectCommand.MESSAGE_SUCCESS
            + PROJ_DEADLINE, expectedModel);
    }

    @Test
    public void execute_sortByRepoHost() {
        expectedModel.sortProjectList(PROJ_REPO_HOST);
        assertCommandSuccess(new SortProjectCommand(PROJ_REPO_HOST), model, SortProjectCommand.MESSAGE_SUCCESS
            + PROJ_REPO_HOST, expectedModel);
    }

    @Test
    public void execute_sortByRepoName() {
        expectedModel.sortProjectList(PROJ_REPO_NAME);
        assertCommandSuccess(new SortProjectCommand(PROJ_REPO_NAME), model, SortProjectCommand.MESSAGE_SUCCESS
            + PROJ_REPO_NAME, expectedModel);
    }

}
