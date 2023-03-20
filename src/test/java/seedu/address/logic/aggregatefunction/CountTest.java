package seedu.address.logic.aggregatefunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class CountTest {

    private static final String SAMPLE_DESCRIPTION = "sample description";
    @Test
    public void getDescription_returnsDescription() {
        Count<Integer> count = new Count<>(new ArrayList<>(), SAMPLE_DESCRIPTION);
        assertEquals(count.getDescription(), SAMPLE_DESCRIPTION);
    }

    @Test
    public void getResult_emptyList_returns0() {
        Count<Integer> count = new Count<>(new ArrayList<>(), SAMPLE_DESCRIPTION);
        assertEquals(count.getResult(), "0");
    }

    @Test
    public void getResult_nonEmptyList_returnsSize() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(3.14);
        list.add(2.71);
        list.add(0.0);
        list.add(Double.MAX_VALUE);
        Count<Double> count = new Count<>(list, SAMPLE_DESCRIPTION);
        assertEquals(count.getResult(), "4");
    }

    @Test
    public void withPredicate_returnsCorrectResult() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(7);
        list.add(11);
        list.add(2);
        list.add(6);
        Predicate<Integer> predicate = (num -> num > 5);
        Count<Integer> count = new Count<>(list, SAMPLE_DESCRIPTION);
        assertEquals(count.with(predicate).getResult(), "3");
    }
}
