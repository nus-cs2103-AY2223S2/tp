package mycelium.mycelium.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.Pair;

public class DeleteClientCommandTest {
    // TODO add more tests in the manner of DeleteCommandTest.java! The tests we
    // have now are very basic, and I have written them in a rush.

    private ModelManager model = new ModelManager();

    @Test
    public void execute_validEmail_success() throws Exception {
        Client client = new ClientBuilder().build();
        model.addClient(client);

        DeleteClientCommand cmd = new DeleteClientCommand(client.getEmail());
        CommandResult res = cmd.execute(model);

        assertFalse(model.hasClient(client));
        assertEquals(String.format(DeleteClientCommand.MESSAGE_DELETE_PERSON_SUCCESS, client), res.getFeedbackToUser());
    }

    @Test
    public void execute_nonExistentEmail_throwsClientNotFoundException() {
        DeleteClientCommand cmd = new DeleteClientCommand(new Email("hogriders@coc.org"));

        assertThrows(CommandException.class, () -> cmd.execute(model));
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
