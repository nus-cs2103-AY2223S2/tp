package trackr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.SupplierList;
import trackr.model.ReadOnlyAddressBook;
import trackr.model.ReadOnlyTaskList;
import trackr.model.TaskList;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;

/**
 * An Immutable Trackr that is serializable to JSON format.
 */
@JsonRootName(value = "trackr")
class JsonSerializableTrackr {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackr} with the given persons and tasks.
     */
    @JsonCreator
    public JsonSerializableTrackr(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                  @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.persons.addAll(persons);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} and {@code ReadOnlyTaskList} into this class for Jackson use.
     *
     * @param sourcePerson future changes to this will not affect the created {@code JsonSerializableTrackr}.
     * @param sourceTask future changes to this will not affect the created {@code JsonSerializableTrackr}.
     */
    public JsonSerializableTrackr(ReadOnlyAddressBook sourcePerson, ReadOnlyTaskList sourceTask) {
        persons.addAll(sourcePerson
            .getSupplierList()
            .stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList()));
        tasks.addAll(sourceTask.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trackr into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SupplierList toModelType() throws IllegalValueException {
        SupplierList addressBook = new SupplierList();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Supplier person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasSupplier(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addSupplier(person);
        }
        return addressBook;
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
