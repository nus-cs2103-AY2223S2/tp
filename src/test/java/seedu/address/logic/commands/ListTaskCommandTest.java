package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;

/**
 * Contains integration tests (interaction with OfficeConnectModel) and unit tests for ListTaskCommand.
 */
public class ListTaskCommandTest {

    private OfficeConnectModel model;
    private OfficeConnectModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new OfficeConnectModel(new RepositoryModelManager<>(getTypicalTaskRepository()),
                new RepositoryModelManager<>(new Repository<AssignTask>()), new ModelManager());
        expectedModel = new OfficeConnectModel(model.getTaskModelManager(),
                new RepositoryModelManager<>(new Repository<AssignTask>()), new ModelManager());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertTaskCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST);
        assertTaskCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
