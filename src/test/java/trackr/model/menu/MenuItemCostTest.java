package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemCostTest {

    @Test
    public void constructor_invalidStringItemPrice_throwsException() {
        assertThrows(NullPointerException.class, () -> new ItemCost(null));

        assertThrows(IllegalArgumentException.class, () -> new ItemCost(""));

        assertThrows(IllegalArgumentException.class, () -> new ItemCost("-1"));

        assertThrows(IllegalArgumentException.class, () -> new ItemCost("1.000"));
    }

    @Test
    public void toStringTest() {
        assertEquals("Cost: $1.00", new ItemCost("1").toString());
        assertEquals("Cost: $1.00", new ItemCost("1.0").toString());
        assertEquals("Cost: $1.00", new ItemCost("1.00").toString());
    }

}
