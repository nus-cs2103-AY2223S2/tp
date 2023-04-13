package mycelium.mycelium.ui.resultoutput;

import static mycelium.mycelium.ui.testutil.GuiTestAssert.assertCardDisplaysClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ClientListCardHandle;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.ui.GuiUnitTest;
import mycelium.mycelium.ui.entitypanel.ClientEntity;

public class ClientEntityTest extends GuiUnitTest {

    @Test
    public void display() {
        Client client = new ClientBuilder().build();
        ClientEntity clientListCard = new ClientEntity(client, 1);
        uiPartExtension.setUiPart(clientListCard);
        assertCardDisplay(clientListCard, client, 1);
    }

    @Test
    public void equals() {
        Client client = new ClientBuilder().build();
        ClientEntity clientCard = new ClientEntity(client, 0);

        // same person, same index -> returns true
        ClientEntity copy = new ClientEntity(client, 0);
        assertTrue(clientCard.equals(copy));

        // same object -> returns true
        assertTrue(clientCard.equals(clientCard));

        // null -> returns false
        assertFalse(clientCard.equals(null));

        // different types -> returns false
        assertFalse(clientCard.equals(0));

        // different person, same index -> returns false
        Client differentPerson = new ClientBuilder().withName("differentName").build();
        assertFalse(clientCard.equals(new ClientEntity(differentPerson, 0)));

        // same person, different index -> returns false
        assertFalse(clientCard.equals(new ClientEntity(client, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedPerson} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ClientEntity clientListCard, Client expectedClient, int expectedId) {
        guiRobot.pauseForHuman();

        ClientListCardHandle clientListCardHandle = new ClientListCardHandle(clientListCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", clientListCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysClient(expectedClient, clientListCardHandle);
    }
}
