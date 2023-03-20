package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_LECTURE_NAME_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_NAME_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_NAME_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_VIDEO_NAME_DESC_V1;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_VIDEO_NAME_DESC_V2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_VIDEO_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VIDEO_NAME;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditLectureCommand;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.logic.commands.edit.EditModuleCommand;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.edit.EditVideoCommand;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.EditLectureDescriptorBuilder;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditVideoDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_noFields_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allEditModuleFields_success() {
        ModuleCode code = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_MODULE_CODE_2040)
                .withName(VALID_MODULE_NAME_2040).build();

        EditModuleCommand command = new EditModuleCommand(code, descriptor);

        // no duplicate fields
        String input = VALID_MODULE_CODE_2103 + EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2040;
        assertParseSuccess(parser, input, command);

        // multiple code - last code accepted
        input = VALID_MODULE_CODE_2103 + EDIT_MODULE_CODE_DESC_2103 + EDIT_MODULE_CODE_DESC_2040
                + EDIT_MODULE_NAME_DESC_2040;
        assertParseSuccess(parser, input, command);

        // multiple name - last name accepted
        input = VALID_MODULE_CODE_2103 + EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2103
                + EDIT_MODULE_NAME_DESC_2040;
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedCode_success() {
        ModuleCode code = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULE_NAME_2040).build();

        EditModuleCommand command = new EditModuleCommand(code, descriptor);

        String input = VALID_MODULE_CODE_2103 + EDIT_MODULE_NAME_DESC_2040;
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedName_success() {
        ModuleCode code = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_MODULE_CODE_2040).build();

        EditModuleCommand command = new EditModuleCommand(code, descriptor);

        String input = VALID_MODULE_CODE_2103 + EDIT_MODULE_CODE_DESC_2040;
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedFields_failure() {
        String input = VALID_MODULE_CODE_2103;
        assertParseFailure(parser, input, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_editModuleFieldsMissingModuleCode_failure() {
        String input = EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2040;
        assertParseFailure(parser, input, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editModuleFieldsInvalidModuleCode_failure() {
        String input = INVALID_MODULE_CODE + EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2040;
        assertParseFailure(parser, input, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editModuleFieldsInvalidUpdatedCode_failure() {
        String input = VALID_MODULE_CODE_2103 + INVALID_EDIT_MODULE_CODE_DESC + EDIT_MODULE_NAME_DESC_2040;
        assertParseFailure(parser, input, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allEditLectureFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder().withName(VALID_LECTURE_NAME_L2).build();

        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);

        // no duplicate fields
        String input = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L2;
        assertParseSuccess(parser, input, command);

        // multiple name - last name accepted
        input = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L1 + EDIT_LECTURE_NAME_DESC_L2;
        assertParseSuccess(parser, input, command);

        // multiple module code - last module code accepted
        input = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2040 + MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L2;
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_editLectureFieldsMissingUpdatedFields_failure() {
        String input = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103;
        assertParseFailure(parser, input, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_editLectureFieldsMissingLectureName_failure() {
        String input = MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L1;
        assertParseFailure(parser, input, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editLectureFieldsInvalidModuleCode_failure() {
        String input = VALID_LECTURE_NAME_L1 + INVALID_MODULE_CODE_DESC + EDIT_LECTURE_NAME_DESC_L2;
        assertParseFailure(parser, input, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editLectureFieldsInvalidLectureName_failure() {
        String input = INVALID_LECTURE_NAME + MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L2;
        assertParseFailure(parser, input, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editLectureFieldsInvalidUpdatedName_failure() {
        String input = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103 + INVALID_EDIT_LECTURE_NAME_DESC;
        assertParseFailure(parser, input, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allEditVideoFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder().withName(VALID_VIDEO_NAME_V2).build();

        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        // no duplicate fields
        String input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2;
        assertParseSuccess(parser, input, command);

        // multiple name - last name accepted
        input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V1
                + EDIT_VIDEO_NAME_DESC_V2;
        assertParseSuccess(parser, input, command);

        // multiple module code - last module code accepted
        input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2040 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2;
        assertParseSuccess(parser, input, command);

        // multiple lecture name - last lecture name accepted
        input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L2 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2;
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_editVideoFieldsMissingUpdatedFields_failure() {
        String input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1;
        assertParseFailure(parser, input, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_editVideoFieldsMissingVideoName_failure() {
        String input = MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2;
        assertParseFailure(parser, input, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editVideoFieldsMissingModuleCode_failure() {
        String input = VALID_VIDEO_NAME_V1 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2;
        assertParseFailure(parser, input, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editVideoFieldsInvalidVideoName_failure() {
        String input = INVALID_VIDEO_NAME + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2;
        assertParseFailure(parser, input, VideoName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidLectureName_failure() {
        String input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC
                + EDIT_VIDEO_NAME_DESC_V2;
        assertParseFailure(parser, input, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidModuleCode_failure() {
        String input = VALID_VIDEO_NAME_V1 + INVALID_MODULE_CODE_DESC + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2;
        assertParseFailure(parser, input, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidUpdatedName_failure() {
        String input = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + INVALID_EDIT_VIDEO_NAME_DESC;
        assertParseFailure(parser, input, VideoName.MESSAGE_CONSTRAINTS);
    }

}
