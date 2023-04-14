package seedu.sudohr.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * Jackson-friendly version of {@link Leave}.
 */
public class JsonAdaptedLeave {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "the %s field is missing!";

    private final String date;
    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLeave} with the given leave details.
     */
    @JsonCreator
    public JsonAdaptedLeave(@JsonProperty("date") String date,
            @JsonProperty("employees") List<JsonAdaptedEmployee> employees) {
        this.date = date;
        if (employees != null) {
            this.employees.addAll(employees);
        }
    }

    /**
     * Converts a given {@code Leave} into this class for Jackson use.
     */
    public JsonAdaptedLeave(Leave source) {
        date = source.getDate().value.toString();
        this.employees.addAll(source.getEmployees().stream()
                .map(JsonAdaptedEmployee::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly leave department object into the model's
     * {@code Leave} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted leave.
     */
    public Leave toModelType() throws IllegalValueException {
        final List<Employee> leaveEmployees = new ArrayList<>();
        for (JsonAdaptedEmployee employee : employees) {
            Employee e = employee.toModelType();
            if (leaveEmployees.stream().anyMatch(e::isSameEmployee)) {
                throw new DuplicateEmployeeException();
            }
            leaveEmployees.add(e);
        }

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LeaveDate.class.getSimpleName()));
        }

        LocalDate leaveDate;
        try {
            leaveDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }

        final LeaveDate currentDate = new LeaveDate(leaveDate);
        final Set<Employee> modelEmployees = new HashSet<>(leaveEmployees);

        return new Leave(currentDate, modelEmployees);
    }
}
