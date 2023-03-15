package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableMap;
import seedu.vms.testutil.SampleVaxTypeData;

public class VaxTypeManagerTest {
    private static final VaxType TYPE_1 = new VaxType(
            SampleVaxTypeData.NAME_1,
            SampleVaxTypeData.GROUPS_1,
            SampleVaxTypeData.MIN_AGE_1,
            SampleVaxTypeData.MAX_AGE_1,
            SampleVaxTypeData.ALLERGY_REQS_1,
            SampleVaxTypeData.HISTORY_REQS_1);


    @Test
    public void asUnmodifiableObservableMapTest() {
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
    public void containsCheck() {
        VaxTypeManager storage = new VaxTypeManager();
        assertFalse(storage.contains(TYPE_1.getName()));

        storage.add(TYPE_1);
        assertTrue(storage.contains(TYPE_1.getName()));

        storage.remove(TYPE_1.getName());
        assertFalse(storage.contains(TYPE_1.getName()));
    }


    @Test
    public void getTest() {
        VaxTypeManager storage = new VaxTypeManager();
        assertFalse(storage.get(TYPE_1.getName()).isPresent());

        storage.add(TYPE_1);
        assertEquals(TYPE_1, storage.get(TYPE_1.getName()).get());

        storage.remove(TYPE_1.getName());
        assertFalse(storage.get(TYPE_1.getName()).isPresent());
    }
}
