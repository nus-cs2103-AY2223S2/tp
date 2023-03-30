package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandFailure;
import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.testutil.Assert;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.Pair;

public class AddClientCommandTest {

    private static final Function<String, CommandResult> buildCommandResult = (msg)
        -> new CommandResult(msg, new TabSwitchAction(TabSwitchAction.TabSwitch.CLIENT));

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
        var client = new ClientBuilder().build();
        var cmd = new AddClientCommand(client);

        var msg = String.format(AddClientCommand.MESSAGE_SUCCESS, client);
        var expRes = buildCommandResult.apply(msg);
        var expModel = new ModelManager();
        expModel.addClient(client);

        assertCommandSuccess(cmd, model, expRes, model);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() throws Exception {
        var client = new ClientBuilder().build();
        var cmd = new AddClientCommand(client);

        model.addClient(client);
        assertTrue(model.hasClient(client));

        assertCommandFailure(cmd, model, AddClientCommand.MESSAGE_DUPLICATE_CLIENT);
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
