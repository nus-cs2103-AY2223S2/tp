package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.RAT;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Name;

class JsonAdaptedMobTest {
    private static final String INVALID_NAME = "$keleton Archer";

    private static final JsonAdaptedStats VALID_STATS = new JsonAdaptedStats(RAT.getStats());
    private static final JsonAdaptedInventory VALID_INVENTORY = new JsonAdaptedInventory(RAT.getInventory());
    private static final float VALID_CHALLENGE_RATING = RAT.getChallengeRating();
    private static final boolean VALID_IS_LEGENDARY = RAT.getLegendaryStatus();
    private static final List<JsonAdaptedTag> VALID_TAGS = RAT.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validMobDetails_returnsMob() throws Exception {
        JsonAdaptedMob mob = new JsonAdaptedMob(RAT);
        assertEquals(RAT, mob.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMob mob =
                new JsonAdaptedMob(INVALID_NAME, VALID_STATS, VALID_INVENTORY,
                        VALID_CHALLENGE_RATING, VALID_IS_LEGENDARY, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, mob::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMob mob =
                new JsonAdaptedMob(null, VALID_STATS, VALID_INVENTORY,
                        VALID_CHALLENGE_RATING, VALID_IS_LEGENDARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, mob::toModelType);
    }

}
