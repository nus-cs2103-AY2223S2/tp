package seedu.sudohr.model.leave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.leave.exceptions.DuplicateLeaveException;
import seedu.sudohr.model.leave.exceptions.LeaveNotFoundException;
import seedu.sudohr.testutil.LeaveBuilder;

public class UniqueLeaveListTest {
    private final UniqueLeaveList uniqueLeaveList = new UniqueLeaveList();

    @Test
    public void contains_nullLeave_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.contains(null));
    }

    @Test
    public void contains_leaveNotInList_returnsFalse() {
        assertFalse(uniqueLeaveList.contains(LEAVE_TYPE_1));
    }

    @Test
    public void contains_leaveInList_returnsTrue() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        assertTrue(uniqueLeaveList.contains(LEAVE_TYPE_1));
    }

    @Test
    public void contains_leaveWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        Leave editedLeave = new LeaveBuilder(LEAVE_TYPE_1).build();
        assertTrue(uniqueLeaveList.contains(editedLeave));
    }

    @Test
    public void add_nullLeave_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.addLeave(null));
    }

    @Test
    public void add_duplicateLeave_throwsDuplicateLeaveException() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        assertThrows(DuplicateLeaveException.class, () -> uniqueLeaveList.addLeave(LEAVE_TYPE_1));
    }

    @Test
    public void setLeave_nullTargetLeave_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.setLeave(null,
                LEAVE_TYPE_1));
    }

    @Test
    public void setLeave_nullEditedLeave_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.setLeave(LEAVE_TYPE_1, null));
    }

    @Test
    public void setLeave_targetLeaveNotInList_throwsLeavetNotFoundException() {
        assertThrows(LeaveNotFoundException.class, () -> uniqueLeaveList.setLeave(
                LEAVE_TYPE_1,
                LEAVE_TYPE_1));
    }

    @Test
    public void setLeave_editedLeaveIsSameLeave_success() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        uniqueLeaveList.setLeave(LEAVE_TYPE_1, LEAVE_TYPE_1);
        UniqueLeaveList expectedUniqueLeaveList = new UniqueLeaveList();
        expectedUniqueLeaveList.addLeave(LEAVE_TYPE_1);
        assertEquals(expectedUniqueLeaveList, uniqueLeaveList);
    }

    @Test
    public void setLeave_editedLeaveHasSameIdentity_success() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        Leave editedLeave = new LeaveBuilder(LEAVE_TYPE_1).build();
        uniqueLeaveList.setLeave(LEAVE_TYPE_1, editedLeave);
        UniqueLeaveList expectedUniqueLeaveList = new UniqueLeaveList();
        expectedUniqueLeaveList.addLeave(editedLeave);
        assertEquals(expectedUniqueLeaveList, uniqueLeaveList);
    }

    @Test
    public void setLeave_editedLeaveHasDifferentIdentity_success() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        uniqueLeaveList.setLeave(LEAVE_TYPE_1, LEAVE_TYPE_2);
        UniqueLeaveList expectedUniqueLeaveList = new UniqueLeaveList();
        expectedUniqueLeaveList.addLeave(LEAVE_TYPE_2);
        assertEquals(expectedUniqueLeaveList, uniqueLeaveList);
    }

    @Test
    public void setLeave_editedLeaveHasNonUniqueIdentity_throwsDuplicateLeaveException() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        uniqueLeaveList.addLeave(LEAVE_TYPE_2);
        assertThrows(DuplicateLeaveException.class, () -> uniqueLeaveList.setLeave(
                LEAVE_TYPE_2,
                LEAVE_TYPE_1));
    }

    @Test
    public void remove_nullLeave_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.remove(null));
    }

    @Test
    public void remove_leaveDoesNotExist_throwsLeaveNotFoundException() {
        assertThrows(LeaveNotFoundException.class, () -> uniqueLeaveList.remove(LEAVE_TYPE_1));
    }

    @Test
    public void remove_existingLeave_removesLeave() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        uniqueLeaveList.remove(LEAVE_TYPE_1);
        UniqueLeaveList expectedUniqueLeaveList = new UniqueLeaveList();
        assertEquals(expectedUniqueLeaveList, uniqueLeaveList);
    }

    @Test
    public void setLeaves_nullUniqueLeaveList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.setLeaves((UniqueLeaveList) null));
    }

    @Test
    public void setLeaves_uniqueLeaveList_replacesOwnListWithProvidedUniqueLeaveList() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        UniqueLeaveList expectedUniqueLeaveList = new UniqueLeaveList();
        expectedUniqueLeaveList.addLeave(LEAVE_TYPE_1);
        uniqueLeaveList.setLeaves(expectedUniqueLeaveList);
        assertEquals(expectedUniqueLeaveList, uniqueLeaveList);
    }

    @Test
    public void setLeaves_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLeaveList.setLeaves((List<Leave>) null));
    }

    @Test
    public void setLeaves_list_replacesOwnListWithProvidedList() {
        uniqueLeaveList.addLeave(LEAVE_TYPE_1);
        List<Leave> leaveList = Collections.singletonList(LEAVE_TYPE_2);
        uniqueLeaveList.setLeaves(leaveList);
        UniqueLeaveList expectedUniqueLeaveList = new UniqueLeaveList();
        expectedUniqueLeaveList.addLeave(LEAVE_TYPE_2);
        assertEquals(expectedUniqueLeaveList, uniqueLeaveList);
    }

    @Test
    public void setLeaves_listWithDuplicateLeaves_throwsDuplicateLeaveException() {
        List<Leave> listWithDuplicateLeaves = Arrays.asList(LEAVE_TYPE_1, LEAVE_TYPE_1);
        assertThrows(DuplicateLeaveException.class, () -> uniqueLeaveList.setLeaves(listWithDuplicateLeaves));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueLeaveList.asUnmodifiableObservableList().remove(0));
    }
}
