package taa.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import taa.commons.exceptions.IllegalValueException;
import taa.model.ClassList;
import taa.model.assignment.Assignment;
import taa.model.student.Student;

/**
 * An Immutable ClassList that is serializable to JSON format.
 */
@JsonRootName(value = "taaData")
class JsonSerializableTaaData {

    public static final String MESSAGE_DUPLICATE_STUDENT = "JSON student list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_ASGN = "JSON assignment list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons. Called when reading from JSON.
     */
    @JsonCreator
    public JsonSerializableTaaData(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments) {
        if (students != null) {
            this.students.addAll(students);
        }
        if (assignments != null) {
            this.assignments.addAll(assignments);
        }
    }

    /**
     * Converts a given {@code ReadOnlyStudentList} into this class for Jackson use. Called when saving to JSON.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTaaData(TaaData source) {
        source.studentList.getStudentList().stream().map(JsonAdaptedStudent::new).forEach(students::add);
        Arrays.stream(source.asgnArr).map(JsonAdaptedAssignment::new).forEach(assignments::add);
    }

    /**
     * Converts this TAA data into the model's {@link TaaData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaaData toModelType() throws IllegalValueException {
        final ClassList classList = new ClassList();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (classList.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            classList.addStudent(student);
        }

        final ArrayList<Assignment> asgns = new ArrayList<>(assignments.size());
        for (JsonAdaptedAssignment jsonAssignment : assignments) {
            asgns.add(jsonAssignment.toModelType());
        }
        if (asgns.stream().map(Assignment::getName).distinct().count() < assignments.size()) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_ASGN);
        }
        return new TaaData(classList, asgns.toArray(new Assignment[0]));
    }

}
