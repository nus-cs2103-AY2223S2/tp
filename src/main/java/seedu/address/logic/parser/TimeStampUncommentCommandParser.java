package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIDEO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.TimeStampUncommentCommand;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.TimeStampComment;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Parses input arguments and creates a new TimeStampUncommentCommand object
 */

public class TimeStampUncommentCommandParser implements Parser<TimeStampUncommentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TimeStampUncommentCommand
     * and returns an TimeStampUncommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public TimeStampUncommentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMMENT, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_VIDEO);

        List<Prefix> presentPrefixes = streamOfPrefixesPresent(argMultimap,
                PREFIX_COMMENT, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_VIDEO);

        if (presentPrefixes.size() < 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimeStampUncommentCommand.MESSAGE_USAGE));
        }

        Set<TimeStampComment> comments = ParserUtil.parseTimeStampComments(argMultimap.getValue(PREFIX_COMMENT).get());

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
        LectureName lectureName = ParserUtil.parseLectureName(argMultimap.getValue(PREFIX_LECTURE).get());
        VideoName videoName = ParserUtil.parseVideoName(argMultimap.getValue(PREFIX_VIDEO).get());
        VideoTimestamp videoTimestamp = ParserUtil.parseVideoTimestamp(argMultimap.getPreamble());

        return new TimeStampUncommentCommand(comments, moduleCode, lectureName, videoName, videoTimestamp);
    }

    private static List<Prefix> streamOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(
                        prefix -> argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList());
    }
}
