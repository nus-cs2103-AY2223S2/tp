package tfifteenfour.clipboard.storage.serializedclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentList;


public class SerializedSession {
    private String sessionName;
//    private Map<SerializedStudent, Integer> attendance = new HashMap<>();
    private List<SerializedStudent> students;
    private List<SerializedStudent> keys;
    private List<Integer> values;

    public SerializedSession(Session session) {
        this.sessionName = session.getSessionName();
        this.students = session.getUnmodifiableStudentList().stream()
                .map(student -> new SerializedStudent(student))
                .collect(Collectors.toList());

        Map<Student, Integer> sessionAttendance = session.getAttendance();
        for (Student student : sessionAttendance.keySet()) {
            keys.add(new SerializedStudent(student));
            values.add(sessionAttendance.get(student));
//            this.attendance.put(new SerializedStudent(student), sessionAttendance.get(student));
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

//    @JsonProperty("attendance")
//    public Map<SerializedStudent, Integer> getAttendance() {
//        return attendance;
//    }

    @JsonProperty("keys")
    public List<SerializedStudent> getKeys() {
        return this.keys;
    }

    @JsonProperty("values")
    public List<Integer> getValues() {
        return this.values;
    }

    public Session toModelType() {
        Session newSession = new Session(this.sessionName);
        UniqueStudentList uniqueStudents = new UniqueStudentList();
//        for (SerializedStudent student : students) {
//            uniqueStudents.add(student.toModelType());
//        }
        this.students.forEach(student -> uniqueStudents.add(student.toModelType()));
        newSession.setStudents(uniqueStudents);

        Map<Student, Integer> newAttendance = newSession.getAttendance();
        for (int i = 0; i < keys.size(); i++) {
            newAttendance.put(
                    keys.get(i).toModelType(),
                    values.get(i)
            );
        }
//        for (SerializedStudent student : attendance.keySet()) {
//            newAttendance.put(student.toModelType(), attendance.get(student));
//        }

        return newSession;
    }
}
