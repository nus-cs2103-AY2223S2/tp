package tfifteenfour.clipboard.storage.serializedclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;


public class SerializedSession {
    private String sessionName;
//    private List<SerializedStudent> students = new ArrayList<>();
    private final List<SerializedStudent> keys = new ArrayList<>();
    private final List<Integer> values = new ArrayList<>();



    public SerializedSession(Session session) {
        this.sessionName = session.getSessionName();
//        this.students = session.getUnmodifiableStudentList().stream()
//                .map(SerializedStudent::new)
//                .collect(Collectors.toList());

        Map<Student, Integer> sessionAttendance = session.getAttendance();
        for (Student student : sessionAttendance.keySet()) {
            keys.add(new SerializedStudent(student));
            values.add(sessionAttendance.get(student));
        }

    }

    public SerializedSession() {}

    @JsonProperty("sessionName")
    public String getSessionName() {
        return sessionName;
    }

//    @JsonProperty("students")
//    public List<SerializedStudent> getStudents() {
//        return students;
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
//        this.students.stream().forEach(student -> newSession.addStudent(student.toModelType()));

        Map<Student, Integer> newAttendance = newSession.getAttendance();
        for (int i = 0; i < keys.size(); i++) {
            newAttendance.put(
                    keys.get(i).toModelType(),
                    values.get(i)
            );
        }
        return newSession;
    }
}
