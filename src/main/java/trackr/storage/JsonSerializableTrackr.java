package trackr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.AddressBook;
import trackr.model.OrderList;
import trackr.model.ReadOnlyAddressBook;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.TaskList;
import trackr.model.order.Order;
import trackr.model.person.Person;
import trackr.model.task.Task;

/**
 * An Immutable Trackr that is serializable to JSON format.
 */
@JsonRootName(value = "trackr")
class JsonSerializableTrackr {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_ORDER = "Tasks list contains duplicate order(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackr} with the given persons and tasks.
     */
    @JsonCreator
    public JsonSerializableTrackr(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                  @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                  @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.persons.addAll(persons);
        this.tasks.addAll(tasks);
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} and {@code ReadOnlyTaskList} into this class for Jackson use.
     *
     * @param sourcePerson future changes to this will not affect the created {@code JsonSerializableTrackr}.
     * @param sourceTask future changes to this will not affect the created {@code JsonSerializableTrackr}.
     * @param sourceOrder future changes to this will not affect the created {@code JsonSerializableTrackr}.
     */
    public JsonSerializableTrackr(ReadOnlyAddressBook sourcePerson,
            ReadOnlyTaskList sourceTask, ReadOnlyOrderList sourceOrder) {
        persons.addAll(sourcePerson.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tasks.addAll(sourceTask.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        orders.addAll(sourceOrder.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trackr into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
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

    /**
     * Converts this trackr into the model's {@code OrderList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public OrderList toOrderModelType() throws IllegalValueException {
        OrderList orderList = new OrderList();
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (orderList.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            orderList.addOrder(order);
        }
        return orderList;
    }

}
