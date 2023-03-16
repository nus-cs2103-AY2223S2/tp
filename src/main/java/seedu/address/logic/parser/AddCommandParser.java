package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddLectureCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddVideoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * Parses input arguments and creates a new {@code AddCommand} object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an {@code AddCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE, PREFIX_LECTURE);

        Optional<String> moduleCodeOpt = argMultimap.getValue(PREFIX_MODULE);
        Optional<String> lectureNameOpt = argMultimap.getValue(PREFIX_LECTURE);

        if (lectureNameOpt.isPresent()) {
            return parseAddVideoCommand(argMultimap.getPreamble(), lectureNameOpt, moduleCodeOpt);
        } else if (moduleCodeOpt.isPresent()) {
            return parseAddLectureCommand(argMultimap.getPreamble(), moduleCodeOpt);
        } else {
            return parseAddModuleCommand(argMultimap.getPreamble(), argMultimap.getValue(PREFIX_NAME));
        }
    }

    private AddCommand parseAddVideoCommand(String videoNameStr, Optional<String> lectureNameOpt,
            Optional<String> moduleCodeOpt) throws ParseException {

        if (!moduleCodeOpt.isPresent() || !lectureNameOpt.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String moduleCodeStr = moduleCodeOpt.get();
        String lectureNameStr = lectureNameOpt.get();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);
        VideoName videoName = ParserUtil.parseVideoName(videoNameStr);

        Video video = new Video(videoName, false, new HashSet<>());

        return new AddVideoCommand(moduleCode, lectureName, video);
    }

    private AddCommand parseAddLectureCommand(String lectureNameStr, Optional<String> moduleCodeOpt)
            throws ParseException {

        if (!moduleCodeOpt.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String moduleCodeStr = moduleCodeOpt.get();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);

        Lecture lecture = new Lecture(lectureName, new HashSet<>(), new ArrayList<>());

        return new AddLectureCommand(moduleCode, lecture);
    }

    private AddCommand parseAddModuleCommand(String moduleCodeStr, Optional<String> moduleNameOpt)
            throws ParseException {

        String moduleNameStr = moduleNameOpt.orElse("");

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        ModuleName moduleName = ParserUtil.parseModuleName(moduleNameStr);

        Module module = new Module(moduleCode, moduleName, new HashSet<>(), new ArrayList<>());

        return new AddModuleCommand(module);
    }

}
