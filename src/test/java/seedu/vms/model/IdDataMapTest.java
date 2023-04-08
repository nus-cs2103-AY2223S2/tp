package seedu.vms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.commons.exceptions.LimitExceededException;

public class IdDataMapTest {
    private static final int TESTING_LIMIT = 10;

    private static final List<Integer> RANDOM_VALUES = List.of(5, 6, 0, 7, 8, 9, 1, 3, 4, 2);

    private IdDataMap<Integer> idMap;


    @BeforeEach
    public void initializeIdMap() {
        idMap = new IdDataMap<>(TESTING_LIMIT);
    }


    @Test
    public void add_valueWithinLimit_valueAdded() {
        for (int i = 0; i < TESTING_LIMIT; i++) {
            idMap.add(i);
        }
        for (int i = 0; i < TESTING_LIMIT; i++) {
            assertTrue(idMap.contains(i));
        }
    }


    @Test
    public void add_valueOverLimit_exceptionThrown() {
        for (int i = 0; i < TESTING_LIMIT; i++) {
            idMap.add(i);
        }
        assertThrows(LimitExceededException.class,
                () -> idMap.add(TESTING_LIMIT));
    }


    @Test
    public void add_dataWithinLimit_valueAdded() {
        for (int i = 0; i < TESTING_LIMIT; i++) {
            idMap.add(new IdData<>(true, i, i));
        }
        for (int i = 0; i < TESTING_LIMIT; i++) {
            assertTrue(idMap.contains(i));
        }
    }


    @Test
    public void add_dataOverLimit_exceptionThrown() {
        assertThrows(LimitExceededException.class,
                () -> idMap.add(new IdData<>(true, TESTING_LIMIT, TESTING_LIMIT)));
    }


    @Test
    public void add_mixedUnordered() {
        /* 0 */ idMap.add(new IdData<>(true, RANDOM_VALUES.get(0), RANDOM_VALUES.get(0)));
        /* 1 */ idMap.add(RANDOM_VALUES.get(1));
        /* 2 */ idMap.add(new IdData<>(true, RANDOM_VALUES.get(2), RANDOM_VALUES.get(2)));
        /* 3 */ idMap.add(RANDOM_VALUES.get(3));
        /* 4 */ idMap.add(RANDOM_VALUES.get(4));
        /* 5 */ idMap.add(RANDOM_VALUES.get(5));
        /* 6 */ idMap.add(RANDOM_VALUES.get(6));
        /* 7 */ idMap.add(new IdData<>(true, RANDOM_VALUES.get(7), RANDOM_VALUES.get(7)));
        /* 8 */ idMap.add(RANDOM_VALUES.get(8));
        /* 9 */ idMap.add(RANDOM_VALUES.get(9));
        for (int i = 0; i < TESTING_LIMIT; i++) {
            assertEquals(i, idMap.get(i).getValue());
        }

        // limit
        assertThrows(LimitExceededException.class,
                () -> idMap.add(new IdData<>(true, TESTING_LIMIT, TESTING_LIMIT)));
    }


    @Test
    public void addAndDeleteTest() {
        /*
         * Test ensures that ID increases on an add operation after the recently added
         * element was deleted immediately
         */

        // first so 0
        int idDel = idMap.add(0).getId();
        idMap.remove(idDel);

        // second so 1
        int idNext = idMap.add(2).getId();

        assertEquals(0, idDel);
        assertEquals(1, idNext);
    }


    @Test
    public void setValues_withinLimit_valuesAdded() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < TESTING_LIMIT; i++) {
            values.add(i);
        }
        idMap.setValues(values);
        for (int i = 0; i < TESTING_LIMIT; i++) {
            assertEquals(i, idMap.get(i).getValue());
        }
    }


    @Test
    public void setValues_overLimit_exceptionThrown() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < TESTING_LIMIT + 1; i++) {
            values.add(i);
        }
        assertThrows(LimitExceededException.class, () -> idMap.setValues(values));
    }


    @Test
    public void setDatas_withinLimit_valuesAdded() {
        ArrayList<IdData<Integer>> datas = formRandDataList();
        idMap.setDatas(datas);
        for (int i = 0; i < TESTING_LIMIT; i++) {
            assertEquals(i, idMap.get(i).getValue());
        }
    }


    @Test
    public void set() {
        int initial = 0;
        int change = 1;
        idMap.add(initial);
        idMap.set(0, change);
        assertEquals(change, idMap.get(0).getValue());
    }


    private ArrayList<IdData<Integer>> formRandDataList() {
        ArrayList<IdData<Integer>> datas = new ArrayList<>();
        for (int i = 0; i < TESTING_LIMIT; i++) {
            datas.add(new IdData<Integer>(true, RANDOM_VALUES.get(i), RANDOM_VALUES.get(i)));
        }
        return datas;
    }
}
