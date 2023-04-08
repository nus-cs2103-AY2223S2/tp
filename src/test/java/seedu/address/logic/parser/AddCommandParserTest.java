package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_VIDEO_TIMESTAMP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RANGE_VIDEO_TIMESTAMP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VIDEO_NAME;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MULTI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VIDEO_TIMESTAMP_DESC_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddLectureCommand;
import seedu.address.logic.commands.add.AddModuleCommand;
import seedu.address.logic.commands.add.AddVideoCommand;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.LectureUtil;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.TagUtil;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;
import seedu.address.testutil.VideoBuilder;
import seedu.address.testutil.VideoUtil;

public class AddCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_noFields_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allAddModuleFields_success() {
        Module module = new ModuleBuilder(TypicalModules.getCs2040s()).withLectures().build();

        String noDuplicateUserInput = ModuleUtil.getModuleDetails(module);

        AddModuleCommand expectedCommand = new AddModuleCommand(module);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple names - last name accepted
        String toReplace = PREFIX_NAME + " " + module.getName().name;
        userInput = noDuplicateUserInput.replaceFirst(toReplace, MODULE_NAME_DESC_2103 + " " + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(module.getTags());
        tagsWithDuplicates.addAll(module.getTags());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(module.getTags());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addModuleFieldsMissingModuleNameField_success() {
        /* Setup */
        Module module = new ModuleBuilder(TypicalModules.getCs2040s()).withName("").withLectures().build();
        String userInput = ModuleUtil.getModuleDetails(module);

        /* Create expected results */
        AddModuleCommand expectedCommand = new AddModuleCommand(module);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addModuleFieldsMissingTagField_success() {
        /* Setup */
        Module module = new ModuleBuilder(TypicalModules.getCs2040s()).withTags().withLectures().build();
        String userInput = ModuleUtil.getModuleDetails(module);

        /* Create expected results */
        AddModuleCommand expectedCommand = new AddModuleCommand(module);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addModuleFieldsMissingModuleCodeField_failure() {
        String userInput = MODULE_NAME_DESC_2103 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addModuleFieldsInvalidModuleCodeValue_failure() {
        String userInput = INVALID_MODULE_CODE + MODULE_NAME_DESC_2103 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addModuleFieldsInvalidModuleNameValue_failure() {
        String userInput = VALID_MODULE_CODE_2103 + INVALID_MODULE_NAME_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addModuleFieldsInvalidTagsValue_failure() {
        String userInput = VALID_MODULE_CODE_2103 + MODULE_NAME_DESC_2103 + INVALID_TAG_DESC;
        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));
    }

    @Test
    public void parse_allAddLectureFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).withVideos().build();

        String noDuplicateUserInput = LectureUtil.getLectureDetails(moduleCode, lecture);

        AddLectureCommand expectedCommand = new AddLectureCommand(moduleCode, lecture);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple module codes - last module code accepted
        String toReplace = PREFIX_MODULE + " " + moduleCode.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, MODULE_CODE_DESC_2040 + " " + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(lecture.getTags());
        tagsWithDuplicates.addAll(lecture.getTags());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(lecture.getTags());
        userInput = noDuplicateUserInput.replaceFirst(toReplace,
                PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addLectureFieldsMissingTagField_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).withVideos().withTags().build();

        String userInput = LectureUtil.getLectureDetails(moduleCode, lecture);

        /* Create expected results */
        AddLectureCommand expectedCommand = new AddLectureCommand(moduleCode, lecture);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addLectureFieldsMissingLectureNameField_failure() {
        String userInput = MODULE_CODE_DESC_2040 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    /* Note */
    // There is no missing module code test for the "add a lecture" intent
    // This is because a missing module code would result in the command intent being interpreted as "add a module"

    @Test
    public void parse_addLectureFieldsInvalidModuleCodeValue_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + INVALID_MODULE_CODE_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addLectureFieldsInvalidLectureNameValue_failure() {
        String userInput = INVALID_LECTURE_NAME + MODULE_CODE_DESC_2103 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addLectureFieldsInvalidTagsValue_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + MODULE_CODE_DESC_2103 + INVALID_TAG_DESC;
        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));
    }

    @Test
    public void parse_allAddVideoFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        Video video = TypicalVideos.ANALYSIS_VIDEO;

        String noDuplicateUserInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video);

        AddVideoCommand expectedCommand = new AddVideoCommand(moduleCode, lectureName, video);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple module codes - last module code accepted
        String toReplace = PREFIX_MODULE + " " + moduleCode.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, MODULE_CODE_DESC_2040 + " " + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple lecture names - last lecture name accepted
        toReplace = PREFIX_LECTURE + " " + lectureName.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, LECTURE_NAME_DESC_L2 + " " + toReplace);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple watch flags - same behaviour as when only one watch flag is provided
        toReplace = PREFIX_WATCH.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, PREFIX_WATCH + " " + PREFIX_WATCH);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple timestamps - last timestamp accepted
        toReplace = PREFIX_TIMESTAMP + " " + video.getTimestamp().toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, VIDEO_TIMESTAMP_DESC_1 + " " + toReplace);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(video.getTags());
        tagsWithDuplicates.addAll(video.getTags());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(video.getTags());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addVideoFieldsMissingTimestampField_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        Video video = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO)
                .withTimestamp(VideoTimestamp.DEFAULT_TIMESTAMP).build();

        String userInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video)
                .replace(PREFIX_TIMESTAMP + " " + VideoTimestamp.DEFAULT_TIMESTAMP, "");

        /* Create expected results */
        AddVideoCommand expectedCommand = new AddVideoCommand(moduleCode, lectureName, video);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addVideoFieldsMissingTagField_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        Video video = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).withTags().build();

        String userInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video);

        /* Create expected results */
        AddVideoCommand expectedCommand = new AddVideoCommand(moduleCode, lectureName, video);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addVideoFieldsNoWatchFlag_success() {
        /* Setup */
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        Video video = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).withWatched(false).build();

        String userInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video);

        /* Create expected results */
        AddVideoCommand expectedCommand = new AddVideoCommand(moduleCode, lectureName, video);

        /* Execute test */
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addVideoFieldsMissingVideoNameField_failure() {
        String userInput = MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1 + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addVideoFieldsMissingModuleCodeField_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + LECTURE_NAME_DESC_L1 + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    /* Note */
    // There is no missing lecture name test for the "add a video" intent
    // This is because a missing lecture name would result in the command intent being interpreted as "add a lecture"

    @Test
    public void parse_addVideoFieldsInvalidModuleCodeValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + INVALID_MODULE_CODE_DESC + LECTURE_NAME_DESC_L1
                + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidLectureNameValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC
                + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidVideoNameValue_failure() {
        String userInput = INVALID_VIDEO_NAME + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + VIDEO_TIMESTAMP_DESC_1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidFormatForVideoTimestampValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + INVALID_FORMAT_VIDEO_TIMESTAMP_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidRangeForVideoTimestampValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + INVALID_RANGE_VIDEO_TIMESTAMP_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoTimestamp.MESSAGE_RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidTagsValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1
                + VIDEO_TIMESTAMP_DESC_1 + INVALID_TAG_DESC;
        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));
    }
}
