package seedu.vms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
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

    private static final List<GroupName> LIST_TO_SORT = List.of(
            new GroupName("a"),
            new GroupName("A"),
            new GroupName("B"),
            new GroupName("b"),
            new GroupName("bBB"),
            new GroupName("bBb"));
    private static final List<GroupName> EXPECTED_SORTED_LIST = List.of(
            new GroupName("a"),
            new GroupName("A"),
            new GroupName("b"),
            new GroupName("B"),
            new GroupName("bBb"),
            new GroupName("bBB"));


    @Test
    public void isValidName_valid_true() {
        assertTrue(GroupName.isValidName(
            "   120 - []{}()- Pks niUw LK () k    "));
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
        assertEquals(testing, testing);
        assertEquals(testing, eqs);
        assertNotEquals(testing, diff);
        assertNotEquals(testing, unrelated);
    }


    @Test
    public void compareToTest() {
        ArrayList<GroupName> toSort = new ArrayList<>(LIST_TO_SORT);
        toSort.sort(Comparator.naturalOrder());
        assertEquals(EXPECTED_SORTED_LIST, toSort);
    }
}
