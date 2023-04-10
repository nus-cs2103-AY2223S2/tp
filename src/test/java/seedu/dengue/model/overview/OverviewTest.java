package seedu.dengue.model.overview;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OverviewTest {
    @Test
    public void enumList_normalList_success() {
        List<String[]> startArray = List.of(new String[]{"name 1", "count 1"},
                new String[]{"name 2", "count 2"},
                new String[]{"name 3", "count 3"});
        List<String[]> endArray = Overview.enumList(startArray);

        List<String[]> matchArray = List.of(new String[]{"1", "name 1", "count 1"},
                new String[]{"2", "name 2", "count 2"},
                new String[]{"3", "name 3", "count 3"});

        for (int i = 0; i < matchArray.size(); i++) {
            assertArrayEquals(matchArray.get(i), endArray.get(i));
        }
    }

    @Test
    public void enumList_emptyList_success() {
        List<String[]> startArray = new ArrayList<>();
        List<String[]> endArray = Overview.enumList(startArray);

        List<String[]> matchArray = new ArrayList<>();

        assertEquals(matchArray, endArray);
    }
}
