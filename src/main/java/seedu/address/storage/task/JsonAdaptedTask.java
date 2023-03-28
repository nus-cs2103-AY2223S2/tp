package seedu.address.storage.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskFeedingReminder;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;

    private final String tank;

    private final String priority;

    private final String isReminder;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                           @JsonProperty("tank") String tank,
                           @JsonProperty("priority") String priority,
                           @JsonProperty("isReminder") String isReminder) {
        this.description = description;
        this.tank = tank;
        this.priority = priority;
        this.isReminder = isReminder;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {

        description = source.getDescription().description;
        if (source.hasPriority()) {
            priority = source.getPriority().priority;
        } else {
            priority = null;
        }
        isReminder = String.valueOf(source.getIsReminder());
        if (source.isTankRelatedTask()) {
            tank = source.getTank().getTankName().fullTankName;
        } else {
            tank = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted Task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Task.
     */
    public Task toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        Tank modelTank = null;

        if (this.tank != null) {
            modelTank = new Tank(new TankName(this.tank), new AddressBook(), );
        }

        Priority modelPriority = null;
        if (this.priority != null) {
            modelPriority = new Priority(this.priority);
        }

        final Description modelDescription = new Description(description);

        if (isReminder.equals("true")) {
            TaskFeedingReminder task = new TaskFeedingReminder(modelDescription, modelTank);
            return task;
        }
        return new Task(modelDescription, modelTank, modelPriority);
    }
}
