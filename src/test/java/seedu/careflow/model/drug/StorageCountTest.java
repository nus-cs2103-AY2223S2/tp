package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.patient.Phone;

public class StorageCountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StorageCount(null));
    }

    @Test
    public void constructor_invalidStorageCount_throwsIllegalArgumentException() {
        String invalidStorageCount = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidStorageCount));
    }

    @Test
    public void isValidStorageCount() {
        // null phone number
        assertThrows(NullPointerException.class, () -> StorageCount.isValidStorageCount(null));

        // invalid storage count
        assertFalse(StorageCount.isValidStorageCount("")); // empty string
        assertFalse(StorageCount.isValidStorageCount(" ")); // spaces only
        assertFalse(StorageCount.isValidStorageCount(" 91")); // digits with space
        assertFalse(StorageCount.isValidStorageCount("string")); // non-numeric
        assertFalse(StorageCount.isValidStorageCount("123+")); // with special character
        assertFalse(StorageCount.isValidStorageCount("9011p041")); // alphabets within digits
        assertFalse(StorageCount.isValidStorageCount("-123")); // -ve value
        assertFalse(StorageCount.isValidStorageCount("500")); // exceed max limit of storage count(>499)
        assertFalse(StorageCount.isValidStorageCount("1234"));

        // valid storage count
        assertTrue(StorageCount.isValidStorageCount("0")); // exactly 1 numbers
        assertTrue(StorageCount.isValidStorageCount("1")); // exactly 1 numbers
        assertTrue(StorageCount.isValidStorageCount("49")); // with 2 digits
        assertTrue(StorageCount.isValidStorageCount("499")); // max limit of storage count
    }

    @Test
    public void equals() {
        StorageCount storageCount = new StorageCount("78");
        // null -> returns false
        assertFalse(storageCount.equals(null));

        // same object -> returns true
        assertTrue(storageCount.equals(storageCount));

        // same values -> returns true
        assertTrue(storageCount.equals(new StorageCount("78")));

        // different values -> return false
        assertFalse(storageCount.equals("79"));
    }
}
