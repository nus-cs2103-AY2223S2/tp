package codoc.model.person;

import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Course} matches any of the keywords given.
 */
public class CourseContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public CourseContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> person.getCourse().course.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CourseContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CourseContainsKeywordsPredicate) other).keywords)); // state check
    }
    @Override
    public String toString() {
        return PREFIX_COURSE + keywords.stream().reduce("", String::concat);
    }

}
