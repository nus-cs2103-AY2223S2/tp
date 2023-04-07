package wingman.logic.plane.linkflight;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import wingman.commons.fp.Lazy;
import wingman.model.Model;
import wingman.model.ModelManager;
import wingman.model.ReadOnlyItemManager;
import wingman.model.flight.Flight;
import wingman.model.plane.Plane;

public class LinkPlaneToFlightCommandFactoryTest {
    private final Model model = new ModelManager();
    private final Lazy<Model> modelLazy = Lazy.of(model);
    private final ReadOnlyItemManager<Flight> flightManager = model.getFlightManager();
    private final ReadOnlyItemManager<Plane> planeManager = model.getPlaneManager();
    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy = Lazy.of(model.getFlightManager());
    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy = Lazy.of(model.getPlaneManager());
    private final LinkPlaneToFlightCommandFactory factory =
            new LinkPlaneToFlightCommandFactory(flightManagerLazy, planeManagerLazy);

    @Test
    public void testLinkPlaneToFlightCommandFactory() {
        assertDoesNotThrow(() -> new LinkPlaneToFlightCommandFactory());
        assertDoesNotThrow(() -> new LinkPlaneToFlightCommandFactory(modelLazy));
        assertDoesNotThrow(() -> new LinkPlaneToFlightCommandFactory(flightManagerLazy, planeManagerLazy));
        assertDoesNotThrow(() -> new LinkPlaneToFlightCommandFactory(flightManager, planeManager));
    }

    @Test
    public void testGetCommandWord() {
        assertEquals("linkflight", factory.getCommandWord());
    }

    @Test
    public void testGetPrefixes() {
        assertEquals(Optional.of(Set.of("/fl", "/pl")), factory.getPrefixes());
    }

    // TODO: write the test case for the createCommand(CommandParam param) method

    //    @Test
    //    public void testCreateCommand() {
    //
    //    }
}
