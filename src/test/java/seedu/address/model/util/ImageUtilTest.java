package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;


public class ImageUtilTest {

    @Test
    public void import_invalidPath_throwsCommandException() {
        String invalidPath = "invalidPath";
        assertThrows(CommandException.class, () -> ImageUtil.importImage(invalidPath));
    }

    @Test
    public void delete_invalidFileName_returnsFalse() {
        String invalidFile = "invalidFile";
        assertFalse(() -> {
            try {
                return ImageUtil.deleteImage(invalidFile);
            } catch (Exception e) {
                return true;
            }
        });
    }

    @Test
    public void valid_invalidPath_throwsCommandException() {
        String invalidPath = "invalidPath";
        assertFalse(() -> {
            try {
                return ImageUtil.isValidImage(invalidPath);
            } catch (Exception e) {
                return true;
            }
        });
    }

}
