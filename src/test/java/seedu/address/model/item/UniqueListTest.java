package seedu.address.model.item;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.exceptions.ItemDuplicateException;
import seedu.address.model.item.exceptions.ItemNotFoundException;

public class UniqueListTest {
    private UniqueList<IdentifiableStub> list;

    @BeforeEach
    void setUp() {
        list = new UniqueList<>();
    }

    @Test
    void constructor_shouldCreateEmptyList() {
        UniqueList<IdentifiableStub> list = new UniqueList<>();
        assertTrue(list.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    void fromObservableList_validList_shouldCreateListWithSameElements() {
        ObservableList<IdentifiableStub> observableList = FXCollections.observableArrayList(
                new IdentifiableStub("a"),
                new IdentifiableStub("b")
        );
        UniqueList<IdentifiableStub> list = UniqueList.fromObservableList(observableList);
        assertEquals(2, list.asUnmodifiableObservableList().size());
    }

    @Test
    void fromObservableList_duplicatedList_shouldThrowItemDuplicateException() {
        ObservableList<IdentifiableStub> observableList = FXCollections.observableArrayList(
                new IdentifiableStub("a"),
                new IdentifiableStub("a")
        );
        assertThrows(ItemDuplicateException.class, () ->
                UniqueList.fromObservableList(observableList));
        try {
            UniqueList.fromObservableList(observableList);
        } catch (ItemDuplicateException e) {
            assertEquals(IdentifiableStub.class, e.getDuplicatedClass());
        }
    }

    @Test
    void fromObservableList_nullList_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueList.fromObservableList(null));
    }

    @Test
    void fromObservableList_nullItem_shouldThrowNullPointerException() {
        ObservableList<IdentifiableStub> observableList = FXCollections.observableArrayList(
                new IdentifiableStub("a"),
                null
        );
        assertThrows(NullPointerException.class, () ->
                UniqueList.fromObservableList(observableList));
    }

    @Test
    void add_validItem_shouldAddItem() {
        this.list.add(new IdentifiableStub("a"));
        assertEquals(1, this.list.asUnmodifiableObservableList().size());
    }

    @Test
    void add_duplicatedItem_shouldThrowItemDuplicateException() {
        this.list.add(new IdentifiableStub("a"));
        assertThrows(ItemDuplicateException.class, () ->
                this.list.add(new IdentifiableStub("a")));
        try {
            this.list.add(new IdentifiableStub("a"));
        } catch (ItemDuplicateException e) {
            assertEquals(IdentifiableStub.class, e.getDuplicatedClass());
        }
    }

