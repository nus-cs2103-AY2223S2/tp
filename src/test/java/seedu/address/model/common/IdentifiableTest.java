package seedu.address.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IdentifiableTest {

    @Test
    void isSame_sameObject_returnsTrue() {
        IdentifiableStub1 a = new IdentifiableStub1("a");
        assertTrue(Identifiable.isSame(a, a));
    }

    @Test
    void isSame_sameId_returnsTrue() {
        IdentifiableStub1 a = new IdentifiableStub1("a");
        IdentifiableStub1 b = new IdentifiableStub1("a");
        assertTrue(Identifiable.isSame(a, b));
    }

    @Test
    void isSame_differentId_returnsFalse() {
        IdentifiableStub1 a = new IdentifiableStub1("a");
        IdentifiableStub1 b = new IdentifiableStub1("b");
        assertFalse(Identifiable.isSame(a, b));
    }

    @Test
    void isSame_differentClass_returnsFalse() {
        IdentifiableStub1 a = new IdentifiableStub1("a");
        IdentifiableStub2 b = new IdentifiableStub2("a");
        assertFalse(Identifiable.isSame(a, b));
    }


    private static class IdentifiableStub1 implements Identifiable {
        private final String id;

        public IdentifiableStub1(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    private static class IdentifiableStub2 implements Identifiable {
        private final String id;

        public IdentifiableStub2(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
