package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.AMY;
import static seedu.address.testutil.TypicalEntities.BOB;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class RerollTest {

    private final Reroll reroll = new Reroll();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), reroll.getEntities().getEntityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reroll.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyReroll_replacesData() {
        Reroll newData = getTypicalReroll();
        reroll.resetData(newData);
        assertEquals(newData, reroll);
    }

    /**
     * Integration test with RerollAllEntities
     */
    @Test
    public void addEntities_integration_returnCorrectResult() {
        RerollAllEntities newData = (RerollAllEntities) getTypicalReroll().getEntities();
        reroll.resetData(getTypicalReroll());
        newData.addEntity(BOB);
        reroll.addEntity(BOB);
        assertTrue(newData.equals(reroll.getEntities()));

        reroll.addEntity(AMY);
        assertFalse(newData.equals(reroll.getEntities()));
    }
}
