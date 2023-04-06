package fasttrack.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fasttrack.commons.core.index.Index;
import fasttrack.model.Model;
import fasttrack.model.expense.Expense;

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
     * Returns the middle index of the expense in the {@code model}'s expense list.
     */
    public static Index getMidIndex(Model dataModel) {
        return Index.fromOneBased(dataModel.getFilteredExpenseList().size() / 2);
    }

    /**
     * Returns the last index of the expense in the {@code model}'s expense list.
     */
    public static Index getLastIndex(Model dataModel) {
        return Index.fromOneBased(dataModel.getFilteredExpenseList().size());
    }

    /**
     * Returns the expense in the {@code model}'s expense list at {@code index}.
     */
    public static Expense getPerson(Model dataModel, Index index) {
        return dataModel.getFilteredExpenseList().get(index.getZeroBased());
    }
}
