package seedu.quickcontacts.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.quickcontacts.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        Assertions.assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

}