    @Test
    void add_nullItem_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> this.list.add(null));
    }

    @Test
    void contains_validItem_shouldReturnTrue() {
        IdentifiableStub item = new IdentifiableStub("a");
        this.list.add(item);
        assertTrue(this.list.contains(item));
    }

    @Test
    void contains_nullItem_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> this.list.contains(null));
    }

    @Test
    void setItem_validItem_shouldReplaceItem() {
        IdentifiableStub item = new IdentifiableStub("a");
        this.list.add(item);
        IdentifiableStub newItem = new IdentifiableStub("b");
        this.list.setItem(item, newItem);
        assertTrue(this.list.contains(newItem));
        assertFalse(this.list.contains(item));
    }

    @Test
    void setItem_nullItem_shouldThrowNullPointerException() {
        IdentifiableStub target = new IdentifiableStub("a");
        IdentifiableStub editedItem = new IdentifiableStub("b");
        assertThrows(NullPointerException.class, () -> this.list.setItem(target,
                null));
        assertThrows(NullPointerException.class, () -> this.list.setItem(null,
                editedItem));
        assertThrows(NullPointerException.class, () -> this.list.setItem(null,
                null));
    }

    @Test
    void setItem_itemNotInList_shouldThrowItemNotFoundException() {
        IdentifiableStub target = new IdentifiableStub("a");
        IdentifiableStub editedItem = new IdentifiableStub("b");
        assertThrows(ItemNotFoundException.class, () -> this.list.setItem(target,
                editedItem));
    }

    @Test
    void setItem_sameItem_shouldNotThrowException() {
        IdentifiableStub item = new IdentifiableStub("a");
        this.list.add(item);
        assertDoesNotThrow(() -> this.list.setItem(item, item));
    }

    @Test
    void setItem_itemAlreadyInList_shouldThrowItemDuplicateException() {
        IdentifiableStub item = new IdentifiableStub("a");
        IdentifiableStub item2 = new IdentifiableStub("b");
        this.list.add(item);
        this.list.add(item2);
        assertThrows(ItemDuplicateException.class, () -> this.list.setItem(item,
                item2));
        try {
            this.list.setItem(item, item2);
        } catch (ItemDuplicateException e) {
            assertEquals(IdentifiableStub.class, e.getDuplicatedClass());
        }
    }

    @Test
    void remove_validItem_shouldRemoveItem() {
        IdentifiableStub item = new IdentifiableStub("a");
        this.list.add(item);
        this.list.remove(item);
        assertFalse(this.list.contains(item));
    }

    @Test
    void remove_nullItem_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> this.list.remove(null));
    }

    @Test
    void remove_itemNotInList_shouldThrowItemNotFoundException() {
        IdentifiableStub item = new IdentifiableStub("a");
        assertThrows(ItemNotFoundException.class, () -> this.list.remove(item));
    }

    @Test
    void setItems_validItems_shouldReplaceItems() {
        IdentifiableStub item = new IdentifiableStub("a");
        IdentifiableStub item2 = new IdentifiableStub("b");
        this.list.add(item);
        this.list.add(item2);

        IdentifiableStub newItem = new IdentifiableStub("c");
        IdentifiableStub newItem2 = new IdentifiableStub("d");
        ObservableList<IdentifiableStub> items = FXCollections.observableArrayList(
                newItem,
                newItem2
        );

        this.list.setItems(items);

        assertTrue(this.list.contains(newItem));
        assertTrue(this.list.contains(newItem2));
        assertFalse(this.list.contains(item));
        assertFalse(this.list.contains(item2));
    }

    @Test
    void setItems_uniqueList_shouldReplaceItems() {
        IdentifiableStub item = new IdentifiableStub("a");
        IdentifiableStub item2 = new IdentifiableStub("b");
        this.list.add(item);
        this.list.add(item2);

        IdentifiableStub newItem = new IdentifiableStub("c");
        IdentifiableStub newItem2 = new IdentifiableStub("d");
        UniqueList<IdentifiableStub> items = new UniqueList<>();
        items.add(newItem);
        items.add(newItem2);

        this.list.setItems(items);

        assertTrue(this.list.contains(newItem));
        assertTrue(this.list.contains(newItem2));
        assertFalse(this.list.contains(item));
        assertFalse(this.list.contains(item2));
    }

    @Test
    void itemsHaveDuplicate_hasDuplicate_shouldReturnTrue() {
        IdentifiableStub item = new IdentifiableStub("a");
        IdentifiableStub item2 = new IdentifiableStub("a");
        ObservableList<IdentifiableStub> items = FXCollections.observableArrayList(
                item,
                item2
        );
        assertTrue(UniqueList.itemsHaveDuplicate(items));
    }

    @Test
    void itemsHaveDuplicate_noDuplicate_shouldReturnFalse() {
        IdentifiableStub item = new IdentifiableStub("a");
        IdentifiableStub item2 = new IdentifiableStub("b");
        ObservableList<IdentifiableStub> items = FXCollections.observableArrayList(
                item,
                item2
        );
        assertFalse(UniqueList.itemsHaveDuplicate(items));
    }

    private static class IdentifiableStub implements Identifiable {
        private final String id;

        public IdentifiableStub(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
