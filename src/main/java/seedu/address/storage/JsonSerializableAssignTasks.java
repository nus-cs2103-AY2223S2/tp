package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.Repository;
import seedu.address.model.mapping.AssignTask;

/**
 * An Immutable person_task_book that is serializable to JSON format.
 */
@JsonRootName(value = "assignTaskMapping")
class JsonSerializableAssignTasks {

    public static final String MESSAGE_DUPLICATE = "AssignTask list contains duplicate assignTask(s).";
    private final List<JsonAdaptedAssignTask> assignTasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAssignTasks} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableAssignTasks(@JsonProperty("assignTasks") List<JsonAdaptedAssignTask> assignTasks) {
        this.assignTasks.addAll(assignTasks);
    }

    /**
     * Converts a given {@code ReadOnlyRepository} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAssignTasks}.
     */
    public JsonSerializableAssignTasks(ReadOnlyRepository<AssignTask> source) {
        assignTasks.addAll(source.getData().stream()
            .map(JsonAdaptedAssignTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this AssignTask into the model's {@code AssignTasks} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Repository<AssignTask> toModelType() throws IllegalValueException {
        Repository<AssignTask> assignTasks = new Repository<>();
        for (JsonAdaptedAssignTask jsonAdaptedAssignTask : this.assignTasks) {
            AssignTask assignTask = jsonAdaptedAssignTask.toModelType();
            if (assignTasks.hasItem(assignTask)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE);
            }
            assignTasks.addItem(assignTask);
        }
        return assignTasks;
    }

}
