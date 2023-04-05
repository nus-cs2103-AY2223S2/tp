package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemProfitTest {

    @Test
    public void stringConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemPrice((String) null));
    }

    @Test
    public void doubleConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemPrice((Double) null));
    }

    @Test
    public void constructor_invalidStringItemPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemPrice(invalidPrice));
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

        assertTrue(itemProfit.equals(itemProfit)); // same object
        assertTrue(differentItemProfit.equals(differentItemProfit)); // same object

        assertTrue(itemProfit.equals(new ItemProfit(new ItemSellingPrice("10.00"), new ItemCost("5.00"))));
        assertTrue(itemProfit.equals(new ItemProfit(new ItemSellingPrice("10"), new ItemCost("5.00"))));
        assertTrue(itemProfit.equals(new ItemProfit(new ItemSellingPrice("10.0"), new ItemCost("5.00"))));
        assertTrue(itemProfit.equals(new ItemProfit(new ItemSellingPrice("10.00"), new ItemCost("5"))));
        assertTrue(itemProfit.equals(new ItemProfit(new ItemSellingPrice("10"), new ItemCost("5.0"))));
        assertTrue(itemProfit.equals(new ItemProfit(new ItemSellingPrice("10.0"), new ItemCost("5.0"))));

        assertFalse(itemProfit.equals(null));
        assertFalse(itemProfit.equals(differentItemProfit));
        assertFalse(itemProfit.equals(1));
    }
}
