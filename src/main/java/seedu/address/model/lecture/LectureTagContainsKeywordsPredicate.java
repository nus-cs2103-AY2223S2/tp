package seedu.address.model.lecture;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Lecture}'s {@code tags} matches any of the keywords given.
 */
public class LectureTagContainsKeywordsPredicate implements Predicate<ReadOnlyLecture> {
    private final List<String> keywords;

    public LectureTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyLecture lecture) {
        return keywords.stream()
                .anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(
                        StringUtil.joinTagsAsString(lecture.getTags()),
                            StringUtil.joinSentenceToWord(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LectureTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LectureTagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
