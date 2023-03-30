package wingman.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    void isSame_sameObject_returnsTrue() {
        ItemStub1 a = new ItemStub1("a");
        assertTrue(Item.isSame(a, a));
    }

    @Test
    void isSame_sameId_returnsTrue() {
        ItemStub1 a = new ItemStub1("a");
        ItemStub1 b = new ItemStub1("a");
        assertTrue(Item.isSame(a, b));
    }

    @Test
    void isSame_differentId_returnsFalse() {
        ItemStub1 a = new ItemStub1("a");
        ItemStub1 b = new ItemStub1("b");
        assertFalse(Item.isSame(a, b));
    }

    @Test
    void isSame_differentClass_returnsFalse() {
        ItemStub1 a = new ItemStub1("a");
        ItemStub2 b = new ItemStub2("a");
        assertFalse(Item.isSame(a, b));
    }


    private static class ItemStub1 implements Item {
        private final String id;

        public ItemStub1(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    private static class ItemStub2 implements Item {
        private final String id;

        public ItemStub2(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
