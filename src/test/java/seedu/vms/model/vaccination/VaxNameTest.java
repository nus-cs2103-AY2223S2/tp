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
}
