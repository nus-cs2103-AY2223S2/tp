package wingman.logic.flight.linklocation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.flight.Flight;
import wingman.model.link.exceptions.LinkException;
import wingman.model.location.FlightLocationType;
import wingman.model.location.Location;

@ExtendWith(MockitoExtension.class)
public class UnlinkFlightToLocationCommandTest {
    @Mock
    private Flight flight;

    @Mock
    private Location location1;

    @Mock
    private Location location2;

    @Mock
    private Model model;

    @Test
    void execute_validState_doesNotThrow() throws LinkException {
        UnlinkFlightToLocationCommand command = new UnlinkFlightToLocationCommand(
                flight,
                Map.of(FlightLocationType.LOCATION_DEPARTURE, location1)
        );
        assertDoesNotThrow(() -> command.execute(model));
        Mockito.verify(flight, times(1)).removeLocation(
                eq(FlightLocationType.LOCATION_DEPARTURE),
                eq(location1));
    }

    @Test
    void execute_twoTypes_calledWithEach() throws LinkException {
        UnlinkFlightToLocationCommand command = new UnlinkFlightToLocationCommand(
                flight,
                Map.of(
                        FlightLocationType.LOCATION_DEPARTURE, location1,
                        FlightLocationType.LOCATION_ARRIVAL, location2
                )
        );
        assertDoesNotThrow(() -> command.execute(model));
        Mockito.verify(flight, times(1)).removeLocation(
                eq(FlightLocationType.LOCATION_DEPARTURE),
                eq(location1));
        Mockito.verify(flight, times(1)).removeLocation(
                eq(FlightLocationType.LOCATION_ARRIVAL),
                eq(location2));
    }

    @Test
    void execute_invalidState_throwsCommandException() throws LinkException {
        UnlinkFlightToLocationCommand command = new UnlinkFlightToLocationCommand(
                flight,
                Map.of(FlightLocationType.LOCATION_DEPARTURE, location1)
        );
        Mockito.doThrow(LinkException.class).when(flight).removeLocation(
                any(FlightLocationType.class),
                any(Location.class));
        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
