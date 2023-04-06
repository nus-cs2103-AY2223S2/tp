package vimification.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

import vimification.common.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private static final String VIMIFICATION = ".vimification";

    private GuiSettings guiSettings = new GuiSettings();
    private Path taskListFilePath = Path.of(VIMIFICATION, "tasklist.json");
    private Path macroMapFilePath = Path.of(VIMIFICATION, "macromap.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the values in {@code userPrefs}.
     */
    public UserPrefs(UserPrefs userPrefs) {
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(UserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.guiSettings);
        setTaskListFilePath(newUserPrefs.taskListFilePath);
        setMacroMapFilePath(newUserPrefs.macroMapFilePath);
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTaskListFilePath() {
        return taskListFilePath;
    }

    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        this.taskListFilePath = taskListFilePath;
    }

    public Path getMacroMapFilePath() {
        return macroMapFilePath;
    }

    public void setMacroMapFilePath(Path macroMapFilePath) {
        requireNonNull(macroMapFilePath);
        this.macroMapFilePath = macroMapFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { // this handles null as well.
            return false;
        }
        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && taskListFilePath.equals(otherUserPrefs.taskListFilePath)
                && macroMapFilePath.equals(otherUserPrefs.macroMapFilePath);
    }

    @Override
    public String toString() {
        return "UserPrefs [guiSettings=" + guiSettings + ", taskListFilePath=" + taskListFilePath
                + ", macroMapFilePath=" + macroMapFilePath + "]";
    }
}
