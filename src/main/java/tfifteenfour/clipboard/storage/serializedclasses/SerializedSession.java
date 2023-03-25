package tfifteenfour.clipboard.storage.serializedclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SerializedSession {
    private String sessionName;
    private Map<SerializedStudent, Integer> attendance = new HashMap<>();
    private List<SerializedStudent> students;

    public SerializedSession(Session session) {
        this.sessionName = session.getSessionName();
        this.students = session.getUnmodifiableStudentList().stream()
                .map(student -> new SerializedStudent(student))
                .collect(Collectors.toList());

        Map<Student, Integer> sessionAttendance = session.getAttendance();
        for (Student student : sessionAttendance.keySet()) {
            this.attendance.put(new SerializedStudent(student), sessionAttendance.get(student));
        }
    }

    @JsonProperty("sessionName")
    public String getSessionName() {
        return sessionName;
    }

    @JsonProperty("students")
    public List<SerializedStudent> getStudents() {
        return students;
    }

    @JsonProperty("attendance")
    public Map<SerializedStudent, Integer> getAttendance() {
        return attendance;
    }

    public Session toModelType() {
        Session newSession = new Session(this.sessionName);
        UniqueStudentList uniqueStudents = new UniqueStudentList();
        this.students.stream().forEach(student -> uniqueStudents.add(student.toModelType()));
        newSession.setStudents(uniqueStudents);

        Map<Student, Integer> newAttendance = newSession.getAttendance();
        for (SerializedStudent student : attendance.keySet()) {
            newAttendance.put(student.toModelType(), attendance.get(student));
        }

        return newSession;
//        this.students.stream().forEach(student -> newSession.addStudent(student.toModelType()));
//        this.students.stream().forEach(student -> newGroup.addStudent(student.toModelType()));
//        return newGroup;
    }
}
