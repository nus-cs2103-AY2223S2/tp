package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VIDEO_NAME;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddLectureCommand;
import seedu.address.logic.commands.add.AddModuleCommand;
import seedu.address.logic.commands.add.AddVideoCommand;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.VideoBuilder;

public class AddCommandParserTest {
    private static final String MESSAGE_COMMAND_FORMAT_ERROR =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_noFields_failure() {
        assertParseFailure(parser, "", MESSAGE_COMMAND_FORMAT_ERROR);
    }

    @Test
    public void parse_allAddModuleFields_success() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103)
                .withName(VALID_MODULE_NAME_2103).withTags().withLectures().build();
        AddModuleCommand command = new AddModuleCommand(module);

        // no duplicate fields
        assertParseSuccess(parser, VALID_MODULE_CODE_2103 + MODULE_NAME_DESC_2103, command);

        // multiple names - last name accepted
        assertParseSuccess(parser, VALID_MODULE_CODE_2103 + MODULE_NAME_DESC_2040 + MODULE_NAME_DESC_2103, command);
    }

    @Test
    public void parse_addModuleFieldsMissingModuleNameField_success() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103)
                .withName("").withTags().withLectures().build();

        assertParseSuccess(parser, VALID_MODULE_CODE_2103, new AddModuleCommand(module));
    }

    @Test
    public void parse_addModuleFieldsMissingModuleCodeField_failure() {
        assertParseFailure(parser, MODULE_NAME_DESC_2103, MESSAGE_COMMAND_FORMAT_ERROR);
    }

    @Test
    public void parse_addModuleFieldInvalidModuleCodeValue_failure() {
        assertParseFailure(parser, INVALID_MODULE_CODE, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allAddLectureFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        Lecture lecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).withVideos().withTags().build();

        AddLectureCommand command = new AddLectureCommand(moduleCode, lecture);

        // no duplicate fields
        assertParseSuccess(parser, VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103, command);

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2040 + MODULE_CODE_DESC_2103, command);
    }

    @Test
    public void parse_addLectureFieldsMissingLectureNameField_failure() {
        assertParseFailure(parser, MODULE_CODE_DESC_2040, MESSAGE_COMMAND_FORMAT_ERROR);
    }

    @Test
    public void parse_addLectureFieldsInvalidModuleCodeValue_failure() {
        assertParseFailure(parser, VALID_LECTURE_NAME_L1 + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addLectureFieldsInvalidLectureNameValue_failure() {
        assertParseFailure(parser, INVALID_LECTURE_NAME + MODULE_CODE_DESC_2103, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allAddVideoFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);

        Video video = new VideoBuilder().withName(VALID_VIDEO_NAME_V1).withTags().build();

        AddVideoCommand command = new AddVideoCommand(moduleCode, lectureName, video);

        // no duplicate fields, module code before lecture name
        assertParseSuccess(parser, VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1, command);

        // no duplicate fields, lecture name before module code
        assertParseSuccess(parser, VALID_VIDEO_NAME_V1 + LECTURE_NAME_DESC_L1 + MODULE_CODE_DESC_2103, command);

        // multiple module codes - last module code accepted
        assertParseSuccess(parser,
                VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2040 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1, command);

        // multiple lecture names - last lecture name accepted
        assertParseSuccess(parser,
                VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L2 + LECTURE_NAME_DESC_L1, command);
    }

    @Test
    public void parse_addVideoFieldsMissingVideoNameField_failure() {
        assertParseFailure(parser, MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1, MESSAGE_COMMAND_FORMAT_ERROR);
    }

    @Test
    public void parse_addVideoFieldsMissingModuleCodeField_failure() {
        assertParseFailure(parser, VALID_VIDEO_NAME_V1 + LECTURE_NAME_DESC_L1, MESSAGE_COMMAND_FORMAT_ERROR);
    }

    @Test
    public void parse_addVideoFieldsInvalidModuleCodeValue_failure() {
        assertParseFailure(parser, VALID_VIDEO_NAME_V1 + INVALID_MODULE_CODE_DESC + LECTURE_NAME_DESC_L1,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidLectureNameValue_failure() {
        assertParseFailure(parser, VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC,
                LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidVideoNameValue_failure() {
        assertParseFailure(parser, INVALID_VIDEO_NAME + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1,
                VideoName.MESSAGE_CONSTRAINTS);
    }
}
