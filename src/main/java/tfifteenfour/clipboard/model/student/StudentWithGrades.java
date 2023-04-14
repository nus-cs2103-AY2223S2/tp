package tfifteenfour.clipboard.model.student;

/**
 * A class representing a student with their grade.
 * Inherits from the Student class.
 */
public class StudentWithGrades extends Student {
    /** The grade of the student. */
    private final int grade;

    /**
     * Creates a new StudentWithGrades object based on the provided Student object and grade assigned.
     *
     * @param student The student object to be wrapped.
     * @param grade The grade of the student.
     */
    public StudentWithGrades(Student student, Integer grade) {
        super(student.getName(), student.getPhone(), student.getEmail(), student.getStudentId(), student.getRemark());
        this.grade = grade;
    }


    /**
     * Returns the grade of the student.
     *
     * @return The grade of the student.
     */
    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; StudentId: ")
                .append(getStudentId())
                .append("; Remark: ")
                .append(getRemark())
                .append("; Grade: ")
                .append(getGrade());
        return builder.toString();
    }
}
