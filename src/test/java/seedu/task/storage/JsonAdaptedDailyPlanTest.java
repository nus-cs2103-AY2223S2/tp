package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDailyPlans.APRIL26;
import static seedu.task.testutil.TypicalDailyPlans.MAY1;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.task.commons.exceptions.IllegalValueException;

public class JsonAdaptedDailyPlanTest {

    private static final long VALID_DESIRED_WORKLOAD = 50;
    private static final long INVALID_DESIRED_WORKLOAD = -1;
    private static final long VALID_CURRENT_WORKLOAD = 30;
    private static final long INVALID_CURRENT_WORKLOAD = -1;
    private static final long ZERO_WORKLOAD = 0;
    private static final String VALID_DATE = "2023-04-12";
    private static final List<JsonAdaptedTask> VALID_TASKS = MAY1.getTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedDailyPlan dp = new JsonAdaptedDailyPlan(APRIL26);
        assertEquals(APRIL26, dp.toModelType());
    }

    @Test
    public void toModelType_invalidDesiredWorkload_throwsIllegalValueException() {
        JsonAdaptedDailyPlan dp = new JsonAdaptedDailyPlan(VALID_TASKS, INVALID_DESIRED_WORKLOAD,
                VALID_CURRENT_WORKLOAD, VALID_DATE);
        String expectedMessage = "DailyPlan's Desired Workload field must be non-negative!";
        assertThrows(IllegalValueException.class, expectedMessage, dp::toModelType);
    }

    @Test
    public void toModelType_nullDesiredWorkload_defaultWorkload() {
        JsonAdaptedDailyPlan dp = new JsonAdaptedDailyPlan(VALID_TASKS, null,
                VALID_CURRENT_WORKLOAD, VALID_DATE);
        JsonAdaptedDailyPlan defaultDP = new JsonAdaptedDailyPlan(VALID_TASKS, ZERO_WORKLOAD,
                VALID_CURRENT_WORKLOAD, VALID_DATE);
        assertEquals(dp, defaultDP);
    }

    @Test
    public void toModelType_invalidCurrentWorkload_throwsIllegalValueException() {
        JsonAdaptedDailyPlan dp = new JsonAdaptedDailyPlan(VALID_TASKS, VALID_DESIRED_WORKLOAD,
                INVALID_CURRENT_WORKLOAD, VALID_DATE);
        String expectedMessage = "DailyPlan's Current Workload field must be non-negative!";
        assertThrows(IllegalValueException.class, expectedMessage, dp::toModelType);
    }

    @Test
    public void toModelType_nullCurrentWorkload_defaultWorkload() {
        JsonAdaptedDailyPlan dp = new JsonAdaptedDailyPlan(VALID_TASKS, VALID_DESIRED_WORKLOAD,
                null, VALID_DATE);
        JsonAdaptedDailyPlan defaultDP = new JsonAdaptedDailyPlan(VALID_TASKS, VALID_DESIRED_WORKLOAD,
                ZERO_WORKLOAD, VALID_DATE);
        assertEquals(dp, defaultDP);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedDailyPlan dp = new JsonAdaptedDailyPlan(VALID_TASKS, VALID_DESIRED_WORKLOAD,
                VALID_CURRENT_WORKLOAD, null);
        String expectedMessage = "DailyPlan's Date field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, dp::toModelType);
    }
}
