package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BY_TAG, PREFIX_MODULE, PREFIX_LECTURE);

        String keywords = argMultimap.getPreamble();

        if (keywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        List<String> keywordList = Arrays.asList(keywords.split("\\s*,\\s*"));

        boolean hasByTag = argMultimap.getValue(PREFIX_BY_TAG).isPresent();
        Optional<String> moduleCodeOpt = argMultimap.getValue(PREFIX_MODULE);
        Optional<String> lectureNameOpt = argMultimap.getValue(PREFIX_LECTURE);

        if (lectureNameOpt.isPresent()) {
            return parseFindVideoCommand(keywordList, lectureNameOpt, moduleCodeOpt, hasByTag);
        }
        if (moduleCodeOpt.isPresent()) {
            return parseFindLectureCommand(keywordList, moduleCodeOpt, hasByTag);
        }
        return new FindCommand(keywordList, hasByTag);
    }

    private FindCommand parseFindLectureCommand(List<String> keywordList,
            Optional<String> moduleCodeOpt, boolean hasByTag) throws ParseException {

        if (!moduleCodeOpt.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String moduleCodeStr = moduleCodeOpt.get();

        if (hasByTag) {
            keywordList = keywordList.subList(0, keywordList.size());
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        return new FindCommand(keywordList, moduleCode, hasByTag);
    }

    private FindCommand parseFindVideoCommand(List<String> keywordList,
            Optional<String> lectureNameOpt, Optional<String> moduleCodeOpt,
            boolean hasByTag) throws ParseException {

        if (!moduleCodeOpt.isPresent() || !lectureNameOpt.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String moduleCodeStr = moduleCodeOpt.get();
        String lectureNameStr = lectureNameOpt.get();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);

        if (hasByTag) {
            keywordList = keywordList.subList(0, keywordList.size());
        }

        return new FindCommand(keywordList, moduleCode, lectureName, hasByTag);

    }
}
