package taa.logic.parser;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;
import static taa.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.EditCommand;
import taa.logic.commands.MarkAttendanceCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Attendance;
import taa.model.tag.Tag;

/**
 * Parses input arguments and creates a new MarkAttendanceCommand object
 */
public class MarkAttendanceParser implements Parser<MarkAttendanceCommand> {
    @Override
    public MarkAttendanceCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_CLASS_TAG, PREFIX_WEEK);

        Index index;
        int week = -1;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
        }

        if (week == -1) {
            throw new ParseException(Attendance.errorMsg);
        }

        return new MarkAttendanceCommand(index, Index.fromOneBased(week));
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
