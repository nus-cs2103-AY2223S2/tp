package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_PROGRESS;
import static seedu.library.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_URL;

import java.util.Set;
import java.util.stream.Stream;

import seedu.library.logic.commands.AddCommand;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Rating;
import seedu.library.model.bookmark.Title;
import seedu.library.model.bookmark.Url;
import seedu.library.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR,
                        PREFIX_PROGRESS, PREFIX_GENRE, PREFIX_RATING, PREFIX_URL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_GENRE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Progress progress = argMultimap.getValue(PREFIX_PROGRESS).isPresent()
                ? ParserUtil.parseProgress(argMultimap.getValue(PREFIX_PROGRESS).get())
                : null;
        Genre genre = ParserUtil.parseGenre(argMultimap.getValue(PREFIX_GENRE).get());
        Author author = argMultimap.getValue(PREFIX_AUTHOR).isPresent()
                ? ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get())
                : null;
        Rating rating = argMultimap.getValue(PREFIX_RATING).isPresent()
                ? ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get())
                : Rating.DEFAULT_RATING;
        Url url = argMultimap.getValue(PREFIX_URL).isPresent()
                ? ParserUtil.parseUrl(argMultimap.getValue(PREFIX_URL).get())
                : new Url("");
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Bookmark bookmark = new Bookmark(title, progress, genre, author, rating, url, tagList);
        boolean hasTags = arePrefixesPresent(argMultimap, PREFIX_TAG);
        return new AddCommand(bookmark, hasTags);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
