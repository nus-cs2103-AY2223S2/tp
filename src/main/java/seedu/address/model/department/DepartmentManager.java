package seedu.address.model.department;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.nio.file.Path;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the in-memory model of the SudoHR's department data.
 */
public class DepartmentManager {
    private static final Logger logger = LogsCenter.getLogger(DepartmentManager.class);

    private final UniqueDepartmentList departments;
    private final UserPrefs userPrefs;

    // Placeholder to initialise with given department list and userPrefs from storage.

    public DepartmentManager() {
        this.departments = new UniqueDepartmentList();
        this.userPrefs = new UserPrefs();
    }

    //=========== UserPrefs ==================================================================================

    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== SudoHR ================================================================================

    /**
     * Checks whether the given department exists in SudoHR.
     * @param department The department to check
     * @return whether department exists
     */
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return departments.contains(department);
    }

    /**
     * Deletes a given department from SudoHR.
     * @param target The department to be deleted
     */
    public void deleteDepartment(Department target) {
        departments.remove(target);
    }

    /**
     * Adds a new department to SudoHR.
     * @param department The new department
     */
    public void addDepartment(Department department) {
        departments.add(department);
        // Frontend update:
        // updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Updates a given department in SudoHR.
     * @param target the department to update
     * @param editedDepartment the new department
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireAllNonNull(target, editedDepartment);

        departments.setDepartment(target, editedDepartment);
    }

    //
}
