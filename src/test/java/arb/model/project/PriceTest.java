package arb.model.project;

import static arb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void constructor_valid_priceObject() {
        assertTrue(new Price("3") instanceof Price);
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid prices
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // only whitespace
        assertFalse(Price.isValidPrice("*=")); // only non-alphanumeric characters
        assertFalse(Price.isValidPrice("sky")); // contains non-numeric characters
        assertFalse(Price.isValidPrice("0003")); // number with padded zeroes
        assertFalse(Price.isValidPrice("3.000")); // number with trailing decimals of zero


        // valid prices
        assertTrue(Price.isValidPrice("3")); // number only
        assertTrue(Price.isValidPrice("3.15")); // number with decimals
    }

}
