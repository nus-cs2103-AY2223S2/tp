package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_LECTURE);

        Optional<String> moduleCodeOpt = argMultimap.getValue(PREFIX_MODULE);
        Optional<String> lectureNameOpt = argMultimap.getValue(PREFIX_LECTURE);

        if (lectureNameOpt.isPresent()) {
            return parseListVideoCommand(lectureNameOpt, moduleCodeOpt);
        }
        if (moduleCodeOpt.isPresent()) {
            return parseListLectureCommand(moduleCodeOpt);
        }
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        return new ListCommand();
    }

    private ListCommand parseListLectureCommand(Optional<String> moduleCodeOpt) throws ParseException {

        if (!moduleCodeOpt.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String moduleCodeStr = moduleCodeOpt.get();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        return new ListCommand(moduleCode);
    }

    private ListCommand parseListVideoCommand(Optional<String> lectureNameOpt,
            Optional<String> moduleCodeOpt) throws ParseException {

        if (!moduleCodeOpt.isPresent() || !lectureNameOpt.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String moduleCodeStr = moduleCodeOpt.get();
        String lectureNameStr = lectureNameOpt.get();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);

        return new ListCommand(moduleCode, lectureName);
    }

}
