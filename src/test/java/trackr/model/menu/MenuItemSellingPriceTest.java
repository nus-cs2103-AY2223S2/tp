package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemSellingPriceTest {

    @Test
    public void constructor_invalidStringItemPrice_throwsException() {
        assertThrows(NullPointerException.class, () -> new ItemSellingPrice((String) null));
        assertThrows(NullPointerException.class, () -> new ItemSellingPrice((Double) null));

        assertThrows(IllegalArgumentException.class, () -> new ItemSellingPrice(""));
        assertThrows(IllegalArgumentException.class, () -> new ItemSellingPrice("-1"));
        assertThrows(IllegalArgumentException.class, () -> new ItemSellingPrice("1.000"));
    }

    @Test
    public void toStringTest() {
        assertEquals("Selling Price: $1.00", new ItemSellingPrice("1").toString());
        assertEquals("Selling Price: $1.00", new ItemSellingPrice("1.0").toString());
        assertEquals("Selling Price: $1.00", new ItemSellingPrice("1.00").toString());
        assertEquals("Selling Price: $1.00", new ItemSellingPrice(1.00).toString());
    }

}
