package seedu.ultron.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.ultron.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path ultronFilePath = Paths.get("data" , "ultron.json");

    /**
     * Creates a {@code UserPrefsNew} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefsNew} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefsNew} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setUltronFilePath(newUserPrefs.getUltronFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getUltronFilePath() {
        return ultronFilePath;
    }

    public void setUltronFilePath(Path ultronFilePath) {
        requireNonNull(ultronFilePath);
        this.ultronFilePath = ultronFilePath;
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
                && ultronFilePath.equals(o.ultronFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, ultronFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + ultronFilePath);
        return sb.toString();
    }

}
