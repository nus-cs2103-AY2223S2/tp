package taa.logic.parser;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_TAG;
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

public class MarkAttendanceParser implements Parser<MarkAttendanceCommand> {
    @Override
    public MarkAttendanceCommand parse(String userInput) throws ParseException {
        System.out.println("1 we are in the markAttendaceParser");
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_TAG, PREFIX_WEEK);

        Index index;
        int week = 1;

        System.out.println("2 we are in the markAttendaceParser");
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        System.out.println("3 we are in the markAttendaceParser");
        MarkAttendanceCommand.EditStudentDescriptor editStudentDescriptor =
                new MarkAttendanceCommand.EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        System.out.println("4 we are in the markAttendaceParser");
        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            System.out.println(argMultimap.getValue(PREFIX_WEEK).get());
            week = ParserUtil.parseWeek("1");
        }
        System.out.println("5 we are in the markAttendaceParser");
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        System.out.println("6 we are in the markAttendaceParser");

        if (week == -1) {
            throw new ParseException(Attendance.errorMsg);
        }

        System.out.println("we have reached the end of mark attendance");
        return new MarkAttendanceCommand(index, editStudentDescriptor, week);
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
