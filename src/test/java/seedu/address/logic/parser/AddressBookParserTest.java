package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateSessionCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteSessionCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.StudentAddCommand;
import seedu.address.logic.commands.StudentRemoveCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsGroupsPredicate;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.SessionBuilder;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_createSession() throws Exception {
        Session session = SessionBuilder.generateDefaultSession();
        String createSessionCommand = "create-session "
                + "n/Hall "
                + "s/10-03-2022 10:00 to 10-03-2022 11:00 "
                + "l/Leon Lim Sports Hall Of Champions";
        CreateSessionCommand command = (CreateSessionCommand) parser.parseCommand(createSessionCommand);
        assertEquals(new CreateSessionCommand(session), command);
    }

    @Test
    public void parseCommand_deleteSession() throws Exception {
        String deleteSessionCommand = "delete-session 1";
        DeleteSessionCommand command = (DeleteSessionCommand) parser.parseCommand(deleteSessionCommand);
        assertEquals(new DeleteSessionCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_studentAdd() throws Exception {
        SessionName name = new SessionName("Hall");
        String studentAddCommand = "student-add 1 n/hall";
        StudentAddCommand command = (StudentAddCommand) parser.parseCommand(studentAddCommand);
        assertEquals(new StudentAddCommand(INDEX_FIRST_PERSON, name),
                command);
    }

    @Test
    public void parseCommand_studentRemove() throws Exception {
        SessionName name = new SessionName("Hall");
        String studentRemoveCommand = "student-remove 1 n/hall";
        StudentRemoveCommand command = (StudentRemoveCommand) parser.parseCommand(studentRemoveCommand);
        assertEquals(new StudentRemoveCommand(INDEX_FIRST_PERSON, name),
                command);
    }

    @Test
    public void parseCommand_mark_unmark() throws Exception {
        Name name = new Name("Bob");
        String markAttendanceCommand = "mark 1 n/bob";
        String unmarkAttendanceCommand = "unmark 1 n/bob";
        MarkAttendanceCommand markCommand = (MarkAttendanceCommand) parser.parseCommand(markAttendanceCommand);
        UnmarkAttendanceCommand unmarkCommand = (UnmarkAttendanceCommand) parser.parseCommand(unmarkAttendanceCommand);
        assertEquals(new UnmarkAttendanceCommand(INDEX_FIRST_PERSON, name),
                unmarkCommand);
        assertEquals(new MarkAttendanceCommand(INDEX_FIRST_PERSON, name),
                markCommand);
    }


    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find_show() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);

        ShowCommand showCommand = (ShowCommand) parser.parseCommand(
                ShowCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new ShowCommand(new TagContainsGroupsPredicate(keywords)), showCommand);
    }

    @Test
    public void parseCommand_show() throws Exception {

    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 1") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 3") instanceof SortCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
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
