package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Keydate;

public class KeydateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Keydate(null, "2023-01-01"));
        assertThrows(NullPointerException.class, () -> new Keydate("OA", null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidKey = "";
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Keydate("OA", invalidDate));
        assertThrows(IllegalArgumentException.class, () -> new Keydate(invalidKey, "2023-01-01"));
    }

    @Test
    public void isValidDate() {
        // null Keydate
        assertThrows(NullPointerException.class, () -> Keydate.isValidKeydate(null));

        // blank Keydate
        assertFalse(Keydate.isValidKeydate("".split("@"))); // empty string
        assertFalse(Keydate.isValidKeydate(" ".split("@"))); // spaces only

        // missing parts
        assertFalse(Keydate.isValidKeydate("@2023-12-12".split("@"))); // missing key part
        assertFalse(Keydate.isValidKeydate("OA2023-12-12".split("@"))); // missing '@' symbol
        assertFalse(Keydate.isValidKeydate("OA@".split("@"))); // missing date part

        // invalid key
        assertFalse(Keydate.isValidKeydate(" @2023-12-12".split("@"))); // whitespace key
        assertFalse(Keydate.isValidKeydate("123@2023-12-12".split("@"))); // number key
        assertFalse(Keydate.isValidKeydate("!*#@2023-12-12".split("@"))); // special key
        assertFalse(Keydate.isValidKeydate("@@@2023-12-12".split("@"))); // special @ key
        assertFalse(Keydate.isValidKeydate("peter@jack@2023-12-12".split("@"))); // '@' symbol in key part
        assertFalse(Keydate.isValidKeydate("peter--jack@2023-12-12".split("@"))); // key part has two hyphens

        // invalid date
        assertFalse(Keydate.isValidKeydate("OA@2023/12/12".split("@"))); // invalid date
        assertFalse(Keydate.isValidKeydate("OA@2023".split("@"))); // invalid date
        assertFalse(Keydate.isValidKeydate("OA@20231212".split("@"))); // invalid date
        assertFalse(Keydate.isValidKeydate("OA@2023 -12-12".split("@"))); // spaces in date
        assertFalse(Keydate.isValidKeydate("OA@2023-12-12-12".split("@"))); // too long date
        assertFalse(Keydate.isValidKeydate("OA@@2023-12-12".split("@"))); // double '@' symbol
        assertFalse(Keydate.isValidKeydate("OA@-2023-12-12".split("@"))); // date starts with a hyphen
        assertFalse(Keydate.isValidKeydate("OA@2023-12-12-".split("@"))); // date ends with a hyphen
        assertFalse(Keydate.isValidKeydate("OA@-2023-12-12".split("@"))); // date starts with a hyphen
        assertFalse(Keydate.isValidKeydate("OA@2023-02-29".split("@"))); // 29 feb, non-leap
        assertFalse(Keydate.isValidKeydate("OA@2023-02-30".split("@"))); // 30 feb, non-leap
        assertFalse(Keydate.isValidKeydate("OA@2020-02-30".split("@"))); // 30 feb, leap
        assertFalse(Keydate.isValidKeydate("OA@2020-11-31".split("@"))); // 31 nov, leap

        // valid Keydate
        assertTrue(Keydate.isValidKeydate("test@2023-12-12".split("@"))); // alphabets only
        assertTrue(Keydate.isValidKeydate("test@2020-02-29".split("@"))); // 29 fec, leap
        assertTrue(Keydate.isValidKeydate("test@2023-12-31".split("@"))); // 31 dec
        assertTrue(Keydate.isValidKeydate("test@2023-11-30".split("@"))); // 30 nov
        assertTrue(Keydate.isValidKeydate("test test@2023-12-12".split("@"))); // spcae in key
        assertTrue(Keydate.isValidKeydate("  test  @  2023-12-12   ".split("@"))); // space around key and date
        assertTrue(Keydate.isValidKeydate("very very very long key part@2023-12-12".split("@"))); // long key



    }
}
