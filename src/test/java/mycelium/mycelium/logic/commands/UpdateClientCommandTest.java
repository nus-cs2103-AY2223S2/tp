package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.RANTARO;
import static mycelium.mycelium.testutil.TypicalEntities.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.UpdateClientDescriptorBuilder;

public class UpdateClientCommandTest {
    private static final Email EMAIL = WEST.getEmail();
    private static final UpdateClientCommand.UpdateClientDescriptor EMPTY_DESC =
            new UpdateClientCommand.UpdateClientDescriptor();
    private static final UpdateClientCommand.UpdateClientDescriptor NEW_DESC =
            new UpdateClientCommand.UpdateClientDescriptor(RANTARO);
    private Model model = new ModelManager();

    @Test
    public void execute_throwsNullPointerException() {
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, EMPTY_DESC);
        assertThrows(NullPointerException.class, () -> updateClientCommand.execute(null));
    }
    @Test
    public void execute_clientNotPresent_throwsCommandException() {
        // Client not present in model
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, EMPTY_DESC);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_CLIENT, () -> updateClientCommand.execute(model));
    }
    @Test
    public void execute_clientPresent_success() {
        // Client present in model
        model.addClient(WEST);
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, EMPTY_DESC);
        assertThrows(CommandException.class, () -> updateClientCommand.execute(model));
    }
    @Test
    public void execute_clientNoFieldEdited_throwsCommandException() {
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, EMPTY_DESC);
        model.addClient(new ClientBuilder().withEmail(EMAIL.value).build());
        assertThrows(CommandException.class, () -> updateClientCommand.execute(model));
    }
    @Test
    public void execute_clientEdited_success() throws CommandException {
        // Client present in model, all fields edited
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, NEW_DESC);
        model.addClient(WEST);
        updateClientCommand.execute(model);
        // New desc is updated with Rantaro's detail
        assertTrue(model.hasClient(RANTARO));
    }

    @Test
    public void execute_clientUpdateEmailOnly_success() throws CommandException {
        // Client present in model, email edited
        UpdateClientCommand.UpdateClientDescriptor desc = new UpdateClientCommand.UpdateClientDescriptor();
        desc.setEmail(RANTARO.getEmail());
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, desc);
        model.addClient(WEST);
        updateClientCommand.execute(model);
        assertFalse(model.hasClient(WEST));
        assertTrue(model.hasClient(new ClientBuilder().withEmail(RANTARO.getEmail().value).build()));
    }
    @Test
    public void execute_clientUpdateOptionsWithoutNewEmail_success() throws CommandException {
        UpdateClientCommand.UpdateClientDescriptor desc =
                new UpdateClientDescriptorBuilder()
                        .withName("Rocky Balboa")
                        .withYearOfBirth("1990")
                        .withSource("Rocky 2")
                        .withMobileNumber("12345678")
                        .build();
        Client client =
                new ClientBuilder()
                        .withName("Rocky Balboa")
                        .withEmail(EMAIL.value)
                        .withYearOfBirth("1990")
                        .withSource("Rocky 2")
                        .withMobileNumber("12345678")
                        .build();
        model.addClient(WEST);
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(EMAIL, desc);
        updateClientCommand.execute(model);
        assertTrue(model.hasClient(client));

    }

    @Test
    public void equals() {
        UpdateClientCommand.UpdateClientDescriptor desc1 = new UpdateClientCommand.UpdateClientDescriptor();
        UpdateClientCommand.UpdateClientDescriptor desc2 = new UpdateClientCommand.UpdateClientDescriptor();
        desc1.setEmail(EMAIL);
        desc2.setEmail(EMAIL);
        UpdateClientCommand updateClientCommand1 = new UpdateClientCommand(EMAIL, desc1);
        UpdateClientCommand updateClientCommand2 = new UpdateClientCommand(EMAIL, desc2);
        UpdateClientCommand updateClientCommand3 = new UpdateClientCommand(EMAIL, desc1);
        UpdateClientCommand updateClientCommand4 = new UpdateClientCommand(EMAIL, desc2);
        assertEquals(updateClientCommand1, updateClientCommand2);
        assertEquals(updateClientCommand3, updateClientCommand4);
        assertEquals(updateClientCommand2, updateClientCommand1);
        assertEquals(updateClientCommand1, updateClientCommand3);
        assertEquals(updateClientCommand2, updateClientCommand4);
    }
}
