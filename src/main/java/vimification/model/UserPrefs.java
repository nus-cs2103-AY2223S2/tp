package vimification.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

import vimification.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private static final String VIMIFICATION = ".vimification";

    private GuiSettings guiSettings = new GuiSettings();
    private Path logicTaskListFilePath = Path.of(VIMIFICATION, "tasklist.json");
    private Path macroMapFilePath = Path.of(VIMIFICATION, "macro.json");
    private Path commandStackFilePath = Path.of(VIMIFICATION, "oldcommand.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(UserPrefs userPrefs) {
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(UserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setLogicTaskListFilePath(newUserPrefs.getLogicTaskListFilePath());
        setMacroMapFilePath(newUserPrefs.getMacroMapFilePath());
        setCommandStackFilePath(newUserPrefs.getCommandStackFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getLogicTaskListFilePath() {
        return logicTaskListFilePath;
    }

    public void setLogicTaskListFilePath(Path logicTaskListFilePath) {
        requireNonNull(logicTaskListFilePath);
        this.logicTaskListFilePath = logicTaskListFilePath;
    }

    public Path getMacroMapFilePath() {
        return macroMapFilePath;
    }

    public void setMacroMapFilePath(Path macroMapFilePath) {
        requireNonNull(macroMapFilePath);
        this.macroMapFilePath = macroMapFilePath;
    }

    public Path getCommandStackFilePath() {
        return commandStackFilePath;
    }

    public void setCommandStackFilePath(Path commandStackFilePath) {
        requireNonNull(commandStackFilePath);
        this.commandStackFilePath = commandStackFilePath;
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
                && logicTaskListFilePath.equals(otherUserPrefs.logicTaskListFilePath)
                && macroMapFilePath.equals(otherUserPrefs.macroMapFilePath)
                && commandStackFilePath.equals(otherUserPrefs.commandStackFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings: " + guiSettings);
        sb.append("\nLocal data file location: " + logicTaskListFilePath);
        sb.append("\nMacro file location: " + macroMapFilePath);
        sb.append("\nCommand stack location: " + commandStackFilePath);
        return sb.toString();
    }

}
