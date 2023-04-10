package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.lecture.exceptions.DuplicateLectureException;
import seedu.address.model.lecture.exceptions.LectureNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class ModuleTest {

    private final Module module = TypicalModules.getCs2040s();

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> module.getTags().remove(new Tag("Math")));
    }

    @Test
    public void getLectureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> module.getLectureList().remove(0));
    }

    @Test
    public void isSameModule_sameObject_returnsTrue() {
        assertTrue(module.isSameModule(module));
    }

    @Test
    public void isSameModule_nullModule_returnsFalse() {
        assertFalse(module.isSameModule(null));
    }

    @Test
    public void isSameModule_sameCode_returnsTrue() {
        Module editedModule = new ModuleBuilder(module).withName("Random Name").withLectures().withTags().build();

        assertTrue(module.isSameModule(editedModule));
    }

    @Test
    public void isSameModule_differentCode_returnsFalse() {
        Module editedModule = new ModuleBuilder(module).withCode("CS2030S").build();

        assertFalse(module.isSameModule(editedModule));
    }

    public void hasLectureReadOnlyLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.hasLecture((ReadOnlyLecture) null));
    }

    public void hasLectureLectureName_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.hasLecture((LectureName) null));
    }

    @Test
    public void hasLecture_lectureNotInModule_returnsFalse() {
        assertFalse(module.hasLecture(TypicalLectures.getSt2334Topic1()));
    }

    @Test
    public void removeLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.removeLecture(null));
    }

    @Test
    public void removeLecture_lectureNotInModule_throwsLectureNotFoundException() {
        assertThrows(LectureNotFoundException.class, () -> module.removeLecture(TypicalLectures.getSt2334Topic1()));
    }

    @Test
    public void removeLecture_lectureInModule_moduleDoesNotHaveLecture() {
        Lecture lecture = TypicalLectures.getCs2040sWeek1();

        module.removeLecture(lecture);

        assertFalse(module.hasLecture(lecture));
    }

    @Test
    public void addLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.addLecture(null));
    }

    @Test
    public void addLecture_lectureNotInModule_moduleHasLecture() {
        Lecture lecture = TypicalLectures.getSt2334Topic1();

        module.addLecture(lecture);

        assertTrue(module.hasLecture(lecture));
    }

    @Test
    public void addLecture_lectureInModule_throwsDuplicateLectureException() {
        assertThrows(DuplicateLectureException.class, () -> module.addLecture(TypicalLectures.getCs2040sWeek1()));
    }

    @Test
    public void setLecture_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                module.setLecture(null, TypicalLectures.getSt2334Topic1()));
    }

    @Test
    public void setLecture_nullEditedLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                module.setLecture(TypicalLectures.getCs2040sWeek1(), null));
    }

    @Test
    public void setLecture_targetNotInModule_throwsLectureNotFoundException() {
        assertThrows(LectureNotFoundException.class, () ->
                module.setLecture(TypicalLectures.getSt2334Topic1(), TypicalLectures.getSt2334Topic2()));
    }

    @Test
    public void setLecture_editedLectureIsDuplicate_throwsDuplicateLectureException() {
        assertThrows(DuplicateLectureException.class, () ->
                module.setLecture(TypicalLectures.getCs2040sWeek1(), TypicalLectures.getCs2040sWeek2()));
    }

    @Test
    public void setLecture_editedLectureHasNoChange_lectureNoChange() {
        Lecture lecture = TypicalLectures.getCs2040sWeek1();

        module.setLecture(lecture, lecture);

        assertTrue(module.hasLecture(lecture));
    }

    @Test
    public void setLecture_validTargetAndEditedLecture_lectureReplaced() {
        Lecture originalLecture = TypicalLectures.getCs2040sWeek1();
        Lecture editedLecture = TypicalLectures.getSt2334Topic1();

        module.setLecture(originalLecture, editedLecture);

        assertFalse(module.hasLecture(originalLecture));
        assertTrue(module.hasLecture(editedLecture));
    }

    @Test
    public void getLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.getLecture(null));
    }

    @Test
    public void getLecture_noLectureWithName_returnsNull() {
        assertEquals(module.getLecture(TypicalLectures.getSt2334Topic1().getName()), null);
    }

    @Test
    public void getLecture_hasLectureWithName_returnsLecture() {
        assertEquals(module.getLecture(TypicalLectures.getCs2040sWeek1().getName()), TypicalLectures.getCs2040sWeek1());
    }

    @Test
    public void compareTo() {
        Module cs2040s = TypicalModules.getCs2040s();
        Module st2334 = TypicalModules.getSt2334();

        assertTrue(cs2040s.compareTo(cs2040s) == 0);
        assertTrue(cs2040s.compareTo(st2334) < 0);
        assertTrue(st2334.compareTo(cs2040s) > 0);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module moduleCopy = new ModuleBuilder(module).build();

        assertTrue(module.equals(moduleCopy));

        // same object -> returns true
        assertTrue(module.equals(module));

        // null -> returns false
        assertFalse(module.equals(null));

        // different type -> returns false
        assertFalse(module.equals(42));

        // different code -> returns false
        Module editedModule = new ModuleBuilder(module).withCode("CS2030S").build();
        assertFalse(module.equals(editedModule));

        // different name -> returns false
        editedModule = new ModuleBuilder(module).withName("Life is Bleak").build();
        assertFalse(module.equals(editedModule));

        // diferent tags -> returns false
        editedModule = new ModuleBuilder(module).withTags("Lorem").build();
        assertFalse(module.equals(editedModule));

        // different lectures -> returns false
        editedModule = new ModuleBuilder(module).withLectures().build();
        assertFalse(module.equals(editedModule));
    }

}
