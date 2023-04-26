package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class TradeNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TradeName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTradeName = "";
        assertThrows(IllegalArgumentException.class, () -> new TradeName(invalidTradeName));
    }

    @Test
    public void isValidTradeName() {
        // null name
        assertThrows(NullPointerException.class, () -> TradeName.isValidTradeName(null));

        // invalid trade name
        assertFalse(TradeName.isValidTradeName("")); // empty string
        assertFalse(TradeName.isValidTradeName(" ")); // spaces only
        assertFalse(TradeName.isValidTradeName("^")); // only non-alphanumeric characters
        assertFalse(TradeName.isValidTradeName("name*")); // contains non-alphanumeric characters
        assertFalse(TradeName.isValidTradeName(StringUtil.generateRandomString(51))); // long name exceed 50 char limit

        // valid trade name
        assertTrue(TradeName.isValidTradeName("Antirheumatics")); // alphabets only
        assertTrue(TradeName.isValidTradeName("Antirheumatics 123")); // alphanumeric characters
        assertTrue(TradeName.isValidTradeName("Capital Tan")); // with capital letters
        assertTrue(TradeName.isValidTradeName("long long long long long long name")); // long names
        assertTrue(TradeName.isValidTradeName("a" + StringUtil.generateRandomString(49))); // long names with 50 char
    }

    @Test
    public void equals() {
        TradeName tradeName = new TradeName("Actamin");
        // null -> returns false
        assertFalse(tradeName.equals(null));

        // same object -> returns true
        assertTrue(tradeName.equals(tradeName));

        // same values -> returns true
        assertTrue(tradeName.equals(new TradeName("Actamin")));

        // different values -> return false
        assertFalse(tradeName.equals("Zestril"));
    }
}
