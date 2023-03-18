package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditLectureCommand;
import seedu.address.logic.commands.edit.EditModuleCommand;
import seedu.address.logic.commands.edit.EditVideoCommand;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.video.VideoName;

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
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_NAME, PREFIX_MODULE, PREFIX_LECTURE);

        if (isEditModule(argMultimap)) {
            return parseEditModuleCommand(argMultimap);
        } else if (isEditLecture(argMultimap)) {
            return parseEditLectureCommand(argMultimap);
        } else if (isEditVideo(argMultimap)) {
            return parseEditVideoCommand(argMultimap);
        } else {
            throw createInvalidCommandFormatException();
        }
    }

    private boolean isEditModule(ArgumentMultimap argMultimap) {
        requireNonNull(argMultimap);

        return !argMultimap.getPreamble().isEmpty()
                && argMultimap.getValue(PREFIX_MODULE).isEmpty()
                && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
    }

    private EditCommand parseEditModuleCommand(ArgumentMultimap argMultimap) throws ParseException {
        requireNonNull(argMultimap);

        String moduleCodeStr = argMultimap.getPreamble();

        String updatedCodeStr = argMultimap.getValue(PREFIX_CODE).orElse(null);
        String updatedNameStr = argMultimap.getValue(PREFIX_NAME).orElse(null);

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);

        ModuleCode updatedCode = updatedCodeStr == null ? null : ParserUtil.parseModuleCode(updatedCodeStr);
        ModuleName updatedName = updatedNameStr == null ? null : ParserUtil.parseModuleName(updatedNameStr);

        EditModuleDescriptor descriptor = new EditModuleDescriptor();
        descriptor.setCode(updatedCode);
        descriptor.setName(updatedName);

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditModuleCommand(moduleCode, descriptor);
    }

    private boolean isEditLecture(ArgumentMultimap argMultimap) {
        requireNonNull(argMultimap);

        return !argMultimap.getPreamble().isEmpty()
                && argMultimap.getValue(PREFIX_MODULE).isPresent()
                && argMultimap.getValue(PREFIX_LECTURE).isEmpty();
    }

    private EditCommand parseEditLectureCommand(ArgumentMultimap argMultimap) throws ParseException {
        requireNonNull(argMultimap);

        String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
        String lectureNameStr = argMultimap.getPreamble();

        String updatedNameStr = argMultimap.getValue(PREFIX_NAME).orElse(null);

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);

        LectureName updatedName = updatedNameStr == null ? null : ParserUtil.parseLectureName(updatedNameStr);

        EditLectureDescriptor descriptor = new EditLectureDescriptor();
        descriptor.setName(updatedName);

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditLectureCommand(moduleCode, lectureName, descriptor);
    }

    private boolean isEditVideo(ArgumentMultimap argMultimap) {
        requireNonNull(argMultimap);

        return !argMultimap.getPreamble().isEmpty()
                && argMultimap.getValue(PREFIX_MODULE).isPresent()
                && argMultimap.getValue(PREFIX_LECTURE).isPresent();
    }

    private EditCommand parseEditVideoCommand(ArgumentMultimap argMultimap) throws ParseException {
        requireNonNull(argMultimap);

        String moduleCodeStr = argMultimap.getValue(PREFIX_MODULE).get();
        String lectureNameStr = argMultimap.getValue(PREFIX_LECTURE).get();
        String videoNameStr = argMultimap.getPreamble();

        String updatedNameStr = argMultimap.getValue(PREFIX_NAME).orElse(null);

        ModuleCode moduleCode = ParserUtil.parseModuleCode(moduleCodeStr);
        LectureName lectureName = ParserUtil.parseLectureName(lectureNameStr);
        VideoName videoName = ParserUtil.parseVideoName(videoNameStr);
        VideoName updatedName = updatedNameStr == null ? null : ParserUtil.parseVideoName(updatedNameStr);

        EditVideoDescriptor descriptor = new EditVideoDescriptor();
        descriptor.setName(updatedName);

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);
    }

    private ParseException createInvalidCommandFormatException() {
        return new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

}
