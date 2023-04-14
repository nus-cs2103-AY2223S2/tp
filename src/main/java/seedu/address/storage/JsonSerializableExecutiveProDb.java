package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.ReadOnlyExecutiveProDb;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * An Immutable ExecutiveProDb that is serializable to JSON format.
 */
@JsonRootName(value = "executiveprodb")
class JsonSerializableExecutiveProDb {

    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";

    private final int maxID;
    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableExecutiveProDb} with the given employees.
     */
    @JsonCreator
    public JsonSerializableExecutiveProDb(@JsonProperty("employees") List<JsonAdaptedEmployee> employees,
                                          @JsonProperty("maxID") int maxID) {
        this.employees.addAll(employees);
        this.maxID = maxID;
    }

    /**
     * Converts a given {@code ReadOnlyExecutiveProDb} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableExecutiveProDb}.
     */
    public JsonSerializableExecutiveProDb(ReadOnlyExecutiveProDb source, int maxID) {
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
        this.maxID = maxID;
    }

    /**
     * Converts this ExecutivePro into the model's {@code ExecutiveProDb} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExecutiveProDb toModelType() throws IllegalValueException {
        EmployeeId.setCount(maxID);
        ExecutiveProDb executiveProDb = new ExecutiveProDb();
        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (executiveProDb.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            executiveProDb.addEmployee(employee);
        }
        return executiveProDb;
    }

}
