package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.logic.commands.CommandTestUtil.showProjectAtIndex;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;

public class ListProjectCommandTest {
    private Model model;
    private Model expectedModel;

    private String message;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String message = String.format(ListProjectCommand.MESSAGE_PROJECTS_CONTENT, model.getProjectsContent());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String message = String.format(ListProjectCommand.MESSAGE_PROJECTS_CONTENT, model.getProjectsContent());
        assertCommandSuccess(new ListProjectCommand(), ListType.PROJECT, ListType.PROJECT, model,
                ListProjectCommand.MESSAGE_SUCCESS + "\n" + message, expectedModel);
    }

    @Test
    public void execute_currentListShownClient_success() {
        String message = String.format(ListProjectCommand.MESSAGE_PROJECTS_CONTENT, model.getProjectsContent());
        assertCommandSuccess(new ListProjectCommand(), ListType.CLIENT, ListType.PROJECT, model,
                ListProjectCommand.MESSAGE_SUCCESS + "\n" + message, expectedModel);
    }

    @Test
    public void execute_currentListShownTag_success() {
        String message = String.format(ListProjectCommand.MESSAGE_PROJECTS_CONTENT, model.getProjectsContent());
        assertCommandSuccess(new ListProjectCommand(), ListType.TAG, ListType.PROJECT, model,
                ListProjectCommand.MESSAGE_SUCCESS + "\n" + message, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showProjectAtIndex(model, INDEX_FIRST);
        String message = String.format(ListProjectCommand.MESSAGE_PROJECTS_CONTENT, model.getProjectsContent());
        assertCommandSuccess(new ListProjectCommand(), ListType.PROJECT, ListType.PROJECT, model,
                ListProjectCommand.MESSAGE_SUCCESS + "\n" + message, expectedModel);
    }
}

