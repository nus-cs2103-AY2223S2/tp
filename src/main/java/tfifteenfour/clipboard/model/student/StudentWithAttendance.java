package tfifteenfour.clipboard.model.student;

/**
 * A class representing a student with their attendance record.
 * Inherits from the Student class.
 */
public class StudentWithAttendance extends Student {
    /** The attendance of the student. */
    private final int attendance;

    /**
     * Creates a new StudentWithAttendance object based on the provided Student object and attendance record.
     *
     * @param student The student object to be wrapped.
     * @param attendance The attendance record of the student.
     */
    public StudentWithAttendance(Student student, int attendance) {
        super(student.getName(), student.getPhone(), student.getEmail(), student.getStudentId(), student.getRemark());
        this.attendance = attendance;
    }


    /**
     * Returns the attendance record of the student.
     *
     * @return The attendance record of the student.
     */
    public int getAttendance() {
        return attendance;
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
                .append("; Attendance: ")
                .append(getAttendance());
        return builder.toString();
    }
}
