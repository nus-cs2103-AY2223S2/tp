package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableMap;

public class VaxTypeStorageTest {
    private static final VaxType TYPE_1 = new VaxType("UNCHI",
            VaxType.DEFAULT_GROUP_SET,
            VaxType.DEFAULT_MIN_AGE,
            VaxType.DEFAULT_MAX_AGE,
            VaxType.DEFAULT_MIN_SPACING,
            VaxType.DEFAULT_ALLERGY_REQS,
            VaxType.DEFAULT_HISTORY_REQS);


    @Test
    public void asUnmodifiableObservableMapTest() {
        VaxTypeStorage storage = new VaxTypeStorage();
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
}
