package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandFailure;
import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.Pair;

public class DeleteClientCommandTest {
    // TODO add more tests in the manner of DeleteCommandTest.java! The tests we
    // have now are very basic, and I have written them in a rush.

    private static final Function<String, CommandResult> buildCommandResult = (msg)
        -> new CommandResult(msg, new TabSwitchAction(TabSwitchAction.TabSwitch.CLIENT));

    private ModelManager model = new ModelManager();

    @Test
    public void execute_validEmail_success() throws Exception {
        var client = new ClientBuilder().build();
        model.addClient(client);
        assertTrue(model.hasClient(client));

        var cmd = new DeleteClientCommand(client.getEmail());
        var expRes = buildCommandResult.apply(String.format(DeleteClientCommand.MESSAGE_DELETE_PERSON_SUCCESS, client));
        var expModel = new ModelManager(); // empty model

        assertCommandSuccess(cmd, model, expRes, expModel);
    }

    @Test
    public void execute_nonExistentEmail_throwsCommandException() {
        var cmd = new DeleteClientCommand(new Email("hogriders@coc.org"));
        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_CLIENT);
    }

    @Test
    public void equals() {
        DeleteClientCommand deleteAlice = new DeleteClientCommand(new Email("alice@coc.org"));
        DeleteClientCommand deleteBob = new DeleteClientCommand(new Email("bob@coc.org"));

        Map<String, Pair<DeleteClientCommand, DeleteClientCommand>> equalTests = Map.of(
            "same object", Pair.of(deleteAlice, deleteAlice),
            "same email, different object", Pair.of(deleteAlice, new DeleteClientCommand(new Email("alice@coc.org")))
        );
        equalTests.forEach((desc, tt) -> {
            assertEquals(tt.first, tt.second, "While testing case: " + desc);
        });

        Map<String, Pair<DeleteClientCommand, Object>> unequalTests = Map.of(
            "different email", Pair.of(deleteAlice, deleteBob),
            "different type", Pair.of(deleteAlice, new Object()),
            "null", Pair.of(deleteAlice, null)
        );
        unequalTests.forEach((desc, tt) -> {
            assertNotEquals(tt.first, tt.second, "While testing case: " + desc);
        });
    }
}
