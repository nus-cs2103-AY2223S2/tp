package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFish.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFishes.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;

public class JsonAdaptedFishTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_LAST_FED_DATE = "+651234";
    private static final String INVALID_SPECIES = "example.com";
    private static final String INVALID_FEEDING_INTERVAL = " ";
    private static final String INVALID_TANK = "";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_LAST_FED_DATE = BENSON.getLastFedDate().toString();
    private static final String VALID_SPECIES = BENSON.getSpecies().toString();
    private static final String VALID_FEEDING_INTERVAL = BENSON.getFeedingInterval().toString();
    private static final String VALID_TANK = BENSON.getTank().getTankName().fullTankName;
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validFishDetails_returnsFish() throws Exception {
        JsonAdaptedFish fish = new JsonAdaptedFish(BENSON);
        assertEquals(BENSON, fish.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFish fish =
                new JsonAdaptedFish(INVALID_NAME, VALID_LAST_FED_DATE, VALID_SPECIES, VALID_FEEDING_INTERVAL,
                        VALID_TANK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFish fish = new JsonAdaptedFish(null, VALID_LAST_FED_DATE, VALID_SPECIES,
                VALID_FEEDING_INTERVAL, VALID_TANK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_invalidLastFedDate_throwsIllegalValueException() {
        JsonAdaptedFish fish =
                new JsonAdaptedFish(VALID_NAME, INVALID_LAST_FED_DATE, VALID_SPECIES, VALID_FEEDING_INTERVAL,
                        VALID_TANK, VALID_TAGS);
        String expectedMessage = LastFedDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_nullLastFedDate_throwsIllegalValueException() {
        JsonAdaptedFish fish = new JsonAdaptedFish(VALID_NAME, null, VALID_SPECIES, VALID_FEEDING_INTERVAL,
                VALID_TANK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LastFedDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_invalidSpecies_throwsIllegalValueException() {
        JsonAdaptedFish fish =
                new JsonAdaptedFish(VALID_NAME, VALID_LAST_FED_DATE, INVALID_SPECIES, VALID_FEEDING_INTERVAL,
                        VALID_TANK, VALID_TAGS);
        String expectedMessage = Species.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_nullSpecies_throwsIllegalValueException() {
        JsonAdaptedFish fish = new JsonAdaptedFish(VALID_NAME, VALID_LAST_FED_DATE, null, VALID_FEEDING_INTERVAL,
                VALID_TANK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_invalidFeedingInterval_throwsIllegalValueException() {
        JsonAdaptedFish fish =
                new JsonAdaptedFish(VALID_NAME, VALID_LAST_FED_DATE, VALID_SPECIES, INVALID_FEEDING_INTERVAL,
                        VALID_TANK, VALID_TAGS);
        String expectedMessage = FeedingInterval.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedFish fish = new JsonAdaptedFish(VALID_NAME, VALID_LAST_FED_DATE, VALID_SPECIES, null,
                VALID_TANK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FeedingInterval.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fish::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFish fish =
                new JsonAdaptedFish(VALID_NAME, VALID_LAST_FED_DATE, VALID_SPECIES, VALID_FEEDING_INTERVAL,
                        VALID_TANK, invalidTags);
        assertThrows(IllegalValueException.class, fish::toModelType);
    }

}
