package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.student.exceptions.DuplicateEntryException;
import seedu.address.model.student.exceptions.EntryNotFoundException;

public class UniqueHomeworkListTest {

    private UniqueHomeworkList uniqueHomeworkList;
    private Homework homework1;
    private Homework homework2;

    @BeforeEach
    public void setUp() {
        uniqueHomeworkList = new UniqueHomeworkList();
        homework1 = new Homework("Complete math assignment", LocalDateTime.of(2022, 4, 1, 23, 59));
        homework2 = new Homework("Write an essay", LocalDateTime.of(2022, 5, 15, 12, 0));
    }

    @Test
    public void contains_homeworkNotInList_returnsFalse() {
        assertFalse(uniqueHomeworkList.contains(homework1));
    }

    @Test
    public void contains_homeworkInList_returnsTrue() {
        uniqueHomeworkList.add(homework1);
        assertTrue(uniqueHomeworkList.contains(homework1));
    }

    @Test
    public void contains_homeworkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueHomeworkList.add(homework1);
        Homework editedHomework = new Homework("Complete math assignment", LocalDateTime.of(2022, 4, 1, 23, 59));
        assertTrue(uniqueHomeworkList.contains(editedHomework));
    }

    @Test
    public void add_nullHomework_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHomeworkList.add(null));
    }

    @Test
    public void add_duplicateHomework_throwsDuplicateHomeworkException() {
        uniqueHomeworkList.add(homework1);
        assertThrows(DuplicateEntryException.class, () -> uniqueHomeworkList.add(homework1));
    }

    @Test
    public void setHomework_nullTargetHomework_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHomeworkList.setHomework(null, homework1));
    }

    @Test
    public void setHomework_nullEditedHomework_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHomeworkList.setHomework(homework1, null));
    }

    @Test
    public void setHomework_targetHomeworkNotInList_throwsHomeworkNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueHomeworkList.setHomework(homework1, homework2));
    }

    @Test
    public void setHomework_editedHomeworkIsSameHomework_success() {
        uniqueHomeworkList.add(homework1);
        uniqueHomeworkList.setHomework(homework1, homework1);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.add(homework1);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void setHomework_editedHomeworkHasSameIdentity_success() {
        uniqueHomeworkList.add(homework1);
        Homework editedHomework = new Homework("Complete math assignment", LocalDateTime.of(2022, 4, 1, 23, 59));
        uniqueHomeworkList.setHomework(homework1, editedHomework);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.add(editedHomework);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void remove_homeworkInList_success() {
        uniqueHomeworkList.add(homework1);
        uniqueHomeworkList.remove(homework1);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void remove_homeworkNotInList_throwsHomeworkNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueHomeworkList.remove(homework1));
    }

    @Test
    public void setHomework_editedHomeworkHasDifferentIdentity_success() {
        uniqueHomeworkList.add(homework1);
        uniqueHomeworkList.setHomework(homework1, homework2);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.add(homework2);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void setHomework_editedHomeworkHasDifferentDeadline_success() {
        uniqueHomeworkList.add(homework1);
        Homework editedHomework = new Homework("Complete math assignment", LocalDateTime.of(2022, 5, 1, 23, 59));
        uniqueHomeworkList.setHomework(homework1, editedHomework);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.add(editedHomework);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void setHomework_editedHomeworkHasDifferentDescription_success() {
        uniqueHomeworkList.add(homework1);
        Homework editedHomework = new Homework("Do physics homework", LocalDateTime.of(2022, 4, 1, 23, 59));
        uniqueHomeworkList.setHomework(homework1, editedHomework);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.add(editedHomework);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }
}
