package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalFields.ORC_STATS;

import org.junit.jupiter.api.Test;

import seedu.address.model.entity.Stats;

class JsonAdaptedStatsTest {

    @Test
    public void toModelType_validStatsDetails_returnsStats() throws Exception {
        Stats validStats = ORC_STATS;
        JsonAdaptedStats stats = new JsonAdaptedStats(validStats);
        assertEquals(validStats, stats.toModalType());
    }
}
