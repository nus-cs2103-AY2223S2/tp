package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * Represents the in-memory model of the ExecutivePro data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExecutiveProDb executiveProDb;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredEmployees;
    private final FilteredList<Employee> allEmployees;

    /**
     * Initializes a ModelManager with the given executiveProDb and userPrefs.
     */
    public ModelManager(ReadOnlyExecutiveProDb executiveProDb, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(executiveProDb, userPrefs);

        logger.fine("Initializing with ExecutivePro: " + executiveProDb + " and user prefs " + userPrefs);

        this.executiveProDb = new ExecutiveProDb(executiveProDb);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEmployees = new FilteredList<>(this.executiveProDb.getEmployeeList());
        allEmployees = new FilteredList<>(this.executiveProDb.getEmployeeList());
    }

    public ModelManager() {
        this(new ExecutiveProDb(), new UserPrefs());
    }

    /**
     * Creates a new ModelManager by making a deep copy of the provided Model.
     * This constructor is particularly useful when you need to create a deep copy of the ModelManager.
     *
     * @param model The Model to be copied.
     */
    public ModelManager(Model model) {
        requireAllNonNull(model);
        userPrefs = new UserPrefs(model.getUserPrefs());
        executiveProDb = new ExecutiveProDb(model.getExecutiveProDb());
        filteredEmployees = new FilteredList<>(this.executiveProDb.getEmployeeList());
        allEmployees = new FilteredList<>(model.getFullEmployeeList());
    }



    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getExecutiveProFilePath() {
        return userPrefs.getExecutiveProDbFilePath();
    }

    @Override
    public void setExecutiveProFilePath(Path executiveProFilePath) {
        requireNonNull(executiveProFilePath);
        userPrefs.setExecutiveProDbFilePath(executiveProFilePath);
    }

    //=========== ExecutiveProDb ================================================================================

    @Override
    public void setExecutiveProDb(ReadOnlyExecutiveProDb addressBook) {
        this.executiveProDb.resetData(addressBook);
    }

    @Override
    public ReadOnlyExecutiveProDb getExecutiveProDb() {
        return executiveProDb;
    }

    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return executiveProDb.hasEmployee(employee);
    }

    @Override
    public Optional<Employee> getEmployee(EmployeeId employeeId) {
        requireNonNull(employeeId);
        return executiveProDb.getEmployee(employeeId);
    }

    @Override
    public void deleteEmployee(Employee target) {
        executiveProDb.removeEmployee(target);
    }

    @Override
    public void addEmployee(Employee employee) {
        executiveProDb.addEmployee(employee);
        updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
    }

    @Override
    public void batchAddEmployees(String fileName) {
        Path file = Paths.get("data", " ");

        String line = "";

    }

    @Override
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        executiveProDb.setEmployee(target, editedEmployee);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Employee> getFullEmployeeList() {
        return allEmployees;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return filteredEmployees;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        filteredEmployees.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return executiveProDb.equals(other.executiveProDb)
                && userPrefs.equals(other.userPrefs)
                && filteredEmployees.equals(other.filteredEmployees);
    }

}
