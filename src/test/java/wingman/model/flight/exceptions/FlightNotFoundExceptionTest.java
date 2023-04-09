package wingman.model.flight.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightNotFoundExceptionTest {
    @Test
    public void testFlightNotFoundException() {
        FlightNotFoundException exception = assertThrows(FlightNotFoundException.class, () -> {
            throw new FlightNotFoundException();
        });
        assertEquals("Flight not found in the list.", exception.getMessage());
    }
}
