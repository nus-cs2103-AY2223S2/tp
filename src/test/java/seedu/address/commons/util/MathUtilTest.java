package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;

class MathUtilTest {

    @Test
    public void index_integers_samePairWithIndex() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Pair<Integer, Integer>> indexedIntegers = MathUtil.<Integer>indexObjects(integers);
        indexedIntegers.forEach(x -> assertTrue(x.getKey() + 1 == x.getValue()));
    }

    @Test
    public void index_strings_samePairWithIndex() {
        List<String> strings = List.of("G. H Hardy", "John Littlewood", "Alan Turing", "David Hilbert");
        List<Pair<Integer, String>> indexedStrings = MathUtil.<String>indexObjects(strings);
        for (int i = 0; i < strings.size(); i++) {
            assertEquals(indexedStrings.get(i).getKey(), i);
            assertEquals(indexedStrings.get(i).getValue(), strings.get(i));
        }
    }

    @Test
    public void index_stringRepeats_samePair() {
        List<String> strings = List.of("G. H Hardy",
            "Alan Turing", "John Littlewood", "Alan Turing", "David Hilbert");
        List<Pair<Integer, String>> indexedStrings = MathUtil.<String>indexObjects(strings);
        for (int i = 0; i < strings.size(); i++) {
            assertEquals(indexedStrings.get(i).getKey(), i);
            assertEquals(indexedStrings.get(i).getValue(), strings.get(i));
        }
    }



}
