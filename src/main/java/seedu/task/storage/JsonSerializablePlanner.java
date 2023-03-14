package seedu.task.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.Planner;
import seedu.task.model.ReadOnlyPlanner;
import seedu.task.model.calendar.DailyPlan;

/**
 * An immutable Planner that is serializable to JSON format.
 */
@JsonRootName(value = "planner")
public class JsonSerializablePlanner {
    private final List<JsonAdaptedDailyPlan> plans = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlanner} with the given tasks.
     */
    @JsonCreator
    public JsonSerializablePlanner(@JsonProperty("dailyplan") List<JsonAdaptedDailyPlan> plans) {
        this.plans.addAll(plans);
    }

    /**
     * Converts a given {@code ReadOnlyPlanner} into this class to use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskBook}.
     */
    public JsonSerializablePlanner(ReadOnlyPlanner source) {
        plans.addAll(source.getDailyPlanList().stream().map(JsonAdaptedDailyPlan::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Planner toModelType() throws IllegalValueException {
        Planner planner = new Planner();
        for (JsonAdaptedDailyPlan jsonAdaptedDailyPlan : plans) {
            DailyPlan plan = jsonAdaptedDailyPlan.toModelType();
            planner.addDailyPlan(plan);
        }
        return planner;
    }

}
