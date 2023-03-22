package seedu.careflow.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.careflow.commons.core.index.Index;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.patient.Patient;

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
     * Returns the middle index of the patient in the {@code careflowModel}'s patient list.
     */
    public static Index getMidIndex(CareFlowModel careflowModel) {
        return Index.fromOneBased(careflowModel.getFilteredPatientList().size() / 2);
    }

    /**
     * Returns the last index of the patient in the {@code careflowModel}'s patient list.
     */
    public static Index getLastIndex(CareFlowModel careflowModel) {
        return Index.fromOneBased(careflowModel.getFilteredPatientList().size());
    }

    /**
     * Returns the patient in the {@code careflowModel}'s patient list at {@code index}.
     */
    public static Patient getPerson(CareFlowModel careflowModel, Index index) {
        return careflowModel.getFilteredPatientList().get(index.getZeroBased());
    }
}
