package seedu.event.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MarkTest {
    @Test
    public void isSameMark() {
        Mark undoneMark = new Mark();
        Mark doneMarkOne = new Mark();
        doneMarkOne.setDone();
        Mark doneMarkTwo = new Mark();
        doneMarkTwo.setDone();
        // same object -> returns true
        assertTrue(undoneMark.equals(undoneMark));

        // null -> returns false
        assertFalse(undoneMark.equals(null));

        // different mark -> returns false
        assertFalse(undoneMark.equals(doneMarkOne));

        // marks that have the same status -> returns true
        assertTrue(undoneMark.equals(new Mark()));

        // marks that have the same status -> returns true
        assertTrue(doneMarkOne.equals(doneMarkTwo));

        // Test Unmark method
        doneMarkOne.setUndone();

        // same object -> returns true
        assertTrue(doneMarkOne.equals(doneMarkOne));

        // null -> returns false
        assertFalse(doneMarkOne.equals(null));

        // different mark -> returns false
        assertFalse(doneMarkOne.equals(doneMarkTwo));

        // marks that have the same status -> returns true
        assertTrue(doneMarkOne.equals(new Mark()));

        doneMarkTwo.setUndone();
        // marks that have the same status -> returns true
        assertTrue(doneMarkOne.equals(doneMarkTwo));
    }

    @Test
    public void equals() {
        Mark undoneMark = new Mark();
        Mark doneMark = new Mark();
        doneMark.setDone();

        // same values -> returns true
        assertTrue(undoneMark.toString().equals("[ ]"));

        // same values -> returns true
        assertTrue(doneMark.toString().equals("[X]"));

        doneMark.setUndone();
        // same values -> returns true
        assertTrue(doneMark.toString().equals("[ ]"));
    }
}
