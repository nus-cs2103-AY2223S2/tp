package seedu.address.testutil;

import seedu.address.model.Mathutoring;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Mathutoring objects.
 * Example usage: <br>
 *     {@code Mathutoring ab = new MathutoringBuilder().withPerson("John", "Doe").build();}
 */
public class MathutoringBuilder {

    private Mathutoring mathutoring;

    public MathutoringBuilder() {
        mathutoring = new Mathutoring();
    }

    public MathutoringBuilder(Mathutoring mathutoring) {
        this.mathutoring = mathutoring;
    }

    /**
     * Adds a new {@code Student} to the {@code Mathutoring} that we are building.
     */
    public MathutoringBuilder withPerson(Student student) {
        mathutoring.addStudent(student);
        return this;
    }

    public Mathutoring build() {
        return mathutoring;
    }
}
