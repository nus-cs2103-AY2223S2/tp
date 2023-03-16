package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.VideoName;

/**
 * Parses input arguments and creates a new TagCommand object
 */

public class TagCommandParser implements Parser<TagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public TagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_VIDEO);

        List<Prefix> presentPrefixes = streamOfPrefixesPresent(argMultimap,
                PREFIX_TAG, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_VIDEO);

        if (!isValidTagCommand(presentPrefixes) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        Set<Tag> tags = ParseArgument.parseMultiTags(argMultimap.getValue(PREFIX_TAG).get());
        ModuleCode moduleCode = ParseArgument.parseModule(argMultimap.getValue(PREFIX_MODULE).get());

        if (isTaggingMod(presentPrefixes)) {
            return new TagCommand(tags, moduleCode);
        }

        else if (isTaggingLec(presentPrefixes)) {
            LectureName lectureName = ParseArgument.parseLecture(argMultimap.getValue(PREFIX_LECTURE).get());
            return new TagCommand(tags, moduleCode, lectureName);
        }

        else {
            LectureName lectureName = ParseArgument.parseLecture(argMultimap.getValue(PREFIX_LECTURE).get());
            VideoName videoName = ParseArgument.parseVideo(argMultimap.getValue(PREFIX_VIDEO).get());
            return new TagCommand(tags, moduleCode, lectureName, videoName);
        }

    }

    private static List<Prefix> streamOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).collect(Collectors.toList());
    }

    private boolean isValidTagCommand(List<Prefix> presentPrefixes) {
        return Stream.of(isTaggingMod(presentPrefixes), isTaggingLec(presentPrefixes),
                isTaggingVideo(presentPrefixes)).allMatch(isPresent -> isPresent);
    }

    private boolean isTaggingMod(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG) &&
                presentPrefixes.contains(PREFIX_MODULE) &&
                !presentPrefixes.contains(PREFIX_VIDEO) &&
                !presentPrefixes.contains(PREFIX_LECTURE)) {
            return true;
        }
        return false;
    }

    private boolean isTaggingLec(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG) &&
                presentPrefixes.contains(PREFIX_MODULE) &&
                presentPrefixes.contains(PREFIX_LECTURE) &&
                !presentPrefixes.contains(PREFIX_VIDEO)) {
            return true;
        }
        return false;
    }

    private boolean isTaggingVideo(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG) &&
                presentPrefixes.contains(PREFIX_MODULE) &&
                presentPrefixes.contains(PREFIX_LECTURE) &&
                presentPrefixes.contains(PREFIX_VIDEO)) {
            return true;
        }
        return false;
    }
}