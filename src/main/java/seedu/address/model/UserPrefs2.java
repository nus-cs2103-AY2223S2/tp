package seedu.address.model;

import static java.util.Objects.requireNonNull;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs2 implements ReadOnlyUserPrefs2 {

    private GuiSettings guiSettings = new GuiSettings();
    private Path listingBookFilePath = Paths.get("data" , "listingbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs2() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs2(ReadOnlyUserPrefs2 userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs2 newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setListingBookFilePath(newUserPrefs.getListingBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getListingBookFilePath() {
        return listingBookFilePath;
    }

    public void setListingBookFilePath(Path listingBookFilePath) {
        requireNonNull(listingBookFilePath);
        this.listingBookFilePath = listingBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs2)) { //this handles null as well.
            return false;
        }

        UserPrefs2 o = (UserPrefs2) other;

        return guiSettings.equals(o.guiSettings)
                && listingBookFilePath.equals(o.listingBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, listingBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + listingBookFilePath);
        return sb.toString();
    }

}
