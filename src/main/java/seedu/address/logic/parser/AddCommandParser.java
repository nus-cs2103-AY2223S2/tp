package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashSet;

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

        if (isAddModule(argMultimap)) {
            return parseAddModuleCommand(argMultimap);
        } else if (isAddLecture(argMultimap)) {
            return parseAddLectureCommand(argMultimap);
        } else if (isAddVideo(argMultimap)) {
            return parseAddVideoCommand(argMultimap);
        } else {
            throw createInvalidCommandFormatException();
        }
    }

    private boolean isAddModule(ArgumentMultimap argMultimap) {
        return !argMultimap.getPreamble().isEmpty()
                && argMultimap.getValue(PREFIX_MODULE).isEmpty()
                && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
    }

    private boolean isAddLecture(ArgumentMultimap argMultimap) {
        return !argMultimap.getPreamble().isEmpty()
                && argMultimap.getValue(PREFIX_MODULE).isPresent()
                && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
    }

    private boolean isAddVideo(ArgumentMultimap argMultimap) {
        return !argMultimap.getPreamble().isEmpty()
                && argMultimap.getValue(PREFIX_MODULE).isPresent()
                && argMultimap.getValue(PREFIX_LECTURE).isPresent();
    }

    private ParseException createInvalidCommandFormatException() {
        return new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    private AddCommand parseAddModuleCommand(ArgumentMultimap argMultimap) throws ParseException {
        String moduleCodeStr = argMultimap.getPreamble();
        String moduleNameStr = argMultimap.getValue(PREFIX_NAME).orElse("");

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        ModuleName moduleName = ParserUtil.parseModuleName(moduleNameStr);

        Module module = new Module(moduleCode, moduleName, new HashSet<>(), new ArrayList<>());

        return new AddModuleCommand(module);
    }

    private AddCommand parseAddLectureCommand(ArgumentMultimap argMultimap) throws ParseException {
        String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
        String lectureNameStr = argMultimap.getPreamble();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);

        Lecture lecture = new Lecture(lectureName, new HashSet<>(), new ArrayList<>());

        return new AddLectureCommand(moduleCode, lecture);
    }

    private AddCommand parseAddVideoCommand(ArgumentMultimap argMultimap) throws ParseException {

        String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
        String lectureNameStr = argMultimap.getValue(PREFIX_LECTURE).get();
        String videoNameStr = argMultimap.getPreamble();

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);
        VideoName videoName = ParserUtil.parseVideoName(videoNameStr);

        Video video = new Video(videoName, false, new HashSet<>());

        return new AddVideoCommand(moduleCode, lectureName, video);
    }

}
