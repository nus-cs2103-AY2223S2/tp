package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
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
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
        AddModuleCommand command = new AddModuleCommand(module);

        String noDuplicateUserInput = ModuleUtil.getModuleDetails(module);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, command);

        // multiple names - last name accepted
        String toReplace = PREFIX_NAME + " " + module.getName().name;
        userInput = noDuplicateUserInput.replaceFirst(toReplace, MODULE_NAME_DESC_2103 + " " + toReplace);
        assertParseSuccess(parser, userInput, command);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(module.getTags());
        tagsWithDuplicates.addAll(module.getTags());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(module.getTags());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_addModuleFieldsMissingModuleNameField_success() {
        Module module = new ModuleBuilder(TypicalModules.getCs2040s()).withName("").withLectures().build();

        String userInput = ModuleUtil.getModuleDetails(module);
        assertParseSuccess(parser, userInput, new AddModuleCommand(module));
    }

    @Test
    public void parse_addModuleFieldsMissingTagField_success() {
        Module module = new ModuleBuilder(TypicalModules.getCs2040s()).withTags().withLectures().build();

        String userInput = ModuleUtil.getModuleDetails(module);
        assertParseSuccess(parser, userInput, new AddModuleCommand(module));
    }

    @Test
    public void parse_addModuleFieldsMissingModuleCodeField_failure() {
        String userInput = MODULE_NAME_DESC_2103 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addModuleFieldInvalidModuleCodeValue_failure() {
        String userInput = INVALID_MODULE_CODE + MODULE_NAME_DESC_2103 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addModuleFieldInvalidModuleNameValue_failure() {
        String userInput = VALID_MODULE_CODE_2103 + INVALID_MODULE_NAME_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allAddLectureFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).withVideos().build();

        AddLectureCommand command = new AddLectureCommand(moduleCode, lecture);
        String noDuplicateUserInput = LectureUtil.getLectureDetails(moduleCode, lecture);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, command);

        // multiple module codes - last module code accepted
        String toReplace = PREFIX_MODULE + " " + moduleCode.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, MODULE_CODE_DESC_2040 + " " + toReplace);
        assertParseSuccess(parser, userInput, command);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(lecture.getTags());
        tagsWithDuplicates.addAll(lecture.getTags());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(lecture.getTags());
        userInput = noDuplicateUserInput.replaceFirst(toReplace,
                PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_addLectureFieldsMissingTagField_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).withVideos().withTags().build();

        AddLectureCommand command = new AddLectureCommand(moduleCode, lecture);
        String userInput = LectureUtil.getLectureDetails(moduleCode, lecture);

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_addLectureFieldsMissingLectureNameField_failure() {
        String userInput = MODULE_CODE_DESC_2040 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addLectureFieldsInvalidModuleCodeValue_failure() {
        String userInput = VALID_LECTURE_NAME_L1 + INVALID_MODULE_CODE_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addLectureFieldsInvalidLectureNameValue_failure() {
        assertParseFailure(parser, INVALID_LECTURE_NAME + MODULE_CODE_DESC_2103, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allAddVideoFields_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);

        Video video = TypicalVideos.ANALYSIS_VIDEO;

        AddVideoCommand command = new AddVideoCommand(moduleCode, lectureName, video);
        String noDuplicateUserInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video);

        // no duplicate fields
        String userInput = noDuplicateUserInput;
        assertParseSuccess(parser, userInput, command);

        // multiple module codes - last module code accepted
        String toReplace = PREFIX_MODULE + " " + moduleCode.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, MODULE_CODE_DESC_2040 + " " + toReplace);
        assertParseSuccess(parser, userInput, command);

        // multiple lecture names - last lecture name accepted
        toReplace = PREFIX_LECTURE + " " + lectureName.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, LECTURE_NAME_DESC_L2 + " " + toReplace);
        assertParseSuccess(parser, userInput, command);

        // multiple watch flags - duplicates ignored
        toReplace = PREFIX_WATCH.toString();
        userInput = noDuplicateUserInput.replaceFirst(toReplace, PREFIX_WATCH + " " + PREFIX_WATCH);
        assertParseSuccess(parser, userInput, command);

        // duplicate tags - duplicates ignored
        List<Tag> tagsWithDuplicates = new ArrayList<>();
        tagsWithDuplicates.addAll(video.getTags());
        tagsWithDuplicates.addAll(video.getTags());

        toReplace = PREFIX_TAG + " " + TagUtil.getTagsStr(video.getTags());
        userInput = noDuplicateUserInput.replaceFirst(
                toReplace, PREFIX_TAG + " " + TagUtil.getTagsStr(tagsWithDuplicates));
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_addVideoFieldsMissingTagField_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);

        Video video = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).withTags().build();

        AddVideoCommand command = new AddVideoCommand(moduleCode, lectureName, video);
        String userInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video);

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_addVideoFieldsNoWatchFlag_success() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);

        Video video = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).withHasWatched(false).build();

        AddVideoCommand command = new AddVideoCommand(moduleCode, lectureName, video);
        String userInput = VideoUtil.getVideoDetails(moduleCode, lectureName, video);

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_addVideoFieldsMissingVideoNameField_failure() {
        String userInput = MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addVideoFieldsMissingModuleCodeField_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + LECTURE_NAME_DESC_L1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addVideoFieldsInvalidModuleCodeValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + INVALID_MODULE_CODE_DESC + LECTURE_NAME_DESC_L1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidLectureNameValue_failure() {
        String userInput = VALID_VIDEO_NAME_V1 + MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addVideoFieldsInvalidVideoNameValue_failure() {
        String userInput = INVALID_VIDEO_NAME + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1 + TAG_DESC_MULTI;
        assertParseFailure(parser, userInput, VideoName.MESSAGE_CONSTRAINTS);
    }
}
