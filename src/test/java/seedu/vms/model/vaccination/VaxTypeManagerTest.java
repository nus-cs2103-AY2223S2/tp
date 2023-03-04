package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableMap;

public class VaxTypeManagerTest {
    private static final VaxType TYPE_1 = new VaxType("UNCHI",
            VaxType.DEFAULT_GROUP_SET,
            VaxType.DEFAULT_MIN_AGE,
            VaxType.DEFAULT_MAX_AGE,
            VaxType.DEFAULT_MIN_SPACING,
            VaxType.DEFAULT_ALLERGY_REQS,
            VaxType.DEFAULT_HISTORY_REQS);


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
