package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CONFLICTING_ARGS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNWATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;

import java.util.Set;

import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditLectureCommand;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.logic.commands.edit.EditModuleCommand;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.edit.EditVideoCommand;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an {@code EditCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_NAME, PREFIX_MODULE, PREFIX_LECTURE,
                        PREFIX_TAG, PREFIX_UNWATCH, PREFIX_WATCH, PREFIX_TIMESTAMP);

        if (EditModuleCommandParserUtil.isArgumentsForEditingModule(argMultimap)) {
            return EditModuleCommandParserUtil.parse(argMultimap);
        } else if (EditLectureCommandParserUtil.isArgumentsForEditingLecture(argMultimap)) {
            return EditLectureCommandParserUtil.parse(argMultimap);
        } else if (EditVideoCommandParserUtil.isArgumentsForEditingVideo(argMultimap)) {
            return EditVideoCommandParserUtil.parse(argMultimap);
        } else {
            throw createInvalidCommandFormatException();
        }
    }

    private ParseException createInvalidCommandFormatException() {
        return new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    /**
     * Contains utility methods utilised by {@code EditModuleCommandParserUtil}, {@code EditLectureCommandParserUtil},
     * and {@code EditVideoCommandParserUtil}
     */
    private static class EditCommandParserUtil {
        /**
         * Extracts a set of updated tags from {@code argMultimap}.
         *
         * @param argMultimap A map of the arguments and their values
         * @return A set of updated tags extracted from {@code argMultimap}.
         * @throws ParseException Indicates that a tag did not conform to the expected format.
         */
        public static Set<Tag> extractUpdatedTags(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String updatedTagsStr = argMultimap.getValue(PREFIX_TAG).orElse(null);
            return updatedTagsStr == null ? null : ParserUtil.parseMultiTags(updatedTagsStr);
        }
    }

    /**
     * Contains utility methods for parsing input arguments and creating a new {@code EditModuleCommand}
     * object from it.
     */
    private static class EditModuleCommandParserUtil {
        /**
         * Returns true if {@code argMultimap} contains arguments that reflect that the intent is to edit a module.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return True if {@code argMultimap} contains arguments that reflect that the intent is to edit a
         *         module. Otherwise, false.
         */
        public static boolean isArgumentsForEditingModule(ArgumentMultimap argMultimap) {
            requireNonNull(argMultimap);

            return !argMultimap.getPreamble().isEmpty()
                    && argMultimap.getValue(PREFIX_MODULE).isEmpty()
                    && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
        }

        /**
         * Parses the arguments in {@code argMultimap} and use it to create an {@code EditModuleCommand} object.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return The {@code EditModuleCommand} object created from the arguments.
         * @throws ParseException Indicates that an argument value did not conform to the expected format.
         */
        public static EditModuleCommand parse(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode moduleCode = extractModuleCode(argMultimap);
            EditModuleDescriptor descriptor = createDescriptor(argMultimap);

            validateDescriptorHasEditedField(descriptor);

            return new EditModuleCommand(moduleCode, descriptor);
        }

        private static ModuleCode extractModuleCode(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String moduleCodeStr = argMultimap.getPreamble();
            return ParserUtil.parseModuleCode(moduleCodeStr);
        }

        private static EditModuleDescriptor createDescriptor(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode updatedCode = extractUpdatedCode(argMultimap);
            ModuleName updatedName = extractUpdatedName(argMultimap);
            Set<Tag> updatedTags = EditCommandParserUtil.extractUpdatedTags(argMultimap);

            EditModuleDescriptor descriptor = new EditModuleDescriptor();
            descriptor.setCode(updatedCode);
            descriptor.setName(updatedName);
            descriptor.setTags(updatedTags);

            return descriptor;
        }

        private static ModuleCode extractUpdatedCode(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String updatedCodeStr = argMultimap.getValue(PREFIX_CODE).orElse(null);
            return updatedCodeStr == null ? null : ParserUtil.parseModuleCode(updatedCodeStr);
        }

        private static ModuleName extractUpdatedName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String updatedNameStr = argMultimap.getValue(PREFIX_NAME).orElse(null);
            return updatedNameStr == null ? null : ParserUtil.parseModuleName(updatedNameStr);
        }

        private static void validateDescriptorHasEditedField(EditModuleDescriptor descriptor) throws ParseException {
            requireNonNull(descriptor);

            if (!descriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
        }
    }

    /**
     * Contains utility methods for parsing input arguments and creating a new {@code EditLectureCommand}
     * object from it.
     */
    private static class EditLectureCommandParserUtil {
        /**
         * Returns true if {@code argMultimap} contains arguments that reflect that the intent is to edit a lecture.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return True if {@code argMultimap} contains arguments that reflect that the intent is to edit a
         *         lecture. Otherwise, false.
         */
        public static boolean isArgumentsForEditingLecture(ArgumentMultimap argMultimap) {
            requireNonNull(argMultimap);

            return !argMultimap.getPreamble().isEmpty()
                    && argMultimap.getValue(PREFIX_MODULE).isPresent()
                    && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
        }

        /**
         * Parses the arguments in {@code argMultimap} and use it to create an {@code EditLectureCommand} object.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return The {@code EditLectureCommand} object created from the arguments.
         * @throws ParseException Indicates that an argument value did not conform to the expected format.
         */
        public static EditLectureCommand parse(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode moduleCode = extractModuleCode(argMultimap);
            LectureName lectureName = extractLectureName(argMultimap);

            EditLectureDescriptor descriptor = createDescriptor(argMultimap);

            validateDescriptorHasEditedField(descriptor);

            return new EditLectureCommand(moduleCode, lectureName, descriptor);
        }

        private static ModuleCode extractModuleCode(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
            return ParserUtil.parseModuleCode(moduleCodeStr);
        }

        private static LectureName extractLectureName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String lectureNameStr = argMultimap.getPreamble();
            return ParserUtil.parseLectureName(lectureNameStr);
        }

        private static EditLectureDescriptor createDescriptor(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            LectureName updatedName = extractUpdatedName(argMultimap);
            Set<Tag> updatedTags = EditCommandParserUtil.extractUpdatedTags(argMultimap);

            EditLectureDescriptor descriptor = new EditLectureDescriptor();
            descriptor.setName(updatedName);
            descriptor.setTags(updatedTags);

            return descriptor;
        }

        private static LectureName extractUpdatedName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String updatedNameStr = argMultimap.getValue(PREFIX_NAME).orElse(null);
            return updatedNameStr == null ? null : ParserUtil.parseLectureName(updatedNameStr);
        }

        private static void validateDescriptorHasEditedField(EditLectureDescriptor descriptor) throws ParseException {
            requireNonNull(descriptor);

            if (!descriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
        }
    }

    /**
     * Contains utility methods for parsing input arguments and creating a new {@code EditVideoCommand}
     * object from it.
     */
    private static class EditVideoCommandParserUtil {
        /**
         * Returns true if {@code argMultimap} contains arguments that reflect that the intent is to edit a video.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return True if {@code argMultimap} contains arguments that reflect that the intent is to edit a
         *         video. Otherwise, false.
         */
        public static boolean isArgumentsForEditingVideo(ArgumentMultimap argMultimap) {
            requireNonNull(argMultimap);

            return !argMultimap.getPreamble().isEmpty()
                    && argMultimap.getValue(PREFIX_MODULE).isPresent()
                    && argMultimap.getValue(PREFIX_LECTURE).isPresent();
        }

        /**
         * Parses the arguments in {@code argMultimap} and use it to create an {@code EditVideoCommand} object.
         *
         * @param argMultimap A map of the arguments and their values.
         * @return The {@code EditVideoCommand} object created from the arguments.
         * @throws ParseException Indicates that an argument value did not conform to the expected format.
         */
        public static EditVideoCommand parse(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            ModuleCode moduleCode = extractModuleCode(argMultimap);
            LectureName lectureName = extractLectureName(argMultimap);
            VideoName videoName = extractVideoName(argMultimap);

            EditVideoDescriptor descriptor = createDescriptor(argMultimap);

            validateDescriptorHasEditedField(descriptor);

            return new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);
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

        private static VideoName extractVideoName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String videoNameStr = argMultimap.getPreamble();
            return ParserUtil.parseVideoName(videoNameStr);
        }

        private static EditVideoDescriptor createDescriptor(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            VideoName updatedName = extractUpdatedName(argMultimap);
            VideoTimestamp updatedTimestamp = extractUpdatedTimestamp(argMultimap);
            Set<Tag> updatedTags = EditCommandParserUtil.extractUpdatedTags(argMultimap);
            Boolean hasWatchedUpdated = hasWatchUpdated(argMultimap);

            EditVideoDescriptor descriptor = new EditVideoDescriptor();
            descriptor.setName(updatedName);
            descriptor.setTimestamp(updatedTimestamp);
            descriptor.setTags(updatedTags);
            descriptor.setWatched(hasWatchedUpdated);

            return descriptor;
        }

        private static VideoName extractUpdatedName(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String updatedNameStr = argMultimap.getValue(PREFIX_NAME).orElse(null);
            return updatedNameStr == null ? null : ParserUtil.parseVideoName(updatedNameStr);
        }

        private static VideoTimestamp extractUpdatedTimestamp(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            String updatedTimestampStr = argMultimap.getValue(PREFIX_TIMESTAMP).orElse(null);
            return updatedTimestampStr == null ? null : ParserUtil.parseVideoTimestamp(updatedTimestampStr);
        }

        // Would ideally be named "extractUpdatedWatchStatus" but that would violate the boolean naming convention
        private static Boolean hasWatchUpdated(ArgumentMultimap argMultimap) throws ParseException {
            requireNonNull(argMultimap);

            boolean hasWatchFlag = argMultimap.getValue(PREFIX_WATCH).isPresent();
            boolean hasUnwatchFlag = argMultimap.getValue(PREFIX_UNWATCH).isPresent();

            if (hasWatchFlag && hasUnwatchFlag) {
                throw new ParseException(String.format(MESSAGE_CONFLICTING_ARGS, PREFIX_WATCH, PREFIX_UNWATCH));
            }

            return (!hasWatchFlag && !hasUnwatchFlag) ? null : hasWatchFlag;
        }

        private static void validateDescriptorHasEditedField(EditVideoDescriptor descriptor) throws ParseException {
            requireNonNull(descriptor);

            if (!descriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
        }
    }
}
