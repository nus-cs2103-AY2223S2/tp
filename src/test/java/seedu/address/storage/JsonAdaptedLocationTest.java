package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.location.util.TypicalLocation.SENGKANG;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;

public class JsonAdaptedLocationTest {

    @Test
    public void toModelType_validLocationDetails_returnsLocation() throws Exception {
        JsonAdaptedLocation jsonAdaptedLocation = new JsonAdaptedLocation(SENGKANG);
        assertEquals(jsonAdaptedLocation.toModelType(), SENGKANG);
    }

    @Test
    public void toModelType_validEmptyLocationDetails_returnsLocation() throws Exception {
        JsonAdaptedLocation jsonAdaptedLocation =
                new JsonAdaptedLocation(new Location(SENGKANG.getLatitude(), SENGKANG.getLongitude()));
        assertEquals(jsonAdaptedLocation.toModelType(), SENGKANG);
        assertEquals(jsonAdaptedLocation.toModelType(),
                new Location(SENGKANG.getLatitude(), SENGKANG.getLongitude()));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedLocation jsonAdaptedLocation =
                new JsonAdaptedLocation(null, SENGKANG.getLatitude(), SENGKANG.getLongitude());
        assertThrows(IllegalValueException.class, jsonAdaptedLocation::toModelType);
    }

    @Test
    public void toModelType_latitudeTooHigh_throwsIllegalValueException() {
        JsonAdaptedLocation jsonAdaptedLocation =
                new JsonAdaptedLocation(SENGKANG.getName(), 1.5, SENGKANG.getLongitude());
        assertThrows(IllegalValueException.class, jsonAdaptedLocation::toModelType);
    }

    @Test
    public void toModelType_latitudeTooLow_throwsIllegalValueException() {
        JsonAdaptedLocation jsonAdaptedLocation =
                new JsonAdaptedLocation(SENGKANG.getName(), 1.2, SENGKANG.getLongitude());
        assertThrows(IllegalValueException.class, jsonAdaptedLocation::toModelType);
    }

    @Test
    public void toModelType_longitudeTooHigh_throwsIllegalValueException() {
        JsonAdaptedLocation jsonAdaptedLocation =
                new JsonAdaptedLocation(SENGKANG.getName(), SENGKANG.getLatitude(), 104.2);
        assertThrows(IllegalValueException.class, jsonAdaptedLocation::toModelType);
    }

    @Test
    public void toModelType_longitudeTooLow_throwsIllegalValueException() {
        JsonAdaptedLocation jsonAdaptedLocation =
                new JsonAdaptedLocation(SENGKANG.getName(), SENGKANG.getLatitude(), 103.6);
        assertThrows(IllegalValueException.class, jsonAdaptedLocation::toModelType);
    }
}
