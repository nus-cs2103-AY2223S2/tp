package tfifteenfour.clipboard.logic.parser;

import java.util.Comparator;

import tfifteenfour.clipboard.logic.commands.studentcommands.SortCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @param args The arguments provided by the user.
     * @return The new SortCommand object.
     * @throws ParseException If the user input does not conform to the expected format.
     */
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

/**
 * Comparator for sorting students by name.
 */
class AlphaNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().toString().compareTo(s2.getName().toString());
    }
}

/**
 * Comparator for sorting students by student ID.
 */
class NumericAlphaStudentIdComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getStudentId().toString().compareTo(s2.getStudentId().toString());
    }
}
