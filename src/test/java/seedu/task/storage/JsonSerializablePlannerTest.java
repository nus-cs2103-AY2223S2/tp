package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.JsonUtil;
import seedu.task.model.Planner;
import seedu.task.testutil.TypicalDailyPlans;

public class JsonSerializablePlannerTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializablePlannerTest");
    private static final Path TYPICAL_DAILY_PLANS_FILE = TEST_DATA_FOLDER.resolve("typicalDailyPlansPlanner.json");
    private static final Path INVALID_DAILY_PLANS_FILE = TEST_DATA_FOLDER.resolve("invalidDailyPlansPlanner.json");

    @Test
    public void toModelType_typicalDailyPlansFile_success() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(TYPICAL_DAILY_PLANS_FILE,
                JsonSerializablePlanner.class).get();
        Planner plannerFromFile = dataFromFile.toModelType();
        Planner typicalDailyPlansPlanner = TypicalDailyPlans.getTypicalPlanner();
        assertEquals(plannerFromFile, typicalDailyPlansPlanner);
    }

    @Test
    public void toModelType_invalidDailyPlansFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(INVALID_DAILY_PLANS_FILE,
                JsonSerializablePlanner.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
