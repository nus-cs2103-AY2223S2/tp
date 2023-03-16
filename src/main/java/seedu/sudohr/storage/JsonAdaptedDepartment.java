package seedu.sudohr.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;

/**
 * Jackson-friendly version of {@link Employee}.
 */
class JsonAdaptedDepartment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Department's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given department details.
     */
    @JsonCreator
    public JsonAdaptedDepartment(@JsonProperty("name") String name,
                                 @JsonProperty("employees") List<JsonAdaptedEmployee> employees) {
        this.name = name;
        if (employees != null) {
            this.employees.addAll(employees);
        }
    }

    /**
     * Converts a given {@code Department} into this class for Jackson use.
     */
    public JsonAdaptedDepartment(Department source) {
        name = source.getName().fullName;
        this.employees.addAll(source.getEmployees().stream()
                .map(JsonAdaptedEmployee::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted employee.
     */
    public Department toModelType() throws IllegalValueException {
        final List<Employee> departmentEmployees = new ArrayList<>();
        for (JsonAdaptedEmployee employee : employees) {
            Employee e = employee.toModelType();
            if (departmentEmployees.stream().anyMatch(e::isSameEmployee)) {
                throw new DuplicateEmployeeException();
            }
            if (departmentEmployees.stream().anyMatch(e::phoneClashes)) {
                throw new DuplicatePhoneNumberException();
            }
            if (departmentEmployees.stream().anyMatch(e::emailClashes)) {
                throw new DuplicateEmailException();
            }
            departmentEmployees.add(e);
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DepartmentName.class.getSimpleName()));
        }
        if (!DepartmentName.isValidName(name)) {
            throw new IllegalValueException(DepartmentName.MESSAGE_CONSTRAINTS);
        }

        final DepartmentName departmentName = new DepartmentName(name);

        final Set<Employee> modelEmployees = new HashSet<>(departmentEmployees);

        return new Department(departmentName, modelEmployees);
    }

}
