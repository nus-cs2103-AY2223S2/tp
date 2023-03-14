package mycelium.mycelium.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.testutil.Assert;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.Pair;

public class AddClientCommandTest {
    /**
     * We don't use a stub for the model, and directly use the ModelManager
     * class. This is because the ModelManager already has unit and integration
     * tests.
     */
    private ModelManager model = new ModelManager();

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        // For this test, we create a dummy client, add it through the model,
        // and assert that the model now sees the client, and that the command's
        // feedback is what we expect.
        Client client = new ClientBuilder().build();
        CommandResult res = new AddClientCommand(client).execute(model);

        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS, client), res.getFeedbackToUser());
        assertTrue(model.hasClient(client));
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() throws Exception {
        Client client = new ClientBuilder().build();
        AddClientCommand cmd = new AddClientCommand(client);

        // Execute this command once. Then we execute it again.
        cmd.execute(model);
        assertTrue(model.hasClient(client));

        Assert.assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    public void equals() {
        // Create two clients with different emails. All else identical.
        Client alice = new ClientBuilder().withEmail("alice@silkroad.com").build();
        Client bob = new ClientBuilder().withEmail("bob@silkroad.com").build();

        AddClientCommand addAlice = new AddClientCommand(alice);
        AddClientCommand addBob = new AddClientCommand(bob);

        Map<String, Pair<AddClientCommand, AddClientCommand>> equalTests = Map.of(
            "same object",
            Pair.of(addAlice, addAlice),
            "same fields, different object",
            Pair.of(addAlice, new AddClientCommand(alice)),
            "same email, different fields",
            Pair.of(addAlice,
                new AddClientCommand(new ClientBuilder().withEmail(alice.getEmail().value)
                    .withName("not alice")
                    .build()))
        );

        equalTests.forEach((desc, tt) -> {
            assertEquals(tt.first, tt.second, "While testing case: " + desc);
        });

        Map<String, Pair<AddClientCommand, Object>> notEqualTests = Map.of(
            "different email", Pair.of(addAlice, addBob),
            "null", Pair.of(addAlice, null),
            "different type", Pair.of(addAlice, 1)
        );

        notEqualTests.forEach((desc, tt) -> {
            assertNotEquals(tt.first, tt.second, "While testing case: " + desc);
        });
    }
}
