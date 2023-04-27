package vimification.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

import vimification.common.core.GuiSettings;

/**
 * Represents the user's preferences.
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
     * Creates a new {@code UserPrefs} with the values in the other {@code userPrefs}. This is a
     * copy constructor.
     *
     * @param userPrefs the other {@code UserPrefs}.
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

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getTaskListFilePath() {
        return taskListFilePath;
    }

    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        this.taskListFilePath = taskListFilePath;
    }

    @Override
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
