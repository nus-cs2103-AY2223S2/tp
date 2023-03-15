package seedu.address.model.shared;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IdTest {

    @Test
    void constructor_generateValidUuid() {
        Id id = new Id();
        assertFalse(Id.isInValidId(id.toString()), "Generated UUID should be valid");
    }

    @Test
    void constructor_generateUuidFromString() {
        String uuid = "d9cc62ca-0f19-11ec-82a8-0242ac130003";
        Id id = new Id(uuid);
        assertEquals(uuid, id.toString(), "UUID should be equal to the string used to create it");
    }

    @Test
    void isInValidId_invalidUuid() {
        String invalidUuid = "not a valid uuid";
        assertTrue(Id.isInValidId(invalidUuid), "Invalid UUID should not be valid");
    }

    @Test
    void isInValidId_validUuid() {
        String validUuid = "d9cc62ca-0f19-11ec-82a8-0242ac130003";
        assertFalse(Id.isInValidId(validUuid), "Valid UUID should be valid");
    }

    @Test
    void equals_sameObject() {
        Id id = new Id();
        assertEquals(id, id, "Object should be equal to itself");
    }

    @Test
    void equals_null() {
        Id id = new Id();
        assertNotEquals(id, null, "Object should not be equal to null");
    }

    @Test
    void equals_differentClass() {
        Id id = new Id();
        assertNotEquals(id, new Object(), "Object should not be equal to object of a different class");
    }

    @Test
    void equals_sameValue() {
        Id id1 = new Id("d9cc62ca-0f19-11ec-82a8-0242ac130003");
        Id id2 = new Id("d9cc62ca-0f19-11ec-82a8-0242ac130003");
        assertEquals(id1, id2, "Objects with same value should be equal");
    }

    @Test
    void hashCode_sameValue() {
        Id id1 = new Id("d9cc62ca-0f19-11ec-82a8-0242ac130003");
        Id id2 = new Id("d9cc62ca-0f19-11ec-82a8-0242ac130003");
        assertEquals(id1.hashCode(), id2.hashCode(), "Objects with same value should have the same hash code");
    }
}
