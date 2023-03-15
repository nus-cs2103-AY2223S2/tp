package seedu.task.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.exceptions.CommandException;

public class IndexListTest {

    @Test
    public void getCorrectIndex() {
        IndexList list = new IndexList();
        list.add(Index.fromOneBased(1));
        list.add(Index.fromOneBased(2));
        list.add(Index.fromOneBased(3));

        assertEquals(2, list.getZeroBasedIndex(2));
        assertEquals(1, list.getZeroBasedIndex(1));
    }

    @Test
    public void checkValidIndex() {
        IndexList list = new IndexList();
        list.add(Index.fromOneBased(1));
        list.add(Index.fromOneBased(2));
        list.add(Index.fromOneBased(3));

        assertThrows(CommandException.class, () -> list.isValidIndex(2));
    }

    @Test
    public void modifyDeleteIndex() {
        IndexList referenceList = new IndexList();
        referenceList.add(Index.fromOneBased(1));
        referenceList.add(Index.fromOneBased(1));
        referenceList.add(Index.fromOneBased(1));

        IndexList testedList = new IndexList();
        testedList.add(Index.fromOneBased(1));
        testedList.add(Index.fromOneBased(2));
        testedList.add(Index.fromOneBased(3));
        testedList.modifyForDelete();

        assertEquals(referenceList, testedList);
    }

    @Test
    public void modifyFromOneBasedList() {
        IndexList expectedList = new IndexList();
        expectedList.add(Index.fromOneBased(1));
        expectedList.add(Index.fromOneBased(2));
        expectedList.add(Index.fromOneBased(3));

        IndexList inputList = new IndexList();
        int[] testIndices = {1, 2, 3};
        IndexList outputList = inputList.fromOneBasedList(testIndices);

        assertEquals(expectedList, outputList);
    }

    @Test
    public void equals() {
        IndexList checklist1 = new IndexList();
        checklist1.add(Index.fromOneBased(1));
        checklist1.add(Index.fromOneBased(2));

        IndexList checklist2 = new IndexList();
        checklist2.add(Index.fromZeroBased(0));
        checklist2.add(Index.fromZeroBased(1));

        // same values -> returns true
        assertTrue(checklist1.equals(checklist2));

        // same object -> returns true
        assertTrue(checklist2.equals(checklist2));

        // null -> returns false
        assertFalse(checklist1.equals(null));

        // different index -> returns false
        IndexList checklist3 = new IndexList();
        checklist3.add(Index.fromOneBased(2));
        assertFalse(checklist3.equals(checklist1));
    }
}
