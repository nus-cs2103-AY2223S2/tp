package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_10AM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_2PM_2HR_LESSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ModuleTest {
    @Test
    public void initialise_onlyModuleCode_success() {
        assertDoesNotThrow(() -> new Module("CS2030S"));
        Module module = new Module("CS2030S");
        assertEquals(module.getModuleCode(), "CS2030S");
        assertTrue(module.getEnrolledLessons().isEmpty());
    }

    @Test
    public void initialise_withEnrolledLessons_success() {
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(TUESDAY_10AM_1HR_LESSON);
        lessonList.add(WEDNESDAY_2PM_2HR_LESSON);
        assertDoesNotThrow(() -> new Module(lessonList, "CS2108"));
        Module module = new Module(lessonList, "CS2108");
        assertEquals(2, module.getEnrolledLessons().size());
        assertEquals("CS2108", module.getModuleCode());
    }

    @Test
    public void equality_withSameObjectReference_equals() {
        Module module1 = new Module("CS2030S");
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(TUESDAY_10AM_1HR_LESSON);
        lessonList.add(WEDNESDAY_2PM_2HR_LESSON);
        Module module2 = new Module(lessonList, "CS2108");
        assertEquals(module1, module1);
        assertEquals(module2, module2);
    }

    @Test
    public void equality_withNull_equals() {
        Module module1 = new Module("CS2030S");
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(TUESDAY_10AM_1HR_LESSON);
        lessonList.add(WEDNESDAY_2PM_2HR_LESSON);
        Module module2 = new Module(lessonList, "CS2108");
        assertNotNull(module1);
        assertNotNull(module2);
        assertNotEquals(module1, null);
        assertNotEquals(module2, null);
    }
}
