package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A utility class for testing {@code Object} methods.
 */
public class ObjectUtil {

    /**
     * A generic test for the {@code Object#equals(Object)} method.<p>
     *
     * The following tests are done:<p>
     * 1. Assert that {@code originalObject} is equal to {@code sameValueObject}<p>
     * 2. Assert that {@code originalObject} is equal to itself<p>
     * 3. Assert that {@code originalObject} is not equal to null<p>
     * 4. Assert that {@code originalObject} is not equal to {@code differentTypeObject}<p>
     * 5. Assert that {@code originalObject} is not equal to any object in {@code differentValueObjects}
     *
     * @param <T> The type to be tested.
     * @param originalObject The original object to be checked for equality with.
     * @param sameValueObject A different object that has the same value as {@code originalObject}.
     * @param differentTypeObject An object of a different type than {@code originalObject}.
     * @param differentValueObjects Objects that have some value that is different from {@code originalObject}.
     */
    @SafeVarargs
    public static <T> void testEquals(T originalObject, T sameValueObject, Object differentTypeObject,
            T... differentValueObjects) {

        // same values -> returns true
        assertTrue(originalObject.equals(sameValueObject));

        // same object -> returns true
        assertTrue(originalObject.equals(originalObject));

        // null -> returns false
        assertFalse(originalObject.equals(null));

        // different types -> returns false
        assertFalse(originalObject.equals(differentTypeObject));

        // different values -> returns false
        for (T diffValueObj : differentValueObjects) {
            assertFalse(originalObject.equals(diffValueObj));
        }
    }

}
