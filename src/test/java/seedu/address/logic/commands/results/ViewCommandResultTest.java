package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.EDWARD;
import static seedu.address.testutil.TypicalPersons.HONG;

import org.junit.jupiter.api.Test;

public class ViewCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final ViewCommandResult VIEW_COMMAND_RESULT = new ViewCommandResult(FEEDBACK, EDWARD);

    @Test
    void equals_sameObject_true() {
        assertEquals(VIEW_COMMAND_RESULT, VIEW_COMMAND_RESULT);
    }

    @Test
    void equals_sameValues_true() {
        assertEquals(VIEW_COMMAND_RESULT, new ViewCommandResult(FEEDBACK, EDWARD));
    }

    @Test
    void equals_differentValues_false() {
        assertNotEquals(VIEW_COMMAND_RESULT, new ViewCommandResult(DIFFERENT, EDWARD));
        assertNotEquals(VIEW_COMMAND_RESULT, new ViewCommandResult(FEEDBACK, HONG));
        assertNotEquals(VIEW_COMMAND_RESULT, new ViewCommandResult(DIFFERENT, HONG));
    }

    @Test
    void isToShowNewPerson() {
        assertTrue(VIEW_COMMAND_RESULT.isToShowNewPerson());
    }

    @Test
    void hashCode_sameObject_true() {
        assertEquals(VIEW_COMMAND_RESULT.hashCode(), VIEW_COMMAND_RESULT.hashCode());
    }

    @Test
    void hashCode_sameValues_true() {
        assertEquals(VIEW_COMMAND_RESULT.hashCode(), new ViewCommandResult(FEEDBACK, EDWARD).hashCode());
    }

    @Test
    void hashCode_differentValues_false() {
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(), new ViewCommandResult(DIFFERENT, EDWARD).hashCode());
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(), new ViewCommandResult(FEEDBACK, HONG).hashCode());
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(), new ViewCommandResult(DIFFERENT, HONG).hashCode());
    }
}
