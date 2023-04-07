package wingman.logic.plane.linkflight;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.ModelManager;
import wingman.model.flight.Flight;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;

public class LinkPlaneToFlightCommandTest {
    private final Flight flight = new Flight("code");
    private final Plane plane1 = new Plane("model1", 1);
    private final Plane plane2 = new Plane("model2", 1);

    private final Map<FlightPlaneType, Plane> mapPlane1 = Map.of(
            FlightPlaneType.PLANE_USING,
            plane1
    );
    private final Map<FlightPlaneType, Plane> mapPlane2 = Map.of(
            FlightPlaneType.PLANE_USING,
            plane2
    );

    private final LinkPlaneToFlightCommand linkCommand1 = new LinkPlaneToFlightCommand(
            flight,
            mapPlane1
    );
    private final LinkPlaneToFlightCommand linkCommand2 = new LinkPlaneToFlightCommand(
            flight,
            mapPlane2
    );
    private final Model model = new ModelManager();

    @Test
    public void testLinkPlaneToFlightCommand() {
        assertDoesNotThrow(() -> new LinkPlaneToFlightCommand(
                flight,
                mapPlane1
        ));
    }

    @Test
    public void testExecute() {
        assertDoesNotThrow(() -> linkCommand1.execute(model));

        // making the same link again
        assertThrows(CommandException.class, () -> linkCommand1.execute(model));

        // making a link after an initial link exists
        assertDoesNotThrow(() -> linkCommand2.execute(model));
    }
}
