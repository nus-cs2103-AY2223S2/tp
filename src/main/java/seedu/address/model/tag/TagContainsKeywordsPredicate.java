package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream().anyMatch(keyword -> isMatch(student, keyword));
    }

    /**
     * Returns if any keyword matches with any of the student's tag.
     *
     * @param student Student.
     * @param keyword Keyword provided by the user.
     * @return Boolean value, if any keyword matches with any of the student's tag given.
     */
    public boolean isMatch(Student student, String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        String lowerCaseTagName = "";

        for (Tag tag : student.getTags()) {
            lowerCaseTagName = tag.tagName.toLowerCase();

            if (lowerCaseTagName.equals(lowerCaseKeyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an object is equal to the TagContainsKeywordsPredicate.
     *
     * @param other The object that need to compare with.
     * @return Boolean value if the object and the TagContainsKeywordsPredicate are the same.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
