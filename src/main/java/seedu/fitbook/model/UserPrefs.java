package seedu.fitbook.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.fitbook.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path fitBookFilePath = Paths.get("data" , "fitbook.json");
    private Path fitBookExerciseRoutineFilePath = Paths.get("data", "exerciseroutine.json");

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
        setFitBookFilePath(newUserPrefs.getFitBookFilePath());
        setFitBookExerciseRoutineFilePath(newUserPrefs.getFitBookExerciseRoutineFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFitBookFilePath() {
        return fitBookFilePath;
    }

    public void setFitBookFilePath(Path fitBookFilePath) {
        requireNonNull(fitBookFilePath);
        this.fitBookFilePath = fitBookFilePath;
    }

    public Path getFitBookExerciseRoutineFilePath() {
        return fitBookExerciseRoutineFilePath;
    }

    public void setFitBookExerciseRoutineFilePath(Path fitBookFilePath) {
        requireNonNull(fitBookFilePath);
        this.fitBookExerciseRoutineFilePath = fitBookFilePath;
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
                && fitBookFilePath.equals(o.fitBookFilePath)
                && fitBookExerciseRoutineFilePath.equals(o.fitBookExerciseRoutineFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, fitBookFilePath, fitBookExerciseRoutineFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + fitBookFilePath);
        sb.append("\nLocal data file location for exercise routine: " + fitBookExerciseRoutineFilePath);
        return sb.toString();
    }

}
