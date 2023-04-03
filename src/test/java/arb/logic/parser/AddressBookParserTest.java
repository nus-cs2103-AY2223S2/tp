package arb.logic.parser;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.CommandTestUtil;
import arb.logic.commands.ExitCommand;
import arb.logic.commands.HelpCommand;
import arb.logic.commands.client.AddClientCommand;
import arb.logic.commands.client.ClearClientCommand;
import arb.logic.commands.client.DeleteClientCommand;
import arb.logic.commands.client.EditClientCommand;
import arb.logic.commands.client.EditClientCommand.EditClientDescriptor;
import arb.logic.commands.client.FindClientCommand;
import arb.logic.commands.client.ListClientCommand;
import arb.logic.commands.client.SortClientCommand;
import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.ClearProjectCommand;
import arb.logic.commands.project.DeleteProjectCommand;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.logic.commands.project.FindProjectCommand;
import arb.logic.commands.project.ListProjectCommand;
import arb.logic.commands.project.MarkProjectCommand;
import arb.logic.commands.project.SortProjectCommand;
import arb.logic.commands.project.UnmarkProjectCommand;
import arb.logic.commands.tag.ListTagCommand;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Client;
import arb.model.client.predicates.ClientContainsTagsPredicate;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Project;
import arb.model.project.predicates.ProjectContainsTagsPredicate;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;
import arb.testutil.ClientBuilder;
import arb.testutil.ClientUtil;
import arb.testutil.EditClientDescriptorBuilder;
import arb.testutil.EditProjectDescriptorBuilder;
import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;
import arb.testutil.ProjectUtil;
import arb.testutil.TypicalProjectSortingOptions;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addClient() throws Exception {
        Client client = new ClientBuilder().build();
        for (String commandWord : AddClientCommand.getCommandWords()) {
            AddClientCommand command = (AddClientCommand) parser
                .parseCommand(ClientUtil.getAddClientCommand(client, commandWord));
            assertEquals(new AddClientCommand(client), command);
        }
    }

    @Test
    public void parseCommand_addProject() throws Exception {
        Project project = new ProjectBuilder().build();
        for (String commandWord : AddProjectCommand.getCommandWords()) {
            AddProjectCommand command = (AddProjectCommand) parser
                .parseCommand(ProjectUtil.getAddProjectCommand(project, commandWord));
            assertEquals(new AddProjectCommand(project, Optional.empty()), command);
        }
    }

    @Test
    public void parseCommand_clearClient() throws Exception {
        for (String commandWord : ClearClientCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof ClearClientCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof ClearClientCommand);
        }
    }

    @Test
    public void parseCommand_clearProject() throws Exception {
        for (String commandWord : ClearProjectCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof ClearProjectCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof ClearProjectCommand);
        }
    }

    @Test
    public void parseCommand_deleteClient() throws Exception {
        for (String commandWord : DeleteClientCommand.getCommandWords()) {
            DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
                    commandWord + " " + INDEX_FIRST.getOneBased());
            assertEquals(new DeleteClientCommand(INDEX_FIRST), command);
        }
    }

    @Test
    public void parseCommand_deleteProject() throws Exception {
        for (String commandWord : DeleteProjectCommand.getCommandWords()) {
            DeleteProjectCommand command = (DeleteProjectCommand) parser.parseCommand(
                    commandWord + " " + INDEX_FIRST.getOneBased());
            assertEquals(new DeleteProjectCommand(INDEX_FIRST), command);
        }
    }

    @Test
    public void parseCommand_editClient() throws Exception {
        for (String commandWord : EditClientCommand.getCommandWords()) {
            Client client = new ClientBuilder().build();
            EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
            EditClientCommand command = (EditClientCommand) parser.parseCommand(commandWord + " "
                    + INDEX_FIRST.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
            assertEquals(new EditClientCommand(INDEX_FIRST, descriptor), command);
        }
    }

    @Test
    public void parseCommand_editProject() throws Exception {
        for (String commandWord : EditProjectCommand.getCommandWords()) {
            Project project = new ProjectBuilder().build();
            EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(project).build();
            EditProjectCommand command = (EditProjectCommand) parser
                    .parseCommand(commandWord + " "
                    + INDEX_FIRST.getOneBased() + " "
                    + ProjectUtil.getEditProjectDescriptorDetails(descriptor));
            assertEquals(new EditProjectCommand(INDEX_FIRST, descriptor), command);
        }
    }

    @Test
    public void parseCommand_exit() throws Exception {
        for (String commandWord : ExitCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof ExitCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof ExitCommand);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void parseCommand_findClient() throws Exception {
        List<String> tags = Arrays.asList("friend", "husband");
        List<String> names = Arrays.asList("foo", "baz");
        ClientContainsTagsPredicate expectedTagsPredicate =
                PredicateUtil.getClientContainsTagPredicate("friend", "husband");
        NameContainsKeywordsPredicate expectedKeywordsPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate("foo", "baz");
        CombinedPredicate<Client> expectedCombinedPredicate = PredicateUtil
                .getCombinedPredicate(expectedKeywordsPredicate, expectedTagsPredicate);
        for (String commandWord : FindClientCommand.getCommandWords()) {
            FindClientCommand command = (FindClientCommand) parser
                    .parseCommand(ClientUtil.getFindClientCommand(tags, names, commandWord));
            assertEquals(new FindClientCommand(expectedCombinedPredicate), command);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void parseCommand_findProject() throws Exception {
        List<String> tags = Arrays.asList("painting", "pottery");
        List<String> names = Arrays.asList("foo", "bar", "baz");
        ProjectContainsTagsPredicate expectedTagsPredicate =
                PredicateUtil.getProjectContainsTagsPredicate("painting", "pottery");
        TitleContainsKeywordsPredicate expectedKeywordsPredicate =
                PredicateUtil.getTitleContainsKeywordsPredicate("foo", "bar", "baz");
        CombinedPredicate<Project> expectedCombinedPredicate = PredicateUtil
                .getCombinedPredicate(expectedKeywordsPredicate, expectedTagsPredicate);
        for (String commandWord : FindProjectCommand.getCommandWords()) {
            FindProjectCommand command = (FindProjectCommand) parser
                    .parseCommand(ProjectUtil.getFindProjectCommand(tags, names, commandWord));
            assertEquals(new FindProjectCommand(expectedCombinedPredicate), command);
        }
    }

    @Test
    public void parseCommand_sortClient() throws Exception {
        for (String commandWord : SortClientCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof SortClientCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof SortClientCommand);
        }
    }

    @Test
    public void parseCommand_sortProject() throws Exception {
        for (String commandWord : SortProjectCommand.getCommandWords()) {
            SortProjectCommand sortProjectCommand = (SortProjectCommand) parser.parseCommand(
                    commandWord + CommandTestUtil.SORTING_OPTION_DESC);
            assertEquals(new SortProjectCommand(TypicalProjectSortingOptions.BY_DEADLINE), sortProjectCommand);
        }
    }

    @Test
    public void parseCommand_help() throws Exception {
        for (String commandWord : HelpCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof HelpCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof HelpCommand);
        }
    }

    @Test
    public void parseCommand_listClient() throws Exception {
        for (String commandWord : ListClientCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof ListClientCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof ListClientCommand);
        }
    }

    @Test
    public void parseCommand_listProject() throws Exception {
        for (String commandWord : ListProjectCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof ListProjectCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof ListProjectCommand);
        }
    }

    @Test
    public void parseCommand_listTag() throws Exception {
        for (String commandWord : ListTagCommand.getCommandWords()) {
            assertTrue(parser.parseCommand(commandWord) instanceof ListTagCommand);
            assertTrue(parser.parseCommand(commandWord + " 3") instanceof ListTagCommand);
        }
    }

    @Test
    public void parseCommand_markProject() throws Exception {
        for (String commandWord : MarkProjectCommand.getCommandWords()) {
            MarkProjectCommand command = (MarkProjectCommand) parser.parseCommand(
                    commandWord + " " + INDEX_FIRST.getOneBased());
            assertEquals(new MarkProjectCommand(INDEX_FIRST), command);
        }
    }

    @Test
    public void parseCommand_unmarkProject() throws Exception {
        for (String commandWord : UnmarkProjectCommand.getCommandWords()) {
            UnmarkProjectCommand command = (UnmarkProjectCommand) parser.parseCommand(
                    commandWord + " " + INDEX_FIRST.getOneBased());
            assertEquals(new UnmarkProjectCommand(INDEX_FIRST), command);
        }
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

    @Test
    public void isCommandWord() {
        Set<String> commandWords = new HashSet<>(Arrays.asList("a", "b", "c"));
        assertTrue(AddressBookParser.isCommandWord(commandWords, "a"));
        assertTrue(AddressBookParser.isCommandWord(commandWords, "b"));
        assertTrue(AddressBookParser.isCommandWord(commandWords, "c"));
        assertFalse(AddressBookParser.isCommandWord(commandWords, "d"));
    }
}
