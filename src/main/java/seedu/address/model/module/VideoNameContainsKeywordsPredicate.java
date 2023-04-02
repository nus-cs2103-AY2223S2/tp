package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.video.Video;

/**
 * Tests that a {@code Video}'s {@code name} matches any of the keywords given.
 */
public class VideoNameContainsKeywordsPredicate implements Predicate<Video> {
    private final List<String> keywords;

    public VideoNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Video video) {
        return keywords.stream()
                .anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(
                        StringUtil.joinSentenceToWord(video.getName().name),
                            StringUtil.joinSentenceToWord(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VideoNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VideoNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
