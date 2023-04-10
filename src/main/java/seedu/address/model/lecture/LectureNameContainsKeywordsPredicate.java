package seedu.address.model.lecture;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Lecture}'s {@code name} matches any of the keywords given.
 */
public class LectureNameContainsKeywordsPredicate implements Predicate<ReadOnlyLecture> {
    private final List<String> keywords;

    public LectureNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyLecture lecture) {
        return keywords.stream()
                .anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(
                        StringUtil.joinSentenceToWord(lecture.getName().name),
                            StringUtil.joinSentenceToWord(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LectureNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LectureNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
