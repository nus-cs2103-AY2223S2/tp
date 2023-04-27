package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemProfitTest {

    @Test
    public void itemConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemProfit(new ItemSellingPrice("1"), null));
        assertThrows(NullPointerException.class, () -> new ItemProfit(null, new ItemCost("1")));
    }

    @Test
    public void doubleConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemProfit(null));
    }

    @Test
    public void isValidProfit() {
        // null price
        assertThrows(NullPointerException.class, () -> ItemProfit.isValidProfit(null));

        // invalid price - String
        assertFalse(ItemProfit.isValidProfit(""));
        assertFalse(ItemProfit.isValidProfit(" "));
        assertFalse(ItemProfit.isValidProfit("NaN")); // non-numeric
        assertFalse(ItemProfit.isValidProfit("90p")); // alphabets within digits
        assertFalse(ItemProfit.isValidProfit("10 10")); // contains spaces
        assertFalse(ItemProfit.isValidProfit("10,10")); // comma instead of decimal
        assertFalse(ItemProfit.isValidProfit("10.000")); // more than 2 decimal places

        // valid price
        assertTrue(ItemProfit.isValidProfit("1")); // no decimal places
        assertTrue(ItemProfit.isValidProfit("1.0")); // exactly 1 decimal places
        assertTrue(ItemProfit.isValidProfit("1.00")); // exactly 2 decimal places
        assertTrue(ItemProfit.isValidProfit("9999991232131321323.00")); //large number

    }

    @Test
    public void equals() {
        ItemSellingPrice itemSellingPrice = new ItemSellingPrice("10.00");
        ItemSellingPrice differentSellingPrice = new ItemSellingPrice("21.00");

        ItemCost itemCost = new ItemCost("5.00");
        ItemCost differentItemCost = new ItemCost("25.00");

        ItemProfit itemProfit = new ItemProfit(itemSellingPrice, itemCost);
        ItemProfit differentItemProfit = new ItemProfit(differentSellingPrice, differentItemCost);

        assertEquals(itemProfit, itemProfit); // same object

        // same value -> true
        assertEquals(itemProfit, new ItemProfit(new ItemSellingPrice("10.00"), new ItemCost("5.00")));
        assertEquals(itemProfit, new ItemProfit(new ItemSellingPrice("10"), new ItemCost("5.00")));
        assertEquals(itemProfit, new ItemProfit(new ItemSellingPrice("10.0"), new ItemCost("5.00")));
        assertEquals(itemProfit, new ItemProfit(new ItemSellingPrice("10.00"), new ItemCost("5")));
        assertEquals(itemProfit, new ItemProfit(new ItemSellingPrice("10"), new ItemCost("5.0")));
        assertEquals(itemProfit, new ItemProfit(new ItemSellingPrice("10.0"), new ItemCost("5.0")));

        assertEquals(itemProfit, new ItemProfit(5.00));
        assertEquals(itemProfit, new ItemProfit(5.0));

        // different type -> false
        assertNotEquals(null, itemProfit);
        assertNotEquals(1, itemProfit);

        //different value -> false
        assertNotEquals(itemProfit, differentItemProfit);
    }

    @Test
    public void toStringTest() {
        assertEquals("Profit: $5.00",
                new ItemProfit(new ItemSellingPrice("10.00"), new ItemCost("5.00")).toString());
        assertEquals("Profit: $1.00", new ItemProfit(1.0).toString());
    }

    @Test
    public void hashCode_success() {
        Double val = 10.0;
        int hashCode = Double.hashCode(val);
        assertEquals(new ItemProfit(val).hashCode(), hashCode);
    }

}
