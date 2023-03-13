package arb.logic.parser;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import arb.logic.commands.ExitCommand;
import arb.logic.commands.HelpCommand;
import arb.logic.commands.client.AddClientCommand;
import arb.logic.commands.client.ClearClientCommand;
import arb.logic.commands.client.DeleteClientCommand;
import arb.logic.commands.client.EditClientCommand;
import arb.logic.commands.client.EditClientCommand.EditClientDescriptor;
import arb.logic.commands.client.FindClientCommand;
import arb.logic.commands.client.ListClientCommand;
import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.logic.commands.project.ListProjectCommand;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Client;
import arb.model.client.NameContainsKeywordsPredicate;
import arb.model.project.Project;
import arb.testutil.ClientBuilder;
import arb.testutil.ClientUtil;
import arb.testutil.EditClientDescriptorBuilder;
import arb.testutil.EditProjectDescriptorBuilder;
import arb.testutil.ProjectBuilder;
import arb.testutil.ProjectUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new ClientBuilder().build();
        AddClientCommand addClientCommand = (AddClientCommand) parser
                .parseCommand(ClientUtil.getAddClientCommand(client));
        assertEquals(new AddClientCommand(client), addClientCommand);
        Project project = new ProjectBuilder().build();
        AddProjectCommand addProjectCommand = (AddProjectCommand) parser
                .parseCommand(ProjectUtil.getAddProjectCommand(project));
        assertEquals(new AddProjectCommand(project), addProjectCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearClientCommand.COMMAND_WORD) instanceof ClearClientCommand);
        assertTrue(parser.parseCommand(ClearClientCommand.COMMAND_WORD + " 3") instanceof ClearClientCommand);
        //assertTrue(parser.parseCommand(ClearProjectCommand.COMMAND_WORD) instanceof ClearProjectCommand);
        //assertTrue(parser.parseCommand(ClearProjectCommand.COMMAND_WORD + " 3") instanceof ClearProjectCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteClientCommand deleteClientCommand = (DeleteClientCommand) parser.parseCommand(
                DeleteClientCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteClientCommand(INDEX_FIRST), deleteClientCommand);
        //DeleteProjectCommand deleteProjectCommand = (DeleteProjectCommand) parser.parseCommand(
        //        DeleteProjecttCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        //assertEquals(new DeleteProjectCommand(INDEX_FIRST), deleteProjectCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new ClientBuilder().build();
        EditClientDescriptor editClientDescriptor = new EditClientDescriptorBuilder(client).build();
        EditClientCommand editClientCommand = (EditClientCommand) parser.parseCommand(EditClientCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " "
                + ClientUtil.getEditClientDescriptorDetails(editClientDescriptor));
        assertEquals(new EditClientCommand(INDEX_FIRST, editClientDescriptor), editClientCommand);
        Project project = new ProjectBuilder().build();
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptorBuilder(project).build();
        EditProjectCommand editProjectCommand = (EditProjectCommand) parser
                .parseCommand(EditProjectCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + ProjectUtil.getEditProjectDescriptorDetails(editProjectDescriptor));
        assertEquals(new EditProjectCommand(INDEX_FIRST, editProjectDescriptor), editProjectCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindClientCommand command = (FindClientCommand) parser.parseCommand(
                FindClientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindClientCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD) instanceof ListClientCommand);
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD + " 3") instanceof ListClientCommand);
        assertTrue(parser.parseCommand(ListProjectCommand.COMMAND_WORD) instanceof ListProjectCommand);
        assertTrue(parser.parseCommand(ListProjectCommand.COMMAND_WORD + " 3") instanceof ListProjectCommand);
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
