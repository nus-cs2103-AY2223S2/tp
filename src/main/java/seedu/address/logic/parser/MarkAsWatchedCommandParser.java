package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Optional;

import seedu.address.logic.commands.MarkAsWatchedCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

/**
 * Parses input arguments and creates a new MarkAsWatchedCommand executable object
 */
public class MarkAsWatchedCommandParser implements Parser<MarkAsWatchedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAsWatched Command
     * and returns a MarkAsWatchedCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public MarkAsWatchedCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_LECTURE);

        Optional<String> moduleCodeOptional = argMultiMap.getValue(PREFIX_MODULE);
        Optional<String> lectureNameOptional = argMultiMap.getValue(PREFIX_LECTURE);
        String preamble = argMultiMap.getPreamble();

        try {

            if (!moduleCodeOptional.isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
            }

            if (!lectureNameOptional.isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
            }
            ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeOptional.get());
            LectureName lectureName = ParserUtil.parseLectureName(lectureNameOptional.get());
            VideoName videoName = ParserUtil.parseVideoName(preamble);

            return new MarkAsWatchedCommand(videoName, moduleCode, lectureName);

        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }

    }
}
