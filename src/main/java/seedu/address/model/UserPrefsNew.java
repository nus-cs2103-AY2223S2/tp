package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefsNew implements ReadOnlyUserPrefsNew {

    private GuiSettings guiSettings = new GuiSettings();
    private Path ultronFilePath = Paths.get("data" , "ultron.json");

    /**
     * Creates a {@code UserPrefsNew} with default values.
     */
    public UserPrefsNew() {}

    /**
     * Creates a {@code UserPrefsNew} with the prefs in {@code userPrefs}.
     */
    public UserPrefsNew(ReadOnlyUserPrefsNew userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefsNew} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefsNew newUserPrefs) {
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
        if (!(other instanceof UserPrefsNew)) { //this handles null as well.
            return false;
        }

        UserPrefsNew o = (UserPrefsNew) other;

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
