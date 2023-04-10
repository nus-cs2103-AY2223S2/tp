package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableMap;
import seedu.vms.commons.exceptions.LimitExceededException;
import seedu.vms.model.GroupName;
import seedu.vms.testutil.SampleVaxTypeData;

public class VaxTypeManagerTest {
    private static final VaxType TYPE_1 = new VaxType(
            SampleVaxTypeData.NAME_1,
            SampleVaxTypeData.GROUPS_1,
            SampleVaxTypeData.MIN_AGE_1,
            SampleVaxTypeData.MAX_AGE_1,
            SampleVaxTypeData.INGREDIENTS_1,
            SampleVaxTypeData.HISTORY_REQS_1);


    @Test
    public void asUnmodifiableObservableMapTest() throws Exception {
        VaxTypeManager storage = new VaxTypeManager();
        ObservableMap<String, VaxType> mapView = storage.asUnmodifiableObservableMap();

        // addition link
        storage.add(TYPE_1);
        assertTrue(mapView.containsKey(TYPE_1.getName()));
        assertEquals(TYPE_1, mapView.get(TYPE_1.getName()));

        // deletion link
        storage.remove(TYPE_1.getName());
        assertFalse(mapView.containsKey(TYPE_1.getName()));
        assertEquals(null, mapView.get(TYPE_1.getName()));

        // immutability
        try {
            mapView.put(TYPE_1.getName(), TYPE_1);
        } catch (UnsupportedOperationException unsupEx) {
            // expected exception
            return;
        }
        fail("Exception not thrown");
    }


    @Test
    public void add_limitReached_exceptionThrown() throws Exception {
        int setLimit = 5;
        VaxTypeManager manager = new VaxTypeManager(setLimit);

        addToManager(manager, setLimit);

        assertThrows(LimitExceededException.class, () -> manager.add(new VaxType(
                new GroupName(String.valueOf(setLimit)),
                SampleVaxTypeData.GROUPS_1,
                SampleVaxTypeData.MIN_AGE_1,
                SampleVaxTypeData.MAX_AGE_1,
                SampleVaxTypeData.INGREDIENTS_1,
                SampleVaxTypeData.HISTORY_REQS_1)));
    }


    @Test
    public void resetData_validReset() throws Exception {
        int setLimit = 5;
        VaxTypeManager manager = new VaxTypeManager(setLimit);
        addToManager(manager, setLimit);

        VaxTypeManager otherManager = new VaxTypeManager(setLimit);
        otherManager.resetData(manager);

        assertEquals(manager.asUnmodifiableObservableMap(), otherManager.asUnmodifiableObservableMap());
    }


    @Test
    public void resetData_limitReached_exceptionThrown() throws Exception {
        int setLimit = 5;
        VaxTypeManager manager = new VaxTypeManager(setLimit);
        addToManager(manager, setLimit);

        assertThrows(LimitExceededException.class,
                () -> new VaxTypeManager(setLimit - 1).resetData(manager));
    }


    @Test
    public void containsCheck() throws Exception {
        VaxTypeManager storage = new VaxTypeManager();
        assertFalse(storage.contains(TYPE_1.getName()));

        storage.add(TYPE_1);
        assertTrue(storage.contains(TYPE_1.getName()));

        storage.remove(TYPE_1.getName());
        assertFalse(storage.contains(TYPE_1.getName()));
    }


    @Test
    public void getTest() throws Exception {
        VaxTypeManager storage = new VaxTypeManager();
        assertFalse(storage.get(TYPE_1.getName()).isPresent());

        storage.add(TYPE_1);
        assertEquals(TYPE_1, storage.get(TYPE_1.getName()).get());

        storage.remove(TYPE_1.getName());
        assertFalse(storage.get(TYPE_1.getName()).isPresent());
    }


    private void addToManager(VaxTypeManager manager, int number) throws Exception {
        for (int i = 0; i < number; i++) {
            manager.add(new VaxType(
                    new GroupName(String.valueOf(i)),
                    SampleVaxTypeData.GROUPS_1,
                    SampleVaxTypeData.MIN_AGE_1,
                    SampleVaxTypeData.MAX_AGE_1,
                    SampleVaxTypeData.INGREDIENTS_1,
                    SampleVaxTypeData.HISTORY_REQS_1));
        }
    }
}
