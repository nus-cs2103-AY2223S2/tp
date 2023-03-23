package seedu.library.model.bookmark;

import java.util.List;
import java.util.function.Predicate;

import seedu.library.commons.util.StringUtil;

/**
 * Tests that a {@code Bookmark}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Bookmark> {
    private final List<String> titleKeywords;
    private final List<String> genreKeywords;
    private final List<String> tagKeywords;
    private final List<String> authorKeywords;

    /**
     * Constructs an {@code TitleContainsKeywordsPredicate}.
     *
     * @param titleKeywords keywords for searching title
     * @param genreKeywords keywords for searching genre
     * @param tagKeywords keywords for searching tag
     * @param authorKeywords keywords for searching author
     */
    public TitleContainsKeywordsPredicate(List<String> titleKeywords, List<String> genreKeywords,
                                          List<String> tagKeywords, List<String> authorKeywords) {
        this.titleKeywords = titleKeywords;
        this.genreKeywords = genreKeywords;
        this.tagKeywords = tagKeywords;
        this.authorKeywords = authorKeywords;
    }

    @Override
    public boolean test(Bookmark bookmark) {
        return testTitle(bookmark) && testGenre(bookmark) && testTag(bookmark) && testAuthor(bookmark);
    }

    private boolean testTitle(Bookmark bookmark) {
        if (titleKeywords == null) {
            return true;
        } else {
            return titleKeywords.stream()
                    .allMatch(keyword -> StringUtil.containsWordIgnoreCase(bookmark.getTitle().value, keyword));
        }
    }

    private boolean testGenre(Bookmark bookmark) {
        if (genreKeywords == null) {
            return true;
        } else {
            return genreKeywords.stream()
                    .allMatch(keyword -> StringUtil.containsWordIgnoreCase(bookmark.getGenre().value, keyword));
        }
    }

    private boolean testTag(Bookmark bookmark) {
        if (tagKeywords == null) {
            return true;
        } else {
            return tagKeywords.stream()
                    .allMatch(keyword -> bookmark.getTags()
                            .stream().anyMatch(tag -> keyword.equalsIgnoreCase(tag.tagName)));
        }
    }

    private boolean testAuthor(Bookmark bookmark) {
        if (authorKeywords == null) {
            return true;
        } else {
            return authorKeywords.stream()
                    .allMatch(keyword -> StringUtil.containsWordIgnoreCase(bookmark.getAuthor().value, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && titleKeywords.equals(((TitleContainsKeywordsPredicate) other).titleKeywords)); // state check
    }

}
