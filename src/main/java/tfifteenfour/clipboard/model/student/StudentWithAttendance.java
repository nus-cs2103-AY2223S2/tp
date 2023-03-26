package tfifteenfour.clipboard.model.student;

public class StudentWithAttendance extends Student {
    private final int attendance;

    public StudentWithAttendance(Student student, int attendance) {
        super(student.getName(), student.getPhone(), student.getEmail(), student.getStudentId(), student.getRemark());
        this.attendance = attendance;
    }

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
