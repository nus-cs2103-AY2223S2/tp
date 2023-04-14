package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.library.logic.commands.FindCommand;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.bookmark.BookmarkContainsKeywordsPredicate;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String MESSAGE_EMPTY_KEYWORD = "Keyword field cannot be empty";
    public static final String MESSAGE_INVALID_GENRE = "Genre provided does not exist";
    public static final String MESSAGE_INVALID_TAG = "Tag provided does not exist";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR, PREFIX_GENRE, PREFIX_TAG);
        if (!areAnyPrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_TAG, PREFIX_GENRE, PREFIX_AUTHOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> titleKeywords = null;
        List<String> genreKeywords = null;
        List<String> tagKeywords = null;
        List<String> authorKeywords = null;

        if (isPrefixPresent(argMultimap, PREFIX_TITLE)) {
            titleKeywords = parseTitle(argMultimap);
        }

        if (isPrefixPresent(argMultimap, PREFIX_GENRE)) {
            genreKeywords = parseGenre(argMultimap);
        }

        if (isPrefixPresent(argMultimap, PREFIX_TAG)) {
            tagKeywords = parseTag(argMultimap);
        }

        if (isPrefixPresent(argMultimap, PREFIX_AUTHOR)) {
            authorKeywords = parseAuthor(argMultimap);
        }


        return new FindCommand(new BookmarkContainsKeywordsPredicate(
                titleKeywords, genreKeywords, tagKeywords, authorKeywords));
    }

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    private static List<String> parseTitle(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_TITLE).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EMPTY_KEYWORD));
        }
        return Arrays.asList(
                argMultimap.getValue(PREFIX_TITLE).get().trim().split("\\s+"));
    }

    private static List<String> parseGenre(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_GENRE).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EMPTY_KEYWORD));
        }
        List<String> genreKeywords = Arrays.asList(
                argMultimap.getValue(PREFIX_GENRE).get().trim().split("\\s+"));
        if (genreKeywords.stream().anyMatch(keyword -> !Genre.isValidGenre(keyword))) {
            throw new ParseException(MESSAGE_INVALID_GENRE);
        }
        return genreKeywords;
    }

    private static List<String> parseTag(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_TAG).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EMPTY_KEYWORD));
        }
        List<String> tagKeywords = Arrays.asList(
                argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+"));
        if (tagKeywords.stream().anyMatch(keyword -> !Tag.isValidTagName(keyword))) {
            throw new ParseException(MESSAGE_INVALID_TAG);
        }
        return tagKeywords;
    }

    private static List<String> parseAuthor(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_AUTHOR).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EMPTY_KEYWORD));
        }
        List<String> authorKeywords = Arrays.asList(
                argMultimap.getValue(PREFIX_AUTHOR).get().trim().split("\\s+"));
        return authorKeywords;
    }

}
