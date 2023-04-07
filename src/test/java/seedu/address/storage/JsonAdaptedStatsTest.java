package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.entity.Stats;

class JsonAdaptedStatsTest {
    private static final int VALID_INT = 3;
    private static final int VALID_STRENGTH = -4;
    private static final int VALID_DEX = 0;

    @Test
    public void toModelType_validStatsDetails_returnsStats() throws Exception {
        Stats validStats = new Stats(VALID_STRENGTH, VALID_DEX, VALID_INT);
        JsonAdaptedStats stats = new JsonAdaptedStats(validStats);
        assertEquals(validStats, stats.toModalType());
    }
}
