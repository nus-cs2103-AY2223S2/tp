package seedu.address.logic.aggregatefunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MaxCountTest {

    private static final String SAMPLE_DESCRIPTION = "sample description";

    @Test
    public void getResult_emptyList_returns0() {
        MaxCount<Integer, Integer> maxCount = new MaxCount<>(
                new ArrayList<>(), SAMPLE_DESCRIPTION, num -> num);
        assertEquals(maxCount.getResult(), "0");
    }

    @Test
    public void getResult_nonEmptyList_returnsSize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Table");
        list.add("Banana");
        list.add("Cake");
        list.add("Box");
        // Group items by first letter
        MaxCount<String, Character> count = new MaxCount<>(
                list, SAMPLE_DESCRIPTION, string -> string.charAt(0));
        assertEquals(count.getResult(), "2");
    }

}
