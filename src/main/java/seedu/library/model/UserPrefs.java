package seedu.library.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.library.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path libraryFilePath = Paths.get("data" , "library.json");
    private Path tagsFilePath = Paths.get("data", "tags.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setLibraryFilePath(newUserPrefs.getLibraryFilePath());
        setTagsFilePath(newUserPrefs.getTagsFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getLibraryFilePath() {
        return libraryFilePath;
    }

    public void setLibraryFilePath(Path libraryFilePath) {
        requireNonNull(libraryFilePath);
        this.libraryFilePath = libraryFilePath;
    }

    public void setTagsFilePath(Path tagsFilePath) {
        requireNonNull(libraryFilePath);
        this.tagsFilePath = tagsFilePath;
    }

    public Path getTagsFilePath() {
        return tagsFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && libraryFilePath.equals(o.libraryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, libraryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + libraryFilePath);
        return sb.toString();
    }

}
