package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the service locator class {@link GetUtils}.
 */
public class GetUtilsTest {
    @BeforeEach
    void setUp() {
        GetUtils.clear();
    }

    @Test
    void get_nullSupplied_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> GetUtils.get(null));
    }

    @Test
    void get_notRegistered_shouldThrowGetException() {
        assertThrows(GetUtils.GetException.class, () -> GetUtils.get(Stub.class));
    }

    @Test
    void put_nullSupplied_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> GetUtils.put(null, new Stub()));
        assertThrows(NullPointerException.class, () -> GetUtils.put(Stub.class, null));
    }

    @Test
    void put_alreadyPut_shouldThrowGetException() {
        GetUtils.put(Stub.class, new Stub());
        assertThrows(GetUtils.GetException.class, () -> GetUtils.put(Stub.class, new Stub()));
        GetUtils.clear();
        GetUtils.putLazy(Stub.class, Stub::new);
        assertThrows(GetUtils.GetException.class, () -> GetUtils.put(Stub.class, new Stub()));
    }

    @Test
    void putForce_alreadyPut_shouldReplaceOriginal() {
        Stub stub1 = new Stub();
        Stub stub2 = new Stub();
        assertNotEquals(stub1, stub2);
        GetUtils.put(Stub.class, stub1);
        assertEquals(stub1, GetUtils.get(Stub.class));
        assertNotEquals(stub2, GetUtils.get(Stub.class));

        assertDoesNotThrow(() -> GetUtils.putForce(Stub.class, stub2));

        assertNotEquals(stub1, GetUtils.get(Stub.class));
        assertEquals(stub2, GetUtils.get(Stub.class));
    }

    @Test
    void putLazy_nullSupplied_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> GetUtils.putLazy(null, Stub::new));
        assertThrows(NullPointerException.class, () -> GetUtils.putLazy(Stub.class, null));
    }

    @Test
    void putLazy_thenGet_shouldReturnNewStub() {
        GetUtils.putLazy(Stub.class, Stub::new);
        assertDoesNotThrow(() -> GetUtils.get(Stub.class));
        assertNotNull(GetUtils.get(Stub.class));
    }

    @Test
    void putLazy_alreadyPut_shouldThrowGetException() {
        GetUtils.putLazy(Stub.class, Stub::new);
        assertThrows(GetUtils.GetException.class, () -> GetUtils.putLazy(Stub.class, Stub::new));
        GetUtils.clear();
        GetUtils.put(Stub.class, new Stub());
        assertThrows(GetUtils.GetException.class, () -> GetUtils.putLazy(Stub.class, Stub::new));
    }

    @Test
    void putLazyForce_alreadyPut_shouldReplacePrevious() {
        Stub stub = new Stub();
        GetUtils.put(Stub.class, stub);

        assertDoesNotThrow(() -> GetUtils.putLazyForce(Stub.class, Stub::new));
        assertNotEquals(stub, GetUtils.get(Stub.class));
    }

    private static class Stub {
        public Stub() {
        }
    }
}
