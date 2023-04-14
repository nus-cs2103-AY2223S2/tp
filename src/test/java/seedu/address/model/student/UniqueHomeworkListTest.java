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
        uniqueHomeworkList.addHomework(homework1);
        assertTrue(uniqueHomeworkList.contains(homework1));
    }

    @Test
    public void contains_homeworkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueHomeworkList.addHomework(homework1);
        Homework editedHomework = new Homework("Complete math assignment", LocalDateTime.of(2022, 4, 1, 23, 59));
        assertTrue(uniqueHomeworkList.contains(editedHomework));
    }

    @Test
    public void add_nullHomework_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHomeworkList.addHomework(null));
    }

    @Test
    public void add_duplicateHomework_throwsDuplicateHomeworkException() {
        uniqueHomeworkList.addHomework(homework1);
        assertThrows(DuplicateEntryException.class, () -> uniqueHomeworkList.addHomework(homework1));
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
        uniqueHomeworkList.addHomework(homework1);
        uniqueHomeworkList.setHomework(homework1, homework1);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.addHomework(homework1);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void setHomework_editedHomeworkHasSameIdentity_success() {
        uniqueHomeworkList.addHomework(homework1);
        Homework editedHomework = new Homework("Complete math assignment", LocalDateTime.of(2022, 4, 1, 23, 59));
        uniqueHomeworkList.setHomework(homework1, editedHomework);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.addHomework(editedHomework);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void remove_homeworkInList_success() {
        uniqueHomeworkList.addHomework(homework1);
        uniqueHomeworkList.removeHomework(homework1);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void remove_homeworkNotInList_throwsHomeworkNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueHomeworkList.removeHomework(homework1));
    }

    @Test
    public void setHomework_editedHomeworkHasDifferentIdentity_success() {
        uniqueHomeworkList.addHomework(homework1);
        uniqueHomeworkList.setHomework(homework1, homework2);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.addHomework(homework2);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void setHomework_editedHomeworkHasDifferentDeadline_success() {
        uniqueHomeworkList.addHomework(homework1);
        Homework editedHomework = new Homework("Complete math assignment", LocalDateTime.of(2022, 5, 1, 23, 59));
        uniqueHomeworkList.setHomework(homework1, editedHomework);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.addHomework(editedHomework);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }

    @Test
    public void setHomework_editedHomeworkHasDifferentDescription_success() {
        uniqueHomeworkList.addHomework(homework1);
        Homework editedHomework = new Homework("Do physics homework", LocalDateTime.of(2022, 4, 1, 23, 59));
        uniqueHomeworkList.setHomework(homework1, editedHomework);
        UniqueHomeworkList expectedUniqueHomeworkList = new UniqueHomeworkList();
        expectedUniqueHomeworkList.addHomework(editedHomework);
        assertEquals(expectedUniqueHomeworkList, uniqueHomeworkList);
    }
}
