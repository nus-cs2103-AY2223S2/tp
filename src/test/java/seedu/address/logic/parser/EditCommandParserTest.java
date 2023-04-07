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
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_VIDEO_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_VIDEO_TIMESTAMP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RANGE_VIDEO_TIMESTAMP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VIDEO_NAME;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MULTI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VIDEO_TIMESTAMP_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.VIDEO_TIMESTAMP_DESC_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNWATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditLectureCommand;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.logic.commands.edit.EditModuleCommand;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.edit.EditVideoCommand;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;
import seedu.address.testutil.LectureUtil;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.TagUtil;
import seedu.address.testutil.VideoUtil;

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
        EditModuleDescriptor descriptor = CommandTestUtil.getEditModuleDescriptorCs2040s();

        String noDuplicateUserInput = ModuleUtil.getEditModuleDetails(code, descriptor);

        EditModuleCommand expectedCommand = new EditModuleCommand(code, descriptor);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple code - last code accepted
        String toReplace = EDIT_MODULE_CODE_DESC_2040;
        userInput = noDuplicateUserInput.replace(toReplace, EDIT_MODULE_CODE_DESC_2103 + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple name - last name accepted
        toReplace = EDIT_MODULE_NAME_DESC_2040;
        userInput = noDuplicateUserInput.replace(toReplace, EDIT_MODULE_NAME_DESC_2103 + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(descriptor.getTags().get());
        tagsWithDuplicates.addAll(descriptor.getTags().get());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(descriptor.getTags().get());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedCode_success() {
        /* Setup */
        ModuleCode code = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = CommandTestUtil.getEditModuleDescriptorCs2040s();
        descriptor.setCode(null);

        String userInput = ModuleUtil.getEditModuleDetails(code, descriptor);

        /* Create expected result */
        EditModuleCommand expectedCommand = new EditModuleCommand(code, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedName_success() {
        /* Setup */
        ModuleCode code = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = CommandTestUtil.getEditModuleDescriptorCs2040s();
        descriptor.setName(null);

        String userInput = ModuleUtil.getEditModuleDetails(code, descriptor);

        /* Create expected result */
        EditModuleCommand command = new EditModuleCommand(code, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedTags_success() {
        /* Setup */
        ModuleCode code = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = CommandTestUtil.getEditModuleDescriptorCs2040s();
        descriptor.setTags(null);

        String userInput = ModuleUtil.getEditModuleDetails(code, descriptor);

        /* Create expected result */
        EditModuleCommand command = new EditModuleCommand(code, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editModuleFieldsMissingUpdatedFields_failure() {
        String userInput = VALID_MODULE_CODE_2103;
        assertParseFailure(parser, userInput, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_editModuleFieldsMissingModuleCode_failure() {
        String userInput = EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2040 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editModuleFieldsInvalidModuleCode_failure() {
        String userInput = INVALID_MODULE_CODE + EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2040
                + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editModuleFieldsInvalidUpdatedCode_failure() {
        String userInput = VALID_MODULE_CODE_2103 + INVALID_EDIT_MODULE_CODE_DESC + EDIT_MODULE_NAME_DESC_2040
                + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editModuleFieldsInvalidUpdatedName_failure() {
        String userInput = VALID_MODULE_CODE_2103 + EDIT_MODULE_CODE_DESC_2040 + INVALID_EDIT_MODULE_NAME_DESC
                + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editModuleFieldsInvalidUpdatedTags_failure() {
        String userInput = VALID_MODULE_CODE_2103 + EDIT_MODULE_CODE_DESC_2040 + EDIT_MODULE_NAME_DESC_2040
                + INVALID_TAG_DESC;
        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));
    }

    @Test
    public void parse_allEditLectureFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureDescriptor descriptor = CommandTestUtil.getEditLectureDescriptorL2();

        String noDuplicateUserInput = LectureUtil.getEditLectureDetails(moduleCode, lectureName, descriptor);

        EditLectureCommand expectedCommand = new EditLectureCommand(moduleCode, lectureName, descriptor);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple name - last name accepted
        String toReplace = EDIT_LECTURE_NAME_DESC_L2;
        userInput = noDuplicateUserInput.replace(toReplace, EDIT_LECTURE_NAME_DESC_L1 + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple module code - last module code accepted
        toReplace = MODULE_CODE_DESC_2103;
        userInput = noDuplicateUserInput.replace(toReplace, MODULE_CODE_DESC_2040 + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(descriptor.getTags().get());
        tagsWithDuplicates.addAll(descriptor.getTags().get());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(descriptor.getTags().get());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_editLectureFieldsMissingUpdatedName_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureDescriptor descriptor = CommandTestUtil.getEditLectureDescriptorL2();
        descriptor.setName(null);

        String userInput = LectureUtil.getEditLectureDetails(moduleCode, lectureName, descriptor);

        /* Create test result */
        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editLectureFieldsMissingUpdatedTags_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        EditLectureDescriptor descriptor = CommandTestUtil.getEditLectureDescriptorL2();
        descriptor.setTags(null);

        String userInput = LectureUtil.getEditLectureDetails(moduleCode, lectureName, descriptor);

        /* Create test result */
        EditLectureCommand command = new EditLectureCommand(moduleCode, lectureName, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editLectureFieldsMissingUpdatedFields_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103;
        assertParseFailure(parser, userInput, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_editLectureFieldsMissingLectureName_failure() {
        String userInput = MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editLectureFieldsInvalidModuleCode_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + INVALID_MODULE_CODE_DESC + EDIT_LECTURE_NAME_DESC_L2
                + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editLectureFieldsInvalidLectureName_failure() {
        String userInput = INVALID_LECTURE_NAME + MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L2
                + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editLectureFieldsInvalidUpdatedName_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103 + INVALID_EDIT_LECTURE_NAME_DESC
                + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editLectureFieldsInvalidUpdatedTags_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103 + EDIT_LECTURE_NAME_DESC_L2
                + INVALID_TAG_DESC;
        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));
    }

    @Test
    public void parse_allEditVideoFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);

        // ------------ watch flag is specified ------------
        EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV2();
        descriptor.setWatched(true);

        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);
        String noDuplicateUserInput = VideoUtil.getEditVideoDetails(moduleCode, lectureName, videoName, descriptor);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, command);

        // multiple name - last name accepted
        String toReplace = EDIT_VIDEO_NAME_DESC_V2;
        userInput = noDuplicateUserInput.replace(toReplace, EDIT_VIDEO_NAME_DESC_V1 + toReplace);
        assertParseSuccess(parser, userInput, command);

        // multiple module code - last module code accepted
        toReplace = MODULE_CODE_DESC_2103;
        userInput = noDuplicateUserInput.replace(toReplace, MODULE_CODE_DESC_2040 + toReplace);
        assertParseSuccess(parser, userInput, command);

        // multiple lecture name - last lecture name accepted
        toReplace = LECTURE_NAME_DESC_L1;
        userInput = noDuplicateUserInput.replace(toReplace, LECTURE_NAME_DESC_L2 + toReplace);
        assertParseSuccess(parser, userInput, command);

        // multiple timestamps - last timestamp accepted
        toReplace = VIDEO_TIMESTAMP_DESC_2;
        userInput = noDuplicateUserInput.replace(toReplace, VIDEO_TIMESTAMP_DESC_1 + toReplace);
        assertParseSuccess(parser, userInput, command);

        // multiple watch flag - same behaviour as when only one watch flag is given
        toReplace = PREFIX_WATCH.toString();
        userInput = noDuplicateUserInput.replace(toReplace, PREFIX_WATCH + " " + PREFIX_WATCH);
        assertParseSuccess(parser, userInput, command);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(descriptor.getTags().get());
        tagsWithDuplicates.addAll(descriptor.getTags().get());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(descriptor.getTags().get());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));

        assertParseSuccess(parser, userInput, command);

        // ------------ unwatch flag is specified ------------
        descriptor.setWatched(false);
        command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);
        noDuplicateUserInput = VideoUtil.getEditVideoDetails(moduleCode, lectureName, videoName, descriptor);

        // no duplicate fields
        userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, command);

        // multiple unwatch flag - same behaviour as when only one unwatch flag is given
        toReplace = PREFIX_UNWATCH.toString();
        userInput = noDuplicateUserInput.replace(toReplace, PREFIX_UNWATCH + " " + PREFIX_UNWATCH);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editVideoFieldsMissingUpdatedName_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV2();
        descriptor.setName(null);

        String userInput = VideoUtil.getEditVideoDetails(moduleCode, lectureName, videoName, descriptor);

        /* Create expected result */
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editVideoFieldsMissingUpdatedTags_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV2();
        descriptor.setTags(null);

        String userInput = VideoUtil.getEditVideoDetails(moduleCode, lectureName, videoName, descriptor);

        /* Create expected result */
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editVideoFieldsMissingUpdatedTimestamp_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV2();
        descriptor.setTimestamp(null);

        String userInput = VideoUtil.getEditVideoDetails(moduleCode, lectureName, videoName, descriptor);

        /* Create expected result */
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editVideoFieldsMissingUpdatedWatched_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV2();
        descriptor.setWatched(null);

        String userInput = VideoUtil.getEditVideoDetails(moduleCode, lectureName, videoName, descriptor);

        /* Create expected result */
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        /* Execute test */
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_editVideoFieldsMissingUpdatedFields_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1;
        assertParseFailure(parser, userInput, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_editVideoFieldsMissingVideoName_failure() {
        String userInput = MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2
                + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editVideoFieldsMissingModuleCode_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + LECTURE_NAME_DESC_L1 + EDIT_VIDEO_NAME_DESC_V2
                + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_editVideoFieldsInvalidVideoName_failure() {
        String userInput = INVALID_VIDEO_NAME + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2 + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidLectureName_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC
                + EDIT_VIDEO_NAME_DESC_V2 + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidModuleCode_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + INVALID_MODULE_CODE_DESC + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2 + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidUpdatedName_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + INVALID_EDIT_VIDEO_NAME_DESC + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidFormatForUpdatedTimestamp_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2 + INVALID_FORMAT_VIDEO_TIMESTAMP_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidRangeForUpdatedTimestamp_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2 + INVALID_RANGE_VIDEO_TIMESTAMP_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoTimestamp.MESSAGE_RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_editVideoFieldsInvalidUpdatedTags_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2 + VIDEO_TIMESTAMP_DESC_1 + INVALID_TAG_DESC;
        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));
    }

    @Test
    public void parse_editVideoFieldsWatchAndUnwatchFlagsSpecified_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + EDIT_VIDEO_NAME_DESC_V2 + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI
                + " " + PREFIX_WATCH + " " + PREFIX_UNWATCH;
        assertParseFailure(parser, userInput,
                String.format(Messages.MESSAGE_CONFLICTING_ARGS, PREFIX_WATCH, PREFIX_UNWATCH));
    }

}
