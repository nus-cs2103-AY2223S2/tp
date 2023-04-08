package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddLectureCommand;
import seedu.address.logic.commands.add.AddModuleCommand;
import seedu.address.logic.commands.add.AddVideoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Parses input arguments and creates a new {@code AddCommand} object.
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an {@code AddCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_TAG, PREFIX_WATCH, PREFIX_TIMESTAMP);

        if (AddModuleCommandParserUtil.isArgumentsForAddingModule(argMultimap)) {
            return AddModuleCommandParserUtil.parse(argMultimap);
        } else if (AddLectureCommandParserUtil.isArgumentsForAddingLecture(argMultimap)) {
            return AddLectureCommandParserUtil.parse(argMultimap);
        } else if (AddVideoCommandParserUtil.isArgumentsForAddingVideo(argMultimap)) {
            return AddVideoCommandParserUtil.parse(argMultimap);
        } else {
            throw createInvalidCommandFormatException();
        }
    }

    private ParseException createInvalidCommandFormatException() {
        return new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    /**
     * Contains utility methods utilised by {@code AddModuleCommandParserUtil}, {@code AddLectureCommandParserUtil},
     * and {@code AddVideoCommandParserUtil}
     */
    private static class AddCommandParserUtil {
        /**
         * Extracts a set of tags from {@code argMultimap}.
         *
         * @param argMultimap A map of the arguments and their values
         * @return A set of tags extracted from {@code argMultimap}.
         * @throws ParseException Indicates that a tag did not conform to the expected format.
         */
        public static Set<Tag> extractTags(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String tagsStr = argMultimap.getValue(PREFIX_TAG).orElse("");
            return ParserUtil.parseMultiTags(tagsStr);
        }
    }

    /**
     * Contains utility methods for parsing input arguments and creating a new {@code AddModuleCommand} object
     * from it.
     */
    private static class AddModuleCommandParserUtil {
        /**
         * Returns true if {@code argMultimap} contains arguments that reflect that the intent is to add a module.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return True if {@code argMultimap} contains arguments that reflect that the intent is to add a
         *         module. Otherwise, false.
         */
        public static boolean isArgumentsForAddingModule(ArgumentMultimap argMultimap) {
            requireNonNull(argMultimap);

            return !argMultimap.getPreamble().isEmpty()
                    && argMultimap.getValue(PREFIX_MODULE).isEmpty()
                    && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
        }

        /**
         * Parses the arguments in {@code argMultimap} and use it to create an {@code AddModuleCommand} object.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return The {@code AddModuleCommand} object created from the arguments.
         * @throws ParseException Indicates that an argument value did not conform to the expected format.
         */
        public static AddModuleCommand parse(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            Module module = createModule(argMultimap);
            return new AddModuleCommand(module);
        }

        private static Module createModule(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode moduleCode = extractModuleCode(argMultimap);
            ModuleName moduleName = extractModuleName(argMultimap);
            Set<Tag> tags = AddCommandParserUtil.extractTags(argMultimap);

            return new Module(moduleCode, moduleName, tags, new ArrayList<>());
        }

        private static ModuleCode extractModuleCode(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String moduleCodeStr = argMultimap.getPreamble();
            return ParserUtil.parseModuleCode(moduleCodeStr);
        }

        private static ModuleName extractModuleName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String moduleNameStr = argMultimap.getValue(PREFIX_NAME).orElse("");
            return ParserUtil.parseModuleName(moduleNameStr);
        }
    }

    /**
     * Contains utility methods for parsing input arguments and creating a new {@code AddLectureCommand} object
     * from it.
     */
    private static class AddLectureCommandParserUtil {
        /**
         * Returns true if {@code argMultimap} contains arguments that reflect that the intent is to add a lecture.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return True if {@code argMultimap} contains arguments that reflect that the intent is to add a
         *         lecture. Otherwise, false.
         */
        public static boolean isArgumentsForAddingLecture(ArgumentMultimap argMultimap) {
            requireNonNull(argMultimap);

            return !argMultimap.getPreamble().isEmpty()
                    && argMultimap.getValue(PREFIX_MODULE).isPresent()
                    && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
        }

        /**
         * Parses the arguments in {@code argMultimap} and use it to create an {@code AddLectureCommand} object.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return The {@code AddLectureCommand} object created from the arguments.
         * @throws ParseException Indicates that an argument value did not conform to the expected format.
         */
        public static AddLectureCommand parse(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode moduleCode = extractModuleCode(argMultimap);
            Lecture lecture = createLecture(argMultimap);

            return new AddLectureCommand(moduleCode, lecture);
        }

        private static ModuleCode extractModuleCode(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
            return ParserUtil.parseModuleCode(moduleCodeStr);
        }

        private static Lecture createLecture(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            LectureName lectureName = extractLectureName(argMultimap);
            Set<Tag> tags = AddCommandParserUtil.extractTags(argMultimap);

            return new Lecture(lectureName, tags, new ArrayList<>());
        }

        private static LectureName extractLectureName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String lectureNameStr = argMultimap.getPreamble();
            return ParserUtil.parseLectureName(lectureNameStr);
        }
    }

    /**
     * Contains utility methods for parsing input arguments and creating a new {@code AddVideoCommand} object
     * from it.
     */
    private static class AddVideoCommandParserUtil {
        /**
         * Returns true if {@code argMultimap} contains arguments that reflect that the intent is to add a video.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return True if {@code argMultimap} contains arguments that reflect that the intent is to add a
         *         video. Otherwise, false.
         */
        public static boolean isArgumentsForAddingVideo(ArgumentMultimap argMultimap) {
            requireNonNull(argMultimap);

            return !argMultimap.getPreamble().isEmpty()
                    && argMultimap.getValue(PREFIX_MODULE).isPresent()
                    && argMultimap.getValue(PREFIX_LECTURE).isPresent();
        }

        /**
         * Parses the arguments in {@code argMultimap} and use it to create an {@code AddVideoCommand} object.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return The {@code AddVideoCommand} object created from the arguments.
         * @throws ParseException Indicates that an argument value did not conform to the expected format.
         */
        public static AddVideoCommand parse(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode moduleCode = extractModuleCode(argMultimap);
            LectureName lectureName = extractLectureName(argMultimap);
            Video video = createVideo(argMultimap);

            return new AddVideoCommand(moduleCode, lectureName, video);
        }

        private static ModuleCode extractModuleCode(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
            return ParserUtil.parseModuleCode(moduleCodeStr);
        }

        private static LectureName extractLectureName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String lectureNameStr = argMultimap.getValue(PREFIX_LECTURE).get();
            return ParserUtil.parseLectureName(lectureNameStr);
        }

        private static Video createVideo(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            VideoName videoName = extractVideoName(argMultimap);
            VideoTimestamp timestamp = extractVideoTimestamp(argMultimap);
            Set<Tag> tags = AddCommandParserUtil.extractTags(argMultimap);
            boolean hasWatched = hasWatchedExtract(argMultimap);

            return new Video(videoName, hasWatched, timestamp, tags);
        }

        private static VideoName extractVideoName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String videoNameStr = argMultimap.getPreamble();
            return ParserUtil.parseVideoName(videoNameStr);
        }

        private static VideoTimestamp extractVideoTimestamp(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String videoTimestampStr = argMultimap.getValue(PREFIX_TIMESTAMP).orElse(VideoTimestamp.DEFAULT_TIMESTAMP);
            return ParserUtil.parseVideoTimestamp(videoTimestampStr);
        }

        // A more fitting name might be "extractWatchStatus", however, that would not comply with the boolean
        // naming convention.
        private static boolean hasWatchedExtract(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            return argMultimap.getValue(PREFIX_WATCH).isPresent();
        }
    }
}
