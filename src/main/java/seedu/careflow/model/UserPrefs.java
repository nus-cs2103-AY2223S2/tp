package seedu.careflow.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.careflow.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path patientRecordFilePath = Paths.get("data", "patientRecord.json");
    private Path drugInventoryFilePath = Paths.get("data", "drugInventory.json");

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
        setPatientRecordFilePath(newUserPrefs.getPatientRecordFilePath());
        setDrugInventoryFilePath(newUserPrefs.getDrugInventoryFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getPatientRecordFilePath() {
        return patientRecordFilePath;
    }

    public void setPatientRecordFilePath(Path patientRecordFilePath) {
        requireNonNull(patientRecordFilePath);
        this.patientRecordFilePath = patientRecordFilePath;
    }

    @Override
    public Path getDrugInventoryFilePath() {
        return drugInventoryFilePath;
    }

    public void setDrugInventoryFilePath(Path drugInventoryFilePath) {
        requireNonNull(drugInventoryFilePath);
        this.drugInventoryFilePath = drugInventoryFilePath;
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
                && patientRecordFilePath.equals(o.patientRecordFilePath)
                && drugInventoryFilePath.equals(o.drugInventoryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, patientRecordFilePath, drugInventoryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : ").append(guiSettings);
        sb.append("\nLocal patient data file location : ").append(patientRecordFilePath);
        sb.append("\nLocal drug data file location : ").append(drugInventoryFilePath);
        return sb.toString();
    }

}
