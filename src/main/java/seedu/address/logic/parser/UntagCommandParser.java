package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.VideoName;

/**
 * Parses input arguments and creates a new TagCommand object
 */

public class UntagCommandParser implements Parser<UntagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public UntagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_MODULE, PREFIX_LECTURE);

        List<Prefix> presentPrefixes = streamOfPrefixesPresent(argMultimap,
                PREFIX_TAG, PREFIX_MODULE, PREFIX_LECTURE);

        if (!isValidUntagCommand(presentPrefixes)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        Tag tag = ParseArgument.parseSingleTag(argMultimap.getValue(PREFIX_TAG).get());

        if (isUntaggingMod(presentPrefixes)) {
            ModuleCode moduleCode = ParseArgument.parseModule(argMultimap.getPreamble());
            return new UntagCommand(tag, moduleCode);
        }

        else if (isUntaggingLec(presentPrefixes)) {
            ModuleCode moduleCode = ParseArgument.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
            LectureName lectureName = ParseArgument.parseLecture(argMultimap.getPreamble());
            return new UntagCommand(tag, moduleCode, lectureName);
        }

        else {
            ModuleCode moduleCode = ParseArgument.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
            LectureName lectureName = ParseArgument.parseLecture(argMultimap.getValue(PREFIX_LECTURE).get());
            VideoName videoName = ParseArgument.parseVideo(argMultimap.getPreamble());
            return new UntagCommand(tag, moduleCode, lectureName, videoName);
        }

    }

    private static List<Prefix> streamOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).collect(Collectors.toList());
    }

    private boolean isValidUntagCommand(List<Prefix> presentPrefixes) {
        return Stream.of(isUntaggingMod(presentPrefixes), isUntaggingLec(presentPrefixes),
                isUntaggingVideo(presentPrefixes)).anyMatch(isPresent -> true);
    }

    private boolean isUntaggingMod(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG) &&
                !presentPrefixes.contains(PREFIX_MODULE) &&
                !presentPrefixes.contains(PREFIX_LECTURE)) {
            return true;
        }
        return false;
    }

    private boolean isUntaggingLec(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG) &&
                presentPrefixes.contains(PREFIX_MODULE) &&
                !presentPrefixes.contains(PREFIX_LECTURE)) {
            return true;
        }
        return false;
    }

    private boolean isUntaggingVideo(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG) &&
                presentPrefixes.contains(PREFIX_MODULE) &&
                presentPrefixes.contains(PREFIX_LECTURE)) {
            return true;
        }
        return false;
    }
}