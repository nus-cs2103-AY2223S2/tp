package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.tag.Tag;

/**
 * A utility class for {@code Tag}.
 */
public class TagUtil {

    /**
     * Converts {@code tags} into a string of tag names separated by ", ".<p>
     * Usable as an argument for the prefix {@link CliSyntax#PREFIX_TAG}.
     *
     * @param tags The set of tags to be converted.
     * @return A string of tag names separated by ", ".
     */
    public static String getTagsStr(Collection<Tag> tags) {
        requireNonNull(tags);
        return String.join(", ", tags.stream().map(t -> t.tagName).collect(Collectors.toList()));
    }

    /**
     * Converts {@code tags} into a string of tag names separated by ", ".<p>
     * Usable as an argument for the prefix {@link CliSyntax#PREFIX_TAG}.
     *
     * @param tags The set of tags to be converted.
     * @return A string of tag names separated by ", ".
     */
    public static String getTagsStr(Tag... tags) {
        requireNonNull(tags);
        return getTagsStr(Arrays.asList(tags));
    }

}
