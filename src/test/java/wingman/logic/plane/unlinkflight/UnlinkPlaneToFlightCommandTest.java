package wingman.logic.plane.unlinkflight;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.plane.linkflight.LinkPlaneToFlightCommand;
import wingman.model.Model;
import wingman.model.ModelManager;
import wingman.model.flight.Flight;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;

public class UnlinkPlaneToFlightCommandTest {
    private final Flight flight = new Flight("code");
    private final Plane plane = new Plane("model", 1);
    private final Map<FlightPlaneType, Plane> mapPlane = Map.of(FlightPlaneType.PLANE_USING, plane);
    private final UnlinkPlaneToFlightCommand unlinkCommand = new UnlinkPlaneToFlightCommand(flight, mapPlane);
    private final Model model = new ModelManager();

    @Test
    public void testUnlinkPlaneToFlightCommand() {
        assertDoesNotThrow(() -> new UnlinkPlaneToFlightCommand(flight, mapPlane));
    }

    @Test
    public void testToString() {
        assertEquals("Unlinked model from code.", unlinkCommand.toString());
    }

    @Test
    public void testExecute() throws CommandException {
        // try to unlink when a link doesn't already exist
        assertThrows(CommandException.class, () -> unlinkCommand.execute(model));

        // try to unlink when a link already exists
        LinkPlaneToFlightCommand linkCommand = new LinkPlaneToFlightCommand(flight, mapPlane);
        linkCommand.execute(model);
        assertDoesNotThrow(() -> unlinkCommand.execute(model));
    }

    @Test
    public void testExecuteCommandResult() throws CommandException {
        LinkPlaneToFlightCommand linkCommand = new LinkPlaneToFlightCommand(flight, mapPlane);
        linkCommand.execute(model);
        assertEquals(new CommandResult("Unlinked model from code."), unlinkCommand.execute(model));
    }
}
