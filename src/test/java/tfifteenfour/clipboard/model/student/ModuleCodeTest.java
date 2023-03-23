package tfifteenfour.clipboard.model.student;

import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.model.course.Course;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Course(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Course(invalidModuleCode));
    }

    @Test
    public void isValidModuleCode() {
        // null module code
        assertThrows(NullPointerException.class, () -> Course.isValidModuleCode(null));
    }

}
