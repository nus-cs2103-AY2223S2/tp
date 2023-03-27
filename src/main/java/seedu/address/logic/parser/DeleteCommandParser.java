package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Optional;

import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.delete.DeleteLectureCommand;
import seedu.address.logic.commands.delete.DeleteModuleCommand;
import seedu.address.logic.commands.delete.DeleteMultipleLecturesCommands;
import seedu.address.logic.commands.delete.DeleteMultipleModulesCommand;
import seedu.address.logic.commands.delete.DeleteVideoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_LECTURE);

        Optional<String> moduleCodeOptional = argMultimap.getValue(PREFIX_MODULE);
        Optional<String> lectureNameOptional = argMultimap.getValue(PREFIX_LECTURE);
        String preamble = argMultimap.getPreamble();

        try {

            if (moduleCodeOptional.isPresent()) {
                ModuleCode moduleCode = new ModuleCode(moduleCodeOptional.get());

                if (lectureNameOptional.isPresent()) {

                    LectureName lectureName = new LectureName(lectureNameOptional.get());
                    VideoName videoName = new VideoName(preamble);
                    return new DeleteVideoCommand(moduleCode, lectureName, videoName);
                } else {
                    LectureName[] lectureNames = MultipleEventsParser.parseLectureNames(preamble);

                    if (lectureNames.length > 1) {
                        return new DeleteMultipleLecturesCommands(moduleCode, lectureNames);
                    } else {
                        return new DeleteLectureCommand(moduleCode, lectureNames[0]);
                    }
                }
            } else {
                ModuleCode[] moduleCodes = MultipleEventsParser.parseModuleCodes(preamble);

                if (moduleCodes.length > 1) {
                    return new DeleteMultipleModulesCommand(moduleCodes);
                } else {
                    return new DeleteModuleCommand(moduleCodes[0]);
                }
            }

        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        } catch (IllegalArgumentException iae) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    iae.getMessage() + "\n" + DeleteCommand.MESSAGE_USAGE));
        }

    }

}
