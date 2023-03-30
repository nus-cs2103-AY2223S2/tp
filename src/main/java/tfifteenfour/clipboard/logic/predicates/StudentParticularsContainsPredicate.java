package tfifteenfour.clipboard.logic.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class StudentParticularsContainsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public StudentParticularsContainsPredicate(String[] keywords) {
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    public boolean test(Student student) {
        if (isValidStudentIdKeyword(keywords)) {
            return keywords.stream()
                    .anyMatch(keyword -> student.getStudentId()
                    .toString().toLowerCase().contains(keyword.toLowerCase()));
        } else {
            return keywords.stream()
                    .anyMatch(keyword -> student.getName().toString()
                    .toLowerCase().contains(keyword.toLowerCase()));
        }
    }

    /**
     * Checks whether keyword is a student ID by checking if the middle 7 characters are numeric
     * @param keywords
     * @return boolean
     */
    public boolean isValidStudentIdKeyword(List<String> keywords) {
        String firstKeyword = keywords.get(0);
        if (firstKeyword.length() < 7) {
            return false;
        }
        return isNumeric(firstKeyword.substring(1, 7));
    }

    public boolean isNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentParticularsContainsPredicate // instanceof handles nulls
                && keywords.equals(((StudentParticularsContainsPredicate) other).keywords)); // state check
    }

}
