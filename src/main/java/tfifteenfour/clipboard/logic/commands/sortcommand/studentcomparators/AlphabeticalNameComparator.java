package tfifteenfour.clipboard.logic.commands.sortcommand.studentcomparators;

import java.util.Comparator;

import tfifteenfour.clipboard.model.student.Student;

/**
 * Comparator for sorting students by name.
 */
public class AlphabeticalNameComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().toString().compareTo(s2.getName().toString());
    }
}
