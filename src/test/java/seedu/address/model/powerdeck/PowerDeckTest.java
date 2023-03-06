package seedu.address.model.powerdeck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;


class PowerDeckTest {

    private final PowerDeck deck = new PowerDeck();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), deck.getCardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deck.resetData(null));
    }

    // Todo: create a typical deck with typical cards to test the remaining methods
}
