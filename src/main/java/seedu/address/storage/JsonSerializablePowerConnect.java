package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PowerConnect;
import seedu.address.model.ReadOnlyPowerConnect;
import seedu.address.model.person.student.Student;
import seedu.address.storage.person.JsonAdaptedStudent;

/**
 * An Immutable PowerConnect that is serializable to JSON format.
 */
@JsonRootName(value = "powerconnect")
class JsonSerializablePowerConnect {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePowerConnect} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePowerConnect(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePowerConnect}.
     */
    public JsonSerializablePowerConnect(ReadOnlyPowerConnect source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PowerConnect} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PowerConnect toModelType() throws IllegalValueException {
        PowerConnect powerConnect = new PowerConnect();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (powerConnect.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            powerConnect.addStudent(student);
        }
        return powerConnect;
    }

}
