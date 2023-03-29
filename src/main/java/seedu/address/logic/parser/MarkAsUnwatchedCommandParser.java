package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Optional;

import seedu.address.logic.commands.mark.MarkAsUnwatchedCommand;
import seedu.address.logic.commands.mark.MarkCommand;
import seedu.address.logic.commands.mark.MarkMultipleAsUnwatchedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

/**
 * Parses input arguments and creates a new MarkAsUnwatchedCommand executable object
 */
public class MarkAsUnwatchedCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAsUnwatchedCommand
     * and returns a MarkAsUnwatchedCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public MarkCommand parse(String args) throws ParseException {

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
            VideoName[] videoNames = MultipleEventsParser.parseVideoNames(preamble);

            if (videoNames.length == 1) {
                return new MarkAsUnwatchedCommand(videoNames[0], moduleCode, lectureName);
            } else {
                return new MarkMultipleAsUnwatchedCommand(videoNames, moduleCode, lectureName);
            }

        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
