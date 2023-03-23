package tfifteenfour.clipboard.model.student;

import java.util.ArrayList;

public class Group {
	String name;
	ArrayList<Student> students = new ArrayList<>();
	ArrayList<Session> sessions = new ArrayList<>();
	ArrayList<Assignment> assignments = new ArrayList<>();

	public Group(String name) {
		this.name = name;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void addSession(Session session) {
		this.sessions.add(session);
	}

	public void addAssignment(Assignment assignment) {
		this.assignments.add(assignment);
	}

}
