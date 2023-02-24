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
    private Path friendlyLinkFilePath = Paths.get("data" , "friendlylink.json");
    private final Path elderlyFilePath = Paths.get("data" , "elderly.json");
    private final Path volunteerFilePath = Paths.get("data" , "volunteer.json");

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
        setFriendlyLinkFilePath(newUserPrefs.getFriendlyLinkFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFriendlyLinkFilePath() {
        return friendlyLinkFilePath;
    }

    public Path getElderlyFilePath() {
        return elderlyFilePath;
    }

    public Path getVolunteerFilePath() {
        return volunteerFilePath;
    }

    public void setFriendlyLinkFilePath(Path friendlyLinkFilePath) {
        requireNonNull(friendlyLinkFilePath);
        this.friendlyLinkFilePath = friendlyLinkFilePath;
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
                && friendlyLinkFilePath.equals(o.friendlyLinkFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, friendlyLinkFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
                + "\nLocal data file location : " + friendlyLinkFilePath;
    }

}
