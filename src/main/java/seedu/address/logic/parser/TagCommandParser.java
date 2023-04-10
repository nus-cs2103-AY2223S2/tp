package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

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
                ArgumentTokenizer.tokenize(args,
                        PREFIX_TAG, PREFIX_MODULE, PREFIX_LECTURE);

        List<Prefix> presentPrefixes = streamOfPrefixesPresent(argMultimap,
                PREFIX_TAG, PREFIX_MODULE, PREFIX_LECTURE);

        if (!isValidTagCommand(presentPrefixes) || presentPrefixes.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        Set<Tag> tags = ParserUtil.parseMultiTags(argMultimap.getValue(PREFIX_TAG).get());

        if (isTaggingMod(presentPrefixes)) {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getPreamble());
            return new TagCommand(tags, moduleCode);
        } else if (isTaggingLec(presentPrefixes)) {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
            LectureName lectureName = ParserUtil.parseLectureName(argMultimap.getPreamble());
            return new TagCommand(tags, moduleCode, lectureName);
        } else {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
            LectureName lectureName = ParserUtil.parseLectureName(argMultimap.getValue(PREFIX_LECTURE).get());
            VideoName videoName = ParserUtil.parseVideoName(argMultimap.getPreamble());
            return new TagCommand(tags, moduleCode, lectureName, videoName);
        }

    }
    private static List<Prefix> streamOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(
                prefix -> argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList());
    }

    private boolean isValidTagCommand(List<Prefix> presentPrefixes) {
        return Stream.of(isTaggingMod(presentPrefixes), isTaggingLec(presentPrefixes),
                isTaggingVideo(presentPrefixes)).anyMatch(isPresent -> isPresent.equals(true));
    }

    private boolean isTaggingMod(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG)
                && !presentPrefixes.contains(PREFIX_MODULE)
                && !presentPrefixes.contains(PREFIX_LECTURE)
                && presentPrefixes.size() <= 3) {
            return true;
        }
        return false;
    }

    private boolean isTaggingLec(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG)
                && presentPrefixes.contains(PREFIX_MODULE)
                && !presentPrefixes.contains(PREFIX_LECTURE)
                && presentPrefixes.size() <= 3) {
            return true;
        }
        return false;
    }

    private boolean isTaggingVideo(List<Prefix> presentPrefixes) {
        if (presentPrefixes.contains(PREFIX_TAG)
                && presentPrefixes.contains(PREFIX_MODULE)
                && presentPrefixes.contains(PREFIX_LECTURE)
                && presentPrefixes.size() <= 3) {
            return true;
        }
        return false;
    }
}
