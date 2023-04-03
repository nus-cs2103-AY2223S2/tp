package taa.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import taa.commons.exceptions.IllegalValueException;
import taa.model.ClassList;
import taa.model.ReadOnlyStudentList;
import taa.model.student.Student;

/**
 * An Immutable ClassList that is serializable to JSON format.
 */
@JsonRootName(value = "taaData")
class JsonSerializableTaaData {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Persons list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTaaData(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyStudentList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTaaData(ReadOnlyStudentList source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TAA data into the model's {@code ClassList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClassList toModelType() throws IllegalValueException {
        ClassList classList = new ClassList();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (classList.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            classList.addStudent(student);
        }
        return classList;
    }

}
