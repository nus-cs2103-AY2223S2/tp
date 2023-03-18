package seedu.address.storage.pcclass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Class;
import seedu.address.model.person.parent.UniqueParentList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;
import seedu.address.storage.JsonAdapted;
import seedu.address.storage.person.JsonAdaptedParent;
import seedu.address.storage.person.JsonAdaptedStudent;

/**
 * A JsonAdapted Class
 */
public class JsonAdaptedClass implements JsonAdapted<Class> {
    private final String className;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private List<JsonAdaptedParent> parents = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClass} with the given {@code className} and {@code students}.
     * @param className Name of the class
     * @param students List of students in the class
     */
    @JsonCreator
    public JsonAdaptedClass(@JsonProperty("className") String className,
                            @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.className = className;
        this.students.addAll(students);
    }

    /**
     * A Constructor for JsonAdaptedClass
     * @param pcClass
     */
    public JsonAdaptedClass(Class pcClass) {
        this.className = pcClass.getClassName();
        this.students.addAll(pcClass.getStudents()
                        .asUnmodifiableObservableList()
                        .stream()
                        .map(JsonAdaptedStudent::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts a given {@code JsonAdaptedClass} into a Class.
     * @return Class
     * @throws IllegalValueException if there were any data constraints violated in the adapted class.
     */
    @Override
    public Class toModelType() throws IllegalValueException {
        if (className == null) {
            throw new IllegalValueException("Class name cannot be null");
        }
        if (!Class.isValidClass(className)) {
            throw new IllegalValueException(Class.MESSAGE_CONSTRAINTS);
        }
        final List<Student> students = new ArrayList<>();
        for (JsonAdaptedStudent student : this.students) {
            students.add(student.toModelType());
        }
        final UniqueStudentList uniqueStudentList = new UniqueStudentList();
        uniqueStudentList.setStudents(students);
        // TODO: parents are empty for now
        return Class.of(className, uniqueStudentList, new UniqueParentList());
    }

}
