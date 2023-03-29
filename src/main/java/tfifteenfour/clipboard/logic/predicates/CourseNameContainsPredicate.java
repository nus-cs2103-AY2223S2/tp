package tfifteenfour.clipboard.logic.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.model.course.Course;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class CourseNameContainsPredicate implements Predicate<Course> {
    private final List<String> keywords;

    public CourseNameContainsPredicate(String[] keywords) {
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    public boolean test(Course course) {
        return keywords.stream()
                .anyMatch(keyword -> course.getCourseCode().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CourseNameContainsPredicate // instanceof handles nulls
                && keywords.equals(((CourseNameContainsPredicate) other).keywords)); // state check
    }

}
