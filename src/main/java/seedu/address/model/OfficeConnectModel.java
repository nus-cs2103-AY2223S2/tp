package seedu.address.model;

import java.util.function.Predicate;
import java.util.logging.Filter;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;


/**
 * The API of the OfficeConnectModel component.
 */
public class OfficeConnectModel {
    public static final Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    private final RepositoryModelManager<Task> taskModelManager;
    private final RepositoryModelManager<AssignTask> assignTaskModelManager;

    /**
     * Initializes a OfficeConnectModel empty data.
     */
    public OfficeConnectModel() {
        taskModelManager = new RepositoryModelManager<>(new Repository<Task>());
        assignTaskModelManager = new RepositoryModelManager<>(new Repository<AssignTask>());
    }
    /**
     * Initializes a OfficeConnectModel given data.
     */
    public OfficeConnectModel(RepositoryModelManager<Task> taskModelManager,
                              RepositoryModelManager<AssignTask> assignTaskModelManager) {
        this.taskModelManager = taskModelManager;
        this.assignTaskModelManager = assignTaskModelManager;
    }

    public RepositoryModelManager<Task> getTaskModelManager() {
        return taskModelManager;
    }

    public RepositoryModelManager<AssignTask> getAssignTaskModelManager() {
        return assignTaskModelManager;
    }

    public boolean hasItem(Task task) {
        return taskModelManager.hasItem(task);
    }

    public void addItem(Task task) {
        taskModelManager.addItem(task);
    }

    public void updateFilteredItemList(Predicate<Task> predicate) {
        taskModelManager.updateFilteredItemList(predicate);
    }

    public ObservableList<Task> getFilteredItemList() {
        return taskModelManager.getFilteredItemList();
    }

    public void deleteItem(Task task) {
        taskModelManager.deleteItem(task);
    }

    public ReadOnlyRepository<Task> getReadOnlyRepository() {
        return taskModelManager.getReadOnlyRepository();
    }
}
