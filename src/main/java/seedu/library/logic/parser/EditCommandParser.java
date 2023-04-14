package seedu.library.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_PROGRESS;
import static seedu.library.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_URL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.EditCommand;
import seedu.library.logic.commands.EditCommand.EditBookmarkDescriptor;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE,
                        PREFIX_PROGRESS, PREFIX_GENRE, PREFIX_AUTHOR, PREFIX_RATING, PREFIX_URL, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditBookmarkDescriptor editBookmarkDescriptor = new EditCommand.EditBookmarkDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editBookmarkDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PROGRESS).isPresent()) {
            editBookmarkDescriptor.setProgress(ParserUtil.parseProgress(argMultimap.getValue(PREFIX_PROGRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_GENRE).isPresent()) {
            editBookmarkDescriptor.setGenre(ParserUtil.parseGenre(argMultimap.getValue(PREFIX_GENRE).get()));
        }
        if (argMultimap.getValue(PREFIX_AUTHOR).isPresent()) {
            editBookmarkDescriptor.setAuthor(ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editBookmarkDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }
        if (argMultimap.getValue(PREFIX_URL).isPresent()) {
            editBookmarkDescriptor.setUrl((ParserUtil.parseUrl(argMultimap.getValue(PREFIX_URL).get())));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editBookmarkDescriptor::setTags);

        if (!editBookmarkDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editBookmarkDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
