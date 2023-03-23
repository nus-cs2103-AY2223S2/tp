package tfifteenfour.clipboard.logic.parser;

import java.util.Comparator;

import tfifteenfour.clipboard.logic.commands.SortCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.student.Student;

public class SortCommandParser implements Parser<SortCommand> {
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

		SortCategory category = SortCategory.fromString(trimmedArgs);

		switch (category) {
		case SORT_BY_NAME:
			return new SortCommand(new AlphaNameComparator(), category.getCategory());
		case SORT_BY_STUDENT_ID:
			return new SortCommand(new NumericAlphaStudentIdComparator(), category.getCategory());
		default:
			throw new ParseException("Unable to parse category for sorting");
		}
    }
}

class AlphaNameComparator implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s1.getName().toString().compareTo(s2.getName().toString());
	}
}

class NumericAlphaStudentIdComparator implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s1.getStudentId().toString().compareTo(s2.getStudentId().toString());
	}
}
