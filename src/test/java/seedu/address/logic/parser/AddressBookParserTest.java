package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2040S;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddLectureCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddVideoCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.video.Video;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.LectureUtil;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.VideoBuilder;
import seedu.address.testutil.VideoUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

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
    public void parseCommand_deleteModule() throws Exception {
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + CS2040S.getCode());
        assertEquals(new DeleteModuleCommand(CS2040S.getCode()), command);
    }

    // @Test
    // public void parseCommand_edit() throws Exception {
    //     Person person = new PersonBuilder().build();
    //     EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
    //     EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
    //             + INDEX_FIRST_MODULE.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
    //     assertEquals(new EditCommand(INDEX_FIRST_MODULE, descriptor), command);
    // }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
