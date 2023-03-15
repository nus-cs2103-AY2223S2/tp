package seedu.fitbook.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
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
     * Returns the middle index of the client in the {@code model}'s client list.
     */
    public static Index getMidIndex(FitBookModel model) {
        return Index.fromOneBased(model.getFilteredClientList().size() / 2);
    }

    /**
     * Returns the last index of the client in the {@code model}'s client list.
     */
    public static Index getLastIndex(FitBookModel model) {
        return Index.fromOneBased(model.getFilteredClientList().size());
    }

    /**
     * Returns the client in the {@code model}'s client list at {@code index}.
     */
    public static Client getClient(FitBookModel model, Index index) {
        return model.getFilteredClientList().get(index.getZeroBased());
    }

    /**
     * Returns the routine in the {@code model}'s routine list at {@code index}.
     */
    public static Routine getRoutine(FitBookModel model, Index index) {
        return model.getFilteredRoutineList().get(index.getZeroBased());
    }
}
