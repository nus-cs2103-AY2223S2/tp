package trackr.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.commands.CommandResult;
import trackr.logic.parser.CriteriaEnum;
import trackr.model.ModelEnum;
import trackr.model.ReadOnlyTaskList;
import trackr.model.TaskList;
import trackr.model.item.Item;
import trackr.model.task.SortTasksComparator;
import trackr.model.task.Task;
import trackr.testutil.TaskBuilder;
import trackr.testutil.TestUtil.ModelStub;

public class SortTasksCommandTest {

    private ModelStubWithUnsortedTasks model;
    private SortTasksComparator comparator;

    @Test
    public void constructor_nullSort_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortTasksCommand(null));
    }

    @BeforeEach
    public void init_modelStub() {
        model = new ModelStubWithUnsortedTasks();
        comparator = new SortTasksComparator();
    }

    @Test
    public void execute_sortsTaskName_success() {
        Task first = new TaskBuilder().withTaskName("A").build();
        Task second = new TaskBuilder().withTaskName("B").build();
        Task third = new TaskBuilder().withTaskName("C").build();
        model.addItem(second, ModelEnum.TASK);
        model.addItem(first, ModelEnum.TASK);
        model.addItem(third, ModelEnum.TASK);

        comparator.setCriteria(CriteriaEnum.NAME);

        SortTasksCommand sortTasksCommand = new SortTasksCommand(comparator);
        CommandResult res = sortTasksCommand.execute(model);
        assertEquals(SortTasksCommand.MESSAGE_SUCCESS, res.getFeedbackToUser());
        assertEquals(model.tasksAdded.get(0), first);
    }

    @Test
    public void execute_sortDefaultStatusAndDeadline_sucess() {
        Task first = new TaskBuilder().withTaskDeadline("01/01/2024").build();
        Task second = new TaskBuilder().withTaskDeadline("12/12/2024").build();
        model.addItem(second, ModelEnum.TASK);
        model.addItem(first, ModelEnum.TASK);

        comparator.setCriteria(CriteriaEnum.STATUS_AND_DEADLINE);

        SortTasksCommand sort = new SortTasksCommand(comparator);
        CommandResult res = sort.execute(model);
        assertEquals(SortTasksCommand.MESSAGE_SUCCESS, res.getFeedbackToUser());
        assertEquals(model.tasksAdded.get(0), first);
    }

    private class ModelStubWithUnsortedTasks extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return tasksAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            tasksAdded.add((Task) item);
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            return new TaskList();
        }

        public void sortFilteredTaskList(Comparator<Task> comparator) {
            requireNonNull(comparator);
            tasksAdded.sort(comparator);
        }
    }
}
