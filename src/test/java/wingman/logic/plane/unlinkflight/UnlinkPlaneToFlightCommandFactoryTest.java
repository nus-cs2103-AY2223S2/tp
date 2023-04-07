package wingman.logic.plane.unlinkflight;

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

public class UnlinkPlaneToFlightCommandFactoryTest {
    private final Model model = new ModelManager();
    private final Lazy<Model> modelLazy = Lazy.of(model);
    private final ReadOnlyItemManager<Flight> flightManager = model.getFlightManager();
    private final ReadOnlyItemManager<Plane> planeManager = model.getPlaneManager();
    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy = Lazy.of(model.getFlightManager());
    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy = Lazy.of(model.getPlaneManager());
    private final UnlinkPlaneToFlightCommandFactory factory =
            new UnlinkPlaneToFlightCommandFactory(flightManagerLazy, planeManagerLazy);

    @Test
    public void testUnlinkPlaneToFlightCommandFactory() {
        assertDoesNotThrow(() -> new UnlinkPlaneToFlightCommandFactory());
        assertDoesNotThrow(() -> new UnlinkPlaneToFlightCommandFactory(modelLazy));
        assertDoesNotThrow(() -> new UnlinkPlaneToFlightCommandFactory(flightManagerLazy, planeManagerLazy));
        assertDoesNotThrow(() -> new UnlinkPlaneToFlightCommandFactory(flightManager, planeManager));
    }

    @Test
    public void testGetCommandWord() {
        assertEquals("unlinkflight", factory.getCommandWord());
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
