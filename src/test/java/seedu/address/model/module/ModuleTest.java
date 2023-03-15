package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.lecture.exceptions.DuplicateLectureException;
import seedu.address.model.lecture.exceptions.LectureNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class ModuleTest {

    private final Module module = new ModuleBuilder(TypicalModules.CS2040S).build();

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

    public void hasLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.hasLecture(null));
    }

    @Test
    public void hasLecture_lectureNotInModule_returnsFalse() {
        assertFalse(module.hasLecture(TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void removeLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.removeLecture(null));
    }

    @Test
    public void removeLecture_lectureNotInModule_throwsLectureNotFoundException() {
        assertThrows(LectureNotFoundException.class, () -> module.removeLecture(TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void removeLecture_lectureInModule_moduleDoesNotHaveLecture() {
        module.removeLecture(TypicalLectures.CS2040S_WEEK_1);

        assertFalse(module.hasLecture(TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void addLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.addLecture(null));
    }

    @Test
    public void addLecture_lectureNotInModule_moduleHasLecture() {
        module.addLecture(TypicalLectures.ST2334_TOPIC_1);

        assertTrue(module.hasLecture(TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void addLecture_lectureInModule_throwsDuplicateLectureException() {
        assertThrows(DuplicateLectureException.class, () -> module.addLecture(TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void setLecture_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                module.setLecture(null, TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void setLecture_nullEditedLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                module.setLecture(TypicalLectures.CS2040S_WEEK_1, null));
    }

    @Test
    public void setLecture_targetNotInModule_throwsLectureNotFoundException() {
        assertThrows(LectureNotFoundException.class, () ->
                module.setLecture(TypicalLectures.ST2334_TOPIC_1, TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void setLecture_editedLectureIsDuplicate_throwsDuplicateLectureException() {
        assertThrows(DuplicateLectureException.class, () ->
                module.setLecture(TypicalLectures.CS2040S_WEEK_1, TypicalLectures.CS2040S_WEEK_2));
    }

    @Test
    public void setLecture_editedLectureHasNoChange_lectureNoChange() {
        module.setLecture(TypicalLectures.CS2040S_WEEK_1, TypicalLectures.CS2040S_WEEK_1);

        assertTrue(module.hasLecture(TypicalLectures.CS2040S_WEEK_1));
    }

    @Test
    public void setLecture_validTargetAndEditedLecture_lectureReplaced() {
        module.setLecture(TypicalLectures.CS2040S_WEEK_1, TypicalLectures.ST2334_TOPIC_1);

        assertFalse(module.hasLecture(TypicalLectures.CS2040S_WEEK_1));
        assertTrue(module.hasLecture(TypicalLectures.ST2334_TOPIC_1));
    }

    @Test
    public void getLecture_nullLecture_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> module.getLecture(null));
    }

    @Test
    public void getLecture_noLectureWithName_returnsNull() {
        assertEquals(module.getLecture(TypicalLectures.ST2334_TOPIC_1.getName()), null);
    }

    @Test
    public void getLecture_hasLectureWithName_returnsLecture() {
        assertEquals(module.getLecture(TypicalLectures.CS2040S_WEEK_1.getName()), TypicalLectures.CS2040S_WEEK_1);
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
