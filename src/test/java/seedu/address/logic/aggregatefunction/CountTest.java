package seedu.address.logic.aggregatefunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CountTest {

    @Test
    public void getResult_returnsClassName() {
        Count<Integer> count = new Count<>(new ArrayList<>(), Integer.class);
        assertEquals(count.getDescription(), String.format(Count.DESCRIPTION, Integer.class.getSimpleName()));
    }

    @Test
    public void getResult_emptyList_returns0() {
        Count<Integer> count = new Count<>(new ArrayList<>(), Integer.class);
        assertEquals(count.getResult(), "0");
    }

    @Test
    public void getResult_nonEmptyList_returnsSize() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(3.14);
        list.add(2.71);
        list.add(0.0);
        list.add(Double.MAX_VALUE);
        Count<Double> count = new Count<>(list, Double.class);
        assertEquals(count.getResult(), "4");
    }
}
