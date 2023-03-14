package seedu.task.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.calendar.DailyPlan;
import seedu.task.model.task.Task;

/**
 * Version of {@link Task}.
 */
public class JsonAdaptedDailyPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private final List<JsonAdaptedTask> task = new ArrayList<>();
    private long desiredWorkload;
    private long currentWorkload;
    private LocalDate date;

    /**
     * Constructs a {@code JsonAdaptedDailyPlan} with the given Daily Plan details.
     */
    @JsonCreator
    public JsonAdaptedDailyPlan(@JsonProperty("task") List<JsonAdaptedTask> task,
                                @JsonProperty("desiredWorkload") Long desiredWorkload,
                                @JsonProperty("currentWorkload") Long currentWorkload,
                                @JsonProperty("date") LocalDate date) {
        this.task.addAll(task);
        this.desiredWorkload = desiredWorkload;
        this.currentWorkload = currentWorkload;
        this.date = date;
    }

    /**
     * Converts a given {@code DailyPlan} into this class to use
     */
    public JsonAdaptedDailyPlan(DailyPlan source) {
        task.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));

        desiredWorkload = source.getDesiredWorkload();
        currentWorkload = source.getCurrentWorkload();
        date = source.getDate();
    }

    /**
     * Converts this DailyPlan object to model's {@code DailyPlan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted DailyPlan.
     */

    public DailyPlan toModelType() throws IllegalValueException {
        final ArrayList<Task> tasksList = new ArrayList<>();
        for (JsonAdaptedTask t : task) {
            tasksList.add(t.toModelType());
        }

        if (Objects.isNull(desiredWorkload)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Desired Workload"));
        }

        if (Objects.isNull(currentWorkload)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Current Workload"));
        }

        if (Objects.isNull(date)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        return new DailyPlan(tasksList, desiredWorkload, currentWorkload, date);
    }

}
