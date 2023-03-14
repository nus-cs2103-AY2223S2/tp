package trackr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.SupplierList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.TaskList;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;

/**
 * An Immutable Trackr that is serializable to JSON format.
 */
@JsonRootName(value = "trackr")
class JsonSerializableTrackr {

    public static final String MESSAGE_DUPLICATE_PERSON = "Suppliers list contains duplicate supplier(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedSupplier> suppliers = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackr} with the given suppliers and tasks.
     */
    @JsonCreator
    public JsonSerializableTrackr(@JsonProperty("suppliers") List<JsonAdaptedSupplier> suppliers,
                                  @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.suppliers.addAll(suppliers);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlySupplierList} and {@code ReadOnlyTaskList} into this class for Jackson use.
     *
     * @param sourceSupplier future changes to this will not affect the created {@code JsonSerializableTrackr}.
     * @param sourceTask future changes to this will not affect the created {@code JsonSerializableTrackr}.
     */
    public JsonSerializableTrackr(ReadOnlySupplierList sourceSupplier, ReadOnlyTaskList sourceTask) {
        suppliers.addAll(sourceSupplier
            .getSupplierList()
            .stream()
            .map(JsonAdaptedSupplier::new)
            .collect(Collectors.toList()));
        tasks.addAll(sourceTask.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trackr into the model's {@code SupplierList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SupplierList toModelType() throws IllegalValueException {
        SupplierList supplierList = new SupplierList();
        for (JsonAdaptedSupplier jsonAdaptedPerson : suppliers) {
            Supplier person = jsonAdaptedPerson.toModelType();
            if (supplierList.hasSupplier(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            supplierList.addSupplier(person);
        }
        return supplierList;
    }

    /**
     * Converts this trackr into the model's {@code TaskList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskList toTaskModelType() throws IllegalValueException {
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskList.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskList.addTask(task);
        }
        return taskList;
    }

}
