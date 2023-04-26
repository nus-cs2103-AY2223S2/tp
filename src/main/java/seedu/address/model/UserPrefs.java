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
    private Path addressBookFilePath = Paths.get("data", "officeconnect.json");
    private final Path taskFilePath = Paths.get("data", "tasks.json");
    private final Path personTaskPath = Paths.get("data", "assigntask.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

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
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    @Override
    public Path getTaskFilePath() {
        return taskFilePath;
    }

    @Override
    public Path getPersonTaskPath() {
        return personTaskPath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
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
            && addressBookFilePath.equals(o.addressBookFilePath)
            && taskFilePath.equals(o.taskFilePath)
            && personTaskPath.equals(o.personTaskPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath,
            taskFilePath, personTaskPath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
            + "\nLocal data file location : " + addressBookFilePath
            + "\nLocal data task file location : " + taskFilePath
            + "\nLocal data taskPerson mapping file location : " + personTaskPath;
    }

}
