package seedu.address.model;

import java.util.function.Predicate;

import seedu.address.model.mapping.PersonTask;
import seedu.address.model.task.Task;


/**
 * The API of the OfficeConnectModel component.
 */
public class OfficeConnectModel {
    public static final Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    private final RepositoryModelManager<Task> taskModelManager;
    private final RepositoryModelManager<PersonTask> personTaskModelManager;

    /**
     * Initializes a OfficeConnectModel empty data.
     */
    public OfficeConnectModel() {
        taskModelManager = new RepositoryModelManager<>(new Repository<Task>());
        personTaskModelManager = new RepositoryModelManager<>(new Repository<PersonTask>());
    }
    /**
     * Initializes a OfficeConnectModel given data.
     */
    public OfficeConnectModel(RepositoryModelManager<Task> taskModelManager,
                              RepositoryModelManager<PersonTask> personTaskModelManager) {
        this.taskModelManager = taskModelManager;
        this.personTaskModelManager = personTaskModelManager;
    }

    public RepositoryModelManager<Task> getTaskModelManager() {
        return taskModelManager;
    }

    public RepositoryModelManager<PersonTask> getPersonTaskModelManager() {
        return personTaskModelManager;
    }

}
