package seedu.vms.testutil;

import static seedu.vms.logic.parser.CliSyntax.DELIMITER;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.parser.Prefix;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;
import seedu.vms.model.patient.Patient;

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
     * Returns the middle index of the patient in the {@code model}'s patient list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPatientList().size() / 2);
    }

    /**
     * Returns the last index of the patient in the {@code model}'s patient list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPatientList().size());
    }

    /**
     * Returns the patient in the {@code model}'s patient list at {@code index}.
     */
    public static Patient getPatient(Model model, Index index) {
        return model.getFilteredPatientList().get(index.getZeroBased()).getValue();
    }

    /**
     * Returns a set of {@code GroupName} of the specified size.
     */
    public static HashSet<GroupName> generateGroupSet(int size) {
        HashSet<GroupName> grpSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            grpSet.add(new GroupName(String.valueOf(i)));
        }
        return grpSet;
    }

    /**
     * Returns the command String to generate the given group set.
     */
    public static String toCommandString(HashSet<GroupName> groupSet) {
        return groupSet.toString().replaceAll("[\\[\\]]", "");
    }

    /**
     * Returns the String[] to generate the given group set.
     */
    public static String[] toStringArr(HashSet<GroupName> groupSet) {
        return groupSet.stream()
                .map(GroupName::toString)
                .toArray(String[]::new);
    }

    /**
     * Returns the String with prefix to generate a command to test.
     */
    public static String toParseStrings(HashSet<GroupName> groupSet, Prefix prefix) {
        StringBuilder sb = new StringBuilder();
        for (GroupName groupName : groupSet) {
            sb.append(DELIMITER);
            sb.append(prefix.toString());
            sb.append(" ");
            sb.append(groupName.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
