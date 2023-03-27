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
 * Jackson-friendly version of {@link DailyPlan}.
 */
public class JsonAdaptedDailyPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "DailyPlan's %s field is missing!";
    public static final String NEGATIVE_FIELD_MESSAGE_FORMAT = "DailyPlan's %s field must be non-negative!";
    private final List<JsonAdaptedTask> task = new ArrayList<>();
    private long desiredWorkload;
    private long currentWorkload;
    private String date;

    /**
     * Constructs a {@code JsonAdaptedDailyPlan} with the given Daily Plan details.
     */
    @JsonCreator
    public JsonAdaptedDailyPlan(@JsonProperty("task") List<JsonAdaptedTask> task,
                                @JsonProperty("desiredWorkload") Long desiredWorkload,
                                @JsonProperty("currentWorkload") Long currentWorkload,
                                @JsonProperty("date") String date) {
        this.task.addAll(task);
        if (desiredWorkload != null) {
            this.desiredWorkload = desiredWorkload;
        }
        if (currentWorkload != null) {
            this.currentWorkload = currentWorkload;
        }
        this.date = date;
    }

    /**
     * Converts a given {@code DailyPlan} into this class for Jackson use
     */
    public JsonAdaptedDailyPlan(DailyPlan source) {
        task.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));

        desiredWorkload = source.getDesiredWorkload();
        currentWorkload = source.getCurrentWorkload();
        date = source.getDate().toString();
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

        if (desiredWorkload < 0) {
            throw new IllegalValueException(String.format(NEGATIVE_FIELD_MESSAGE_FORMAT, "Desired Workload"));
        }

        if (currentWorkload < 0) {
            throw new IllegalValueException(String.format(NEGATIVE_FIELD_MESSAGE_FORMAT, "Current Workload"));
        }

        if (Objects.isNull(date) || date == "") {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        LocalDate actualDate = LocalDate.parse(date);

        return new DailyPlan(tasksList, desiredWorkload, currentWorkload, actualDate);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof JsonAdaptedDailyPlan)) {
            return false;
        } else {
            JsonAdaptedDailyPlan dp = (JsonAdaptedDailyPlan) other;
            return dp.task.equals(this.task)
                    && dp.desiredWorkload == this.desiredWorkload
                    && dp.currentWorkload == this.currentWorkload
                    && dp.date == this.date;
        }
    }
}
