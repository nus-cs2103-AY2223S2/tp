package mycelium.mycelium.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.commands.UpdateProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.Assert;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.ProjectBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_createClient() throws Exception {
        String input = AddClientCommand.COMMAND_ACRONYM + " -cn Jamal -e jamal@hogriders.org";
        AddClientCommand got = (AddClientCommand) parser.parseCommand(input);
        AddClientCommand
            want =
            new AddClientCommand(new ClientBuilder().withName("Jamal").withEmail("jamal@hogriders.org").build());
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_createProject() throws Exception {
        String input = AddProjectCommand.COMMAND_ACRONYM + " -pn Bing -e jamal@hogriders.org";
        AddProjectCommand got = (AddProjectCommand) parser.parseCommand(input);
        AddProjectCommand
            want =
            new AddProjectCommand(new ProjectBuilder().withName("Bing").withClientEmail("jamal@hogriders.org").build());
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_deleteClient() throws Exception {
        String input = DeleteClientCommand.COMMAND_ACRONYM + " -e jamal@hogriders.org";
        DeleteClientCommand got = (DeleteClientCommand) parser.parseCommand(input);
        DeleteClientCommand want = new DeleteClientCommand(new Email("jamal@hogriders.org"));
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_deleteProject() throws Exception {
        String input = DeleteProjectCommand.COMMAND_ACRONYM + " -pn Bing";
        DeleteProjectCommand got = (DeleteProjectCommand) parser.parseCommand(input);
        DeleteProjectCommand want = new DeleteProjectCommand(NonEmptyString.of("Bing"));
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_updateProject() throws Exception {
        var input = UpdateProjectCommand.COMMAND_ACRONYM + " -pn Bing -e foo@bar.com";
        var descriptor = new UpdateProjectCommand.UpdateProjectDescriptor();
        descriptor.setClientEmail(new Email("foo@bar.com"));

        var got = (UpdateProjectCommand) parser.parseCommand(input);
        var want = new UpdateProjectCommand(NonEmptyString.of("Bing"), descriptor);

        assertEquals(want, got);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class,
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_CHECK_USER_GUIDE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class,
            Messages.MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
