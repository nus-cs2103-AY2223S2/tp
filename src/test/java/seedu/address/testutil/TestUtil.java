package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} EndTime the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the tutee in the {@code model}'s tutee list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredTuteeList().size() / 2);
    }

    /**
     * Returns the last index of the tutee in the {@code model}'s tutee list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredTuteeList().size());
    }

    /**
     * Returns the tutee in the {@code model}'s tutee list at {@code index}.
     */
    public static Tutee getPerson(Model model, Index index) {
        return model.getFilteredTuteeList().get(index.getZeroBased());
    }
}
