package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class VaxNameTest {
    private static List<String> INVALID_LIST = List.of(
        "",
        " ",
        "a\n\ra",
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        "abc:",
        "( ^)o(^ )b");

    private static final String SAMPLE_NAME = "UNCHI";


    @Test
    public void isValidName_valid_true() {
        assertTrue(VaxName.isValidName(
            "   120 - []{}()- Pks niUw LK <> k    "));
        assertTrue(VaxName.isValidName("a"));
    }


    @Test
    public void isValidName_invalid_false() {
        for (String invalid : INVALID_LIST) {
            assertFalse(VaxName.isValidName(invalid), invalid);
        }
    }


    @Test
    public void equalsTest() {
        VaxName testing = new VaxName(SAMPLE_NAME);
        VaxName eqs = new VaxName(SAMPLE_NAME + " ");
        VaxName diff = new VaxName(SAMPLE_NAME + "a");

        assertTrue(testing.equals(testing));
        assertTrue(testing.equals(eqs));
        assertFalse(testing.equals(diff));
    }
}
