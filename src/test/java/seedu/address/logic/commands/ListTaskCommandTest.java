package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with OfficeConnectModel) and unit tests for ListTaskCommand.
 */
public class ListTaskCommandTest {

    private OfficeConnectModel model;
    private OfficeConnectModel expectedModel;

    @BeforeEach
    public void setUp() {
        RepositoryModelManager<Task> sampleModelManager = new RepositoryModelManager<>(new Repository<Task>());
        Task defaultTask = new TaskBuilder().build();
        sampleModelManager.addItem(defaultTask);
        model = new OfficeConnectModel(sampleModelManager, new RepositoryModelManager<>(new Repository<PersonTask>()));
        expectedModel = new OfficeConnectModel(model.getTaskModelManager(),
                new RepositoryModelManager<>(new Repository<PersonTask>()));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertTaskCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.getTaskModelManager().addItem(TaskBuilder.ofRandomTask());
        model.getTaskModelManager().updateFilteredItemList(x -> x.getSubject().getValue().equals("Sports")
                ? true : false);
        assertEquals(1, model.getTaskModelManager().getFilteredItemList().size());
        assertTaskCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
