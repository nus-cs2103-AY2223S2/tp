package seedu.task.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.exceptions.CommandException;

public class IndexListTest {

    @Test
    public void getZeroBasedIndex() {
        IndexList list = new IndexList();
        list.add(Index.fromOneBased(1));
        list.add(Index.fromOneBased(2));
        list.add(Index.fromOneBased(3));

        assertEquals(2, list.getZeroBasedIndex(2));
        assertEquals(1, list.getZeroBasedIndex(1));
    }

    @Test
    public void checkValidIndex_invalidIndex_throwsCommandException() {
        IndexList list = new IndexList();
        list.add(Index.fromOneBased(1));
        list.add(Index.fromOneBased(2));
        list.add(Index.fromOneBased(3));

        // index deleting from is greater than list size
        assertThrows(CommandException.class, () -> list.isValidIndex(2));

        // index entered in non-ascending order
        list.add(Index.fromOneBased(2));
        assertThrows(CommandException.class, () -> list.isValidIndex(4));
    }

    @Test
    public void checkValidIndex_validIndexList_throwsCommandException() throws CommandException {
        IndexList list = new IndexList();
        list.add(Index.fromOneBased(1));
        list.add(Index.fromOneBased(2));
        list.add(Index.fromOneBased(3));

        assertTrue(list.isValidIndex(3));
    }

    @Test
    public void modifyForDelete() {
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
    public void fromOneBasedList() {
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

        IndexList checklist3 = new IndexList();
        checklist3.add(Index.fromZeroBased(0));
        checklist3.add(Index.fromZeroBased(2));

        // same values -> returns true
        assertTrue(checklist1.equals(checklist2));

        // same object -> returns true
        assertTrue(checklist2.equals(checklist2));

        // null -> returns false
        assertFalse(checklist1.equals(null));

        // different number of index -> returns false
        IndexList checklist4 = new IndexList();
        checklist4.add(Index.fromOneBased(2));
        assertFalse(checklist4.equals(checklist1));

        // same number of index, different values -> return false
        assertFalse(checklist2.equals(checklist3));
    }
}
