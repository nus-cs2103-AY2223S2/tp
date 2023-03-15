package seedu.library.testutil;

import static seedu.library.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_PROGRESS;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.library.logic.commands.AddCommand;
import seedu.library.logic.commands.EditCommand.EditBookmarkDescriptor;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.tag.Tag;

/**
 * A utility class for Bookmark.
 */
public class BookmarkUtil {

    /**
     * Returns an add command string for adding the {@code bookmark}.
     */
    public static String getAddCommand(Bookmark bookmark) {
        return AddCommand.COMMAND_WORD + " " + getBookmarkDetails(bookmark);
    }

    /**
     * Returns the part of command string for the given {@code bookmark}'s details.
     */
    public static String getBookmarkDetails(Bookmark bookmark) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + bookmark.getTitle().value + " ");
        sb.append(PREFIX_PROGRESS + bookmark.getProgress().value + " ");
        sb.append(PREFIX_GENRE + bookmark.getGenre().value + " ");
        sb.append(PREFIX_AUTHOR + bookmark.getAuthor().value + " ");
        bookmark.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditBookmarkDescriptor}'s details.
     */
    public static String getEditBookmarkDescriptorDetails(EditBookmarkDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.value).append(" "));
        descriptor.getProgress().ifPresent(progress -> sb.append(PREFIX_PROGRESS).append(progress.value).append(" "));
        descriptor.getGenre().ifPresent(genre -> sb.append(PREFIX_GENRE).append(genre.value).append(" "));
        descriptor.getAuthor().ifPresent(author -> sb.append(PREFIX_AUTHOR).append(author.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
