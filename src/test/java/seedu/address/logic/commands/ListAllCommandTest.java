package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAllCommand.
 */
public class ListAllCommandTest {
    private Model model;
    private Model expectedModel;

    private OfficeConnectModel officeConnectModel;
    private OfficeConnectModel expectedofficeConnectModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        officeConnectModel = new OfficeConnectModel(new RepositoryModelManager<>(getTypicalTaskRepository()),
                new RepositoryModelManager<>(new Repository<AssignTask>()));
        expectedofficeConnectModel = new OfficeConnectModel(officeConnectModel.getTaskModelManager(),
                new RepositoryModelManager<>(new Repository<AssignTask>()));
    }
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ListAllCommand command = new ListAllCommand();
        assertCommandSuccess(command, model,
                ListAllCommand.MESSAGE_SUCCESS, expectedModel);
        assertTaskCommandSuccess(command, officeConnectModel,
                ListAllCommand.MESSAGE_SUCCESS, expectedofficeConnectModel);
    }
    @Test
    public void execute_onlyPersonListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST);
        ListAllCommand command = new ListAllCommand();
        assertCommandSuccess(command, model,
                ListAllCommand.MESSAGE_SUCCESS, expectedModel);
        assertTaskCommandSuccess(command, officeConnectModel,
                ListAllCommand.MESSAGE_SUCCESS, expectedofficeConnectModel);
    }
    @Test
    public void execute_onlyTaskListIsFiltered_showsEverything() {
        showTaskAtIndex(officeConnectModel, INDEX_FIRST);
        ListAllCommand command = new ListAllCommand();
        assertCommandSuccess(command, model,
                ListAllCommand.MESSAGE_SUCCESS, expectedModel);
        assertTaskCommandSuccess(command, officeConnectModel,
                ListAllCommand.MESSAGE_SUCCESS, expectedofficeConnectModel);
    }
    @Test
    public void execute_bothListsAreFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST);
        showTaskAtIndex(officeConnectModel, INDEX_FIRST);
        ListAllCommand command = new ListAllCommand();
        assertCommandSuccess(command, model,
                ListAllCommand.MESSAGE_SUCCESS, expectedModel);
        assertTaskCommandSuccess(command, officeConnectModel,
                ListAllCommand.MESSAGE_SUCCESS, expectedofficeConnectModel);
    }
}
