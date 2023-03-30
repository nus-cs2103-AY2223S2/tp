package tfifteenfour.clipboard.logic.commands.sortcommand.studentcomparators;

import java.util.Comparator;

import tfifteenfour.clipboard.model.student.Student;

public class AlphaNumericSidComparator implements Comparator<Student> {
	@Override
    public int compare(Student s1, Student s2) {
        return s1.getStudentId().toString().compareTo(s2.getStudentId().toString());
	}
}
