package seedu.address.model;

import static seedu.address.logic.commands.UnassignCommand.MESSAGE_NON_EXIST_ASSIGNMENT;

import java.util.List;
import java.util.function.Predicate;

<<<<<<< HEAD
import javafx.collections.ObservableList;
=======
import seedu.address.logic.commands.exceptions.CommandException;
>>>>>>> upstream/master
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
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

    public boolean hasTaskModelManagerItem(Task task) {
        return taskModelManager.hasItem(task);
    }

    public void addTaskModelManagerItem(Task task) {
        taskModelManager.addItem(task);
    }

    public void updateTaskModelManagerFilteredItemList(Predicate<Task> predicate) {
        taskModelManager.updateFilteredItemList(predicate);
    }

    public ObservableList<Task> getTaskModelManagerFilteredItemList() {
        return taskModelManager.getFilteredItemList();
    }

    public void deleteTaskModelManagerItem(Task task) {
        taskModelManager.deleteItem(task);
    }

    public ReadOnlyRepository<Task> getTaskModelManagerReadOnlyRepository() {
        return taskModelManager.getReadOnlyRepository();
    }

    /**
     * Checks the given index is within the filter task list range
     *
     * @param index to check
     * @return true if within the range else false
     */
    public boolean isValidFilterTaskListIndexRange(int index) {
        return index >= 0 && index < taskModelManager.getFilteredItemList().size();
    }

    /**
     * Delete the task assignment from a person
     *
     * @param person       assigned to the task
     * @param taskFilterId base on the current task filter list
     * @return task that has been deleted
     */
    public Task deleteAssignment(Person person, int taskFilterId) throws CommandException {
        Task task = taskModelManager.getFilterItem(taskFilterId);
        AssignTask toDelete = new AssignTask(person, task);

        if (!assignTaskModelManager.hasItem(toDelete)) {
            throw new CommandException(MESSAGE_NON_EXIST_ASSIGNMENT);
        }
        assignTaskModelManager.deleteItem(toDelete);

        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getPersonId().equals(person.getId()));

        taskModelManager.updateFilteredItemList(t -> assignTasks.stream()
            .anyMatch(a -> a.getTaskId().equals(t.getId())));
        return task;
    }
}
