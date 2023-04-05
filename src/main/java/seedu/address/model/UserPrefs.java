package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path petPalFilePath = Paths.get("data" , "petpal.json");
    private Path petPalArchiveFilePath = Paths.get("data", "archive.json");

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
        setPetPalFilePath(newUserPrefs.getPetPalFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPetPalFilePath() {
        return this.petPalFilePath;
    }

    public void setPetPalFilePath(Path petPalFilePath) {
        requireNonNull(petPalFilePath);
        this.petPalFilePath = petPalFilePath;
    }

    public Path getPetPalArchiveFilePath() {
        return this.petPalArchiveFilePath;
    }

    public void setPetPalArchiveFilePath(Path petPalArchiveFilePath) {
        requireNonNull(petPalArchiveFilePath);
        this.petPalArchiveFilePath = petPalArchiveFilePath;
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
                && petPalFilePath.equals(o.petPalFilePath)
                && petPalArchiveFilePath.equals(o.petPalArchiveFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, petPalFilePath, petPalArchiveFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : "
                + guiSettings
                + "\nLocal data file location : "
                + petPalFilePath
                + "\nLocal archive file location: "
                + petPalArchiveFilePath;
    }

}
