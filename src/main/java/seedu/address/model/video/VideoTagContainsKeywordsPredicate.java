package seedu.address.model.video;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Video}'s {@code tags} matches any of the keywords given.
 */
public class VideoTagContainsKeywordsPredicate implements Predicate<Video> {
    private final List<String> keywords;

    public VideoTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Video video) {
        return keywords.stream()
                .anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(
                        StringUtil.joinTagsAsString(video.getTags()),
                            StringUtil.joinSentenceToWord(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VideoTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VideoTagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
