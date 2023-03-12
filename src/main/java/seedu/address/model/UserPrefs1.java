package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs1 implements ReadOnlyUserPrefs1 {
    private GuiSettings guiSettings = new GuiSettings();
    private Path internshipCatalogueFilePath = Paths.get("data", "internshipcatalogue.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs1() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs1(ReadOnlyUserPrefs1 userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs1 newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setInternshipCatalogueFilePath(newUserPrefs.getInternshipCatalogueFilePath());
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs1)) { //this handles null as well.
            return false;
        }

        UserPrefs1 o = (UserPrefs1) other;

        return guiSettings.equals(o.guiSettings)
                && internshipCatalogueFilePath.equals(o.internshipCatalogueFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, internshipCatalogueFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + internshipCatalogueFilePath);
        return sb.toString();
    }

}