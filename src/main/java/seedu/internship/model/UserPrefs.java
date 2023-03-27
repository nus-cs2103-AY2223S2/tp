package seedu.internship.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.internship.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private GuiSettings guiSettings = new GuiSettings();
    private Path internshipCatalogueFilePath = Paths.get("data", "internshipcatalogue.json");
    private Path eventCatalogueFilePath = Paths.get("data", "eventcatalogue.json");

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
        setInternshipCatalogueFilePath(newUserPrefs.getInternshipCatalogueFilePath());
        setEventCatalogueFilePath(newUserPrefs.getEventCatalogueFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getInternshipCatalogueFilePath() {
        return internshipCatalogueFilePath;
    }

    public void setInternshipCatalogueFilePath(Path internshipCatalogueFilePath) {
        requireNonNull(internshipCatalogueFilePath);
        this.internshipCatalogueFilePath = internshipCatalogueFilePath;
    }

    public Path getEventCatalogueFilePath() {
        return eventCatalogueFilePath;
    }

    public void setEventCatalogueFilePath(Path eventCatalogueFilePath) {
        requireNonNull(eventCatalogueFilePath);
        this.eventCatalogueFilePath = eventCatalogueFilePath;
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
                && internshipCatalogueFilePath.equals(o.internshipCatalogueFilePath)
                && eventCatalogueFilePath.equals(o.eventCatalogueFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, internshipCatalogueFilePath, eventCatalogueFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + internshipCatalogueFilePath + " | " + eventCatalogueFilePath);
        return sb.toString();
    }

}
