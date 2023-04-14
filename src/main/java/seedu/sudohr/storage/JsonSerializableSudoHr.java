package seedu.sudohr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
import seedu.sudohr.model.leave.Leave;

/**
 * An Immutable SudoHr that is serializable to JSON format.
 */
@JsonRootName(value = "sudohr")
class JsonSerializableSudoHr {

    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";
    public static final String MESSAGE_DUPLICATE_PHONE = "There are duplicate phone numbers in the employee list.";
    public static final String MESSAGE_DUPLICATE_EMAIL = "There are duplicate email addresses in the employee list";
    public static final String MESSAGE_DUPLICATE_DEPARTMENTS = "Departments list contains duplicate department(s).";
    public static final String MESSAGE_DUPLICATE_LEAVES = "Leave list contains duplicate events(s).";

    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();
    private final List<JsonAdaptedDepartment> departments = new ArrayList<>();
    private final List<JsonAdaptedLeave> leaves = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSudoHr} with the given employees.
     */
    @JsonCreator
    public JsonSerializableSudoHr(@JsonProperty("employees") List<JsonAdaptedEmployee> employees,
            @JsonProperty("departments") List<JsonAdaptedDepartment> departments,
            @JsonProperty("leaves") List<JsonAdaptedLeave> leaves) {
        this.employees.addAll(employees);
        this.departments.addAll(departments);
        this.leaves.addAll(leaves);
    }

    /**
     * Converts a given {@code ReadOnlySudoHr} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSudoHr}.
     */
    public JsonSerializableSudoHr(ReadOnlySudoHr source) {
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
        departments.addAll(source.getDepartmentList().stream().map(JsonAdaptedDepartment::new)
                .collect(Collectors.toList()));
        leaves.addAll(source.getLeavesList().stream().map(JsonAdaptedLeave::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this sudohr book into the model's {@code SudoHr} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SudoHr toModelType() throws IllegalValueException {
        SudoHr sudoHr = new SudoHr();

        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (sudoHr.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            if (sudoHr.hasClashingPhoneNumber(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PHONE);
            }
            if (sudoHr.hasClashingEmail(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMAIL);
            }
            sudoHr.addEmployee(employee);
        }

        for (JsonAdaptedDepartment jsonAdaptedDepartment : departments) {
            Department department = jsonAdaptedDepartment.toModelType();
            if (sudoHr.hasDepartment(department)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DEPARTMENTS);
            }

            Set<Employee> employees = department.getEmployees();
            for (Employee employee: employees) {
                if (!sudoHr.strictlyHasEmployee(employee)) {
                    throw new EmployeeNotFoundException();
                }
            }

            sudoHr.addDepartment(department);
        }

        for (JsonAdaptedLeave jsonAdaptedLeave : leaves) {
            Leave leave = jsonAdaptedLeave.toModelType();
            if (sudoHr.hasLeave(leave)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LEAVES);
            }

            List<Employee> employees = leave.getEmployees();
            for (Employee employee: employees) {
                if (!sudoHr.strictlyHasEmployee(employee)) {
                    throw new EmployeeNotFoundException();
                }
            }

            sudoHr.addLeave(leave);
        }

        return sudoHr;
    }

}
