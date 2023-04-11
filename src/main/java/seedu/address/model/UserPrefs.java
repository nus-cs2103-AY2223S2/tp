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
    private Path pcClassFilePath = Paths.get("data" , "pcclass.json");
    private Path parentsFilePath = Paths.get("data" , "parents.json");

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
        setParentsFilePath(newUserPrefs.getParentsFilePath());
        setPcClassFilePath(newUserPrefs.getPcClassFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    /**
     * Returns the PCClass file path.
     * @return the PCClass file path.
     */
    public Path getPcClassFilePath() {
        return pcClassFilePath;
    }

    /**
     * Returns the parents file path.
     * @return the parents file path.
     */
    public Path getParentsFilePath() {
        return parentsFilePath;
    }

    /**
     * Sets the PCClass file path.
     * @param pcClassFilePath the new PCClass file path.
     */
    public void setPcClassFilePath(Path pcClassFilePath) {
        requireNonNull(pcClassFilePath);
        this.pcClassFilePath = pcClassFilePath;
    }

    /**
     * Sets the parents file path.
     * @param parentsFilePath the new parents file path.
     */
    public void setParentsFilePath(Path parentsFilePath) {
        requireNonNull(parentsFilePath);
        this.parentsFilePath = parentsFilePath;
    }

    /**
     * Returns true if both user prefs have the same data.
     * @param o the other object to compare with.
     * @return true if both user prefs have the same data.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPrefs userPrefs = (UserPrefs) o;
        return Objects.equals(guiSettings, userPrefs.guiSettings)
                && Objects.equals(pcClassFilePath, userPrefs.pcClassFilePath)
                && Objects.equals(parentsFilePath, userPrefs.parentsFilePath);
    }

    /**
     * Returns the hashcode of the user prefs.
     * @return the hashcode of the user prefs.
     */
    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, pcClassFilePath, parentsFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + pcClassFilePath + " " + parentsFilePath);
        return sb.toString();
    }

}
