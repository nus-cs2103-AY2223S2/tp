package seedu.vms.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GroupNameTest {
    private static final List<String> INVALID_LIST = List.of(
        "",
        " ",
        "a\n\ra",
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        "abc:",
        "( ^)o(^ )b");

    private static final String SAMPLE_NAME = "UNCHI";


    @Test
    public void isValidName_valid_true() {
        assertTrue(GroupName.isValidName(
            "   120 - []{}()- Pks niUw LK <> k    "));
        assertTrue(GroupName.isValidName("a"));
    }


    @Test
    public void isValidName_invalid_false() {
        for (String invalid : INVALID_LIST) {
            assertFalse(GroupName.isValidName(invalid), invalid);
        }
    }


    @Test
    public void equalsTest() {
        GroupName testing = new GroupName(SAMPLE_NAME);
        GroupName eqs = new GroupName(SAMPLE_NAME + " ");
        GroupName diff = new GroupName(SAMPLE_NAME + "a");
        Integer unrelated = Integer.valueOf(0);

        assertTrue(testing.equals(testing));
        assertTrue(testing.equals(eqs));
        assertFalse(testing.equals(diff));
        assertFalse(testing.equals(unrelated));
    }
}
