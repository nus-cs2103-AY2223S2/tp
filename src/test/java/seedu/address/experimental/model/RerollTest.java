package seedu.address.experimental.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;

class RerollTest {

    private final Reroll reroll = new Reroll();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), reroll.getAllList());
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

    @Test
    public void getEntityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> reroll.getAllList().remove(0));
    }

}