package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_KNOWN_COMMANDS;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V2;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddLectureCommand;
import seedu.address.logic.commands.add.AddModuleCommand;
import seedu.address.logic.commands.add.AddVideoCommand;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditLectureCommand;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.logic.commands.edit.EditModuleCommand;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.edit.EditVideoCommand;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.logic.commands.mark.MarkAsUnwatchedCommand;
import seedu.address.logic.commands.mark.MarkAsWatchedCommand;
import seedu.address.logic.commands.mark.MarkMultipleAsUnwatchedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.EditLectureDescriptorBuilder;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditVideoDescriptorBuilder;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.LectureUtil;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;
import seedu.address.testutil.VideoBuilder;
import seedu.address.testutil.VideoUtil;

public class TrackerParserTest {

    private final TrackerParser parser = new TrackerParser();

    @Test
    public void parseCommand_addModule() throws Exception {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2040)
                .withName(VALID_MODULE_NAME_2040).build();
        AddCommand command = (AddCommand) parser.parseCommand(ModuleUtil.getAddCommand(module));
        assertEquals(new AddModuleCommand(module), command);
    }

    @Test
    public void parseCommand_addLecture() throws Exception {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2040);
        Lecture lecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();

        AddCommand command = (AddCommand) parser.parseCommand(LectureUtil.getAddCommand(moduleCode, lecture));

        assertEquals(new AddLectureCommand(moduleCode, lecture), command);
    }

    @Test
    public void parseCommand_addVideo() throws Exception {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2040);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        Video video = new VideoBuilder().withName(VALID_VIDEO_NAME_V1).build();

        AddCommand command = (AddCommand) parser.parseCommand(VideoUtil.getAddCommand(moduleCode, lectureName, video));

        assertEquals(new AddVideoCommand(moduleCode, lectureName, video), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {

        // delete module
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " " + VALID_MODULE_CODE_2040)
                instanceof DeleteCommand);

        // delete multiple modules
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                        + " " + VALID_MODULE_CODE_2040
                        + ", " + VALID_MODULE_CODE_2103
                ) instanceof DeleteCommand);

        // delete lecture
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                        + " " + VALID_LECTURE_NAME_L1
                        + MODULE_CODE_DESC_2040
                ) instanceof DeleteCommand);

        // delete multiple lectures
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                        + " " + VALID_LECTURE_NAME_L1
                        + ", " + VALID_LECTURE_NAME_L2
                        + MODULE_CODE_DESC_2040
                ) instanceof DeleteCommand);

        // delete video
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                        + " " + VALID_VIDEO_NAME_V1
                        + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1
                ) instanceof DeleteCommand);

        // delete multiple videos
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                        + " " + VALID_VIDEO_NAME_V1
                        + ", " + VALID_VIDEO_NAME_V2
                        + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1
                ) instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_editModule() throws Exception {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        Module module = new ModuleBuilder(TypicalModules.getCs2040s()).build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();

        EditCommand command = (EditCommand) parser.parseCommand(ModuleUtil.getEditCommand(moduleCode, descriptor));
        assertEquals(new EditModuleCommand(moduleCode, descriptor), command);
    }

    @Test
    public void parseCommand_editLecture() throws Exception {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);

        Lecture lecture = new LectureBuilder(TypicalLectures.getCs2040sWeek1()).build();
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder(lecture).build();

        EditCommand command = (EditCommand) parser.parseCommand(
                LectureUtil.getEditCommand(moduleCode, lectureName, descriptor));
        assertEquals(new EditLectureCommand(moduleCode, lectureName, descriptor), command);
    }

    @Test
    public void parseCommand_editVideo() throws Exception {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);

        Video video = new VideoBuilder(TypicalVideos.CONTENT_VIDEO).build();
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder(video).build();

        EditCommand command = (EditCommand) parser.parseCommand(
                VideoUtil.getEditCommand(moduleCode, lectureName, videoName, descriptor));
        assertEquals(new EditVideoCommand(moduleCode, lectureName, videoName, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(",")));
        assertEquals(new FindCommand(keywords, false), command);
    }

    @Test
    public void parseCommand_markAsWatched() throws Exception {
        assertTrue(parser.parseCommand(MarkAsWatchedCommand.COMMAND_WORD
                        + " " + VALID_VIDEO_NAME_V1
                        + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1)
                instanceof MarkAsWatchedCommand);

    }

    @Test
    public void parseCommand_markAsUnwatched() throws Exception {
        assertTrue(parser.parseCommand(MarkAsUnwatchedCommand.COMMAND_WORD
                        + " " + VALID_VIDEO_NAME_V1
                        + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1)
                instanceof MarkAsUnwatchedCommand);

        assertTrue(parser.parseCommand(MarkAsUnwatchedCommand.COMMAND_WORD
                        + " " + VALID_VIDEO_NAME_V1
                        + ", " + VALID_VIDEO_NAME_V2
                        + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1)
                instanceof MarkMultipleAsUnwatchedCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND + MESSAGE_KNOWN_COMMANDS, () -> parser.parseCommand("unknownCommand"));
    }
}
