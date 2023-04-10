package seedu.event.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.event.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.event.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.event.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.event.commons.core.index.Index;
import seedu.event.logic.commands.EditCommand;
import seedu.event.logic.commands.EditCommand.EditEventDescriptor;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RATE,
                        PREFIX_ADDRESS, PREFIX_TIME_START, PREFIX_TIME_END, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_RATE).isPresent()) {
            editEventDescriptor.setRate(ParserUtil.parseRate(argMultimap.getValue(PREFIX_RATE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editEventDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME_START).isPresent()) {
            editEventDescriptor.setStartTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME_START).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME_END).isPresent()) {
            editEventDescriptor.setEndTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME_END).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEventDescriptor::setTags);

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEventDescriptor);
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
