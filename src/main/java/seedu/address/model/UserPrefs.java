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
    private Path elderlyFilePath = Paths.get("data" , "elderly.json");
    private Path volunteerFilePath = Paths.get("data" , "volunteer.json");
    private Path pairFilePath = Paths.get("data" , "pair.json");

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
        setElderlyFilePath(newUserPrefs.getElderlyFilePath());
        setVolunteerFilePath(newUserPrefs.getVolunteerFilePath());
        setPairFilePath(newUserPrefs.getPairFilePath());
    }

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getElderlyFilePath() {
        return elderlyFilePath;
    }

    public void setElderlyFilePath(Path elderlyFilePath) {
        requireNonNull(elderlyFilePath);
        this.elderlyFilePath = elderlyFilePath;
    }

    @Override
    public Path getVolunteerFilePath() {
        return volunteerFilePath;
    }

    public void setVolunteerFilePath(Path volunteerFilePath) {
        requireNonNull(volunteerFilePath);
        this.volunteerFilePath = volunteerFilePath;
    }

    @Override
    public Path getPairFilePath() {
        return pairFilePath;
    }

    public void setPairFilePath(Path pairFilePath) {
        requireNonNull(pairFilePath);
        this.pairFilePath = pairFilePath;
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
                && elderlyFilePath.equals(o.elderlyFilePath)
                && volunteerFilePath.equals(o.volunteerFilePath)
                && pairFilePath.equals(o.pairFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, elderlyFilePath, volunteerFilePath, pairFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
                + "\nLocal elderly data file location : " + elderlyFilePath
                + "\nLocal volunteer data file location : " + volunteerFilePath
                + "\nLocal pair data file location : " + pairFilePath;
    }

}
