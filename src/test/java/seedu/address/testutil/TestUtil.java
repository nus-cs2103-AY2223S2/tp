package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

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
     * Returns the middle index of the volunteer in the {@code model}'s volunteer list.
     */
    public static Index getMidVolunteerIndex(Model model) {
        return Index.fromOneBased(model.getFilteredVolunteerList().size() / 2);
    }

    /**
     * Returns the last index of the volunteer in the {@code model}'s volunteer list.
     */
    public static Index getLastVolunteerIndex(Model model) {
        return Index.fromOneBased(model.getFilteredVolunteerList().size());
    }

    /**
     * Returns the volunteer in the {@code model}'s volunteer list at {@code index}.
     */
    public static Volunteer getVolunteer(Model model, Index index) {
        return model.getFilteredVolunteerList().get(index.getZeroBased());
    }

    /**
     * Returns the middle index of the elderly in the {@code model}'s elderly list.
     */
    public static Index getMidElderlyIndex(Model model) {
        return Index.fromOneBased(model.getFilteredElderlyList().size() / 2);
    }

    /**
     * Returns the last index of the elderly in the {@code model}'s elderly list.
     */
    public static Index getLastElderlyIndex(Model model) {
        return Index.fromOneBased(model.getFilteredElderlyList().size());
    }

    /**
     * Returns the elderly in the {@code model}'s elderly list at {@code index}.
     */
    public static Elderly getElderly(Model model, Index index) {
        return model.getFilteredElderlyList().get(index.getZeroBased());
    }

    /**
     * Returns the middle index of the pair in the {@code model}'s pair list.
     */
    public static Index getMidPairIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPairList().size() / 2);
    }

    /**
     * Returns the last index of the pair in the {@code model}'s pair list.
     */
    public static Index getLastPairIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPairList().size());
    }

    /**
     * Returns the pair in the {@code model}'s pair list at {@code index}.
     */
    public static Pair getPair(Model model, Index index) {
        return model.getFilteredPairList().get(index.getZeroBased());
    }

    /**
     * Returns a {@code FriendlyLink} with all the typical volunteers, elderly and pairs.
     */
    public static FriendlyLink getTypicalFriendlyLink() {
        FriendlyLink fl = new FriendlyLink();
        for (Volunteer volunteer : TypicalVolunteers.getTypicalVolunteers()) {
            fl.addVolunteer(volunteer);
        }
        for (Elderly elderly : TypicalElderly.getTypicalElderly()) {
            fl.addElderly(elderly);
        }
        for (Pair pair : TypicalPairs.getTypicalPairs()) {
            fl.addPair(pair);
        }
        return fl;
    }

    /**
     * Returns a {@code ModelManager} with the typical FriendlyLink.
     */
    public static ModelManager getTypicalModelManager() {
        return new ModelManagerBuilder()
                .withFriendlyLink(getTypicalFriendlyLink())
                .build();
    }
}
