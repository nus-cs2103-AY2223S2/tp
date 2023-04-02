package wingman.model.item;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wingman.model.item.exceptions.DuplicateItemException;
import wingman.model.item.exceptions.ItemNotFoundException;
import wingman.testutil.Assert;

public class UniqueListTest {
    private UniqueList<ItemStub> list;

    @BeforeEach
    void setUp() {
        list = new UniqueList<>();
    }

    @Test
    void constructor_shouldCreateEmptyList() {
        UniqueList<ItemStub> list = new UniqueList<>();
        assertTrue(list.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    void fromObservableList_validList_shouldCreateListWithSameElements() {
        ObservableList<ItemStub> observableList = FXCollections.observableArrayList(
                new ItemStub("a"),
                new ItemStub("b")
        );
        UniqueList<ItemStub> list = UniqueList.fromObservableList(observableList);
        assertEquals(2, list.asUnmodifiableObservableList().size());
    }

    @Test
    void fromObservableList_duplicatedList_shouldThrowItemDuplicateException() {
        ObservableList<ItemStub> observableList = FXCollections.observableArrayList(
                new ItemStub("a"),
                new ItemStub("a")
        );
        Assert.assertThrows(DuplicateItemException.class, () ->
                UniqueList.fromObservableList(observableList));
        try {
            UniqueList.fromObservableList(observableList);
        } catch (DuplicateItemException e) {
            assertEquals(ItemStub.class, e.getDuplicatedClass());
        }
    }

    @Test
    void fromObservableList_nullList_shouldThrowNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> UniqueList.fromObservableList(null));
    }

    @Test
    void fromObservableList_nullItem_shouldThrowNullPointerException() {
        ObservableList<ItemStub> observableList = FXCollections.observableArrayList(
                new ItemStub("a"),
                null
        );
        Assert.assertThrows(NullPointerException.class, () ->
                UniqueList.fromObservableList(observableList));
    }

    @Test
    void add_validItem_shouldAddItem() {
        this.list.add(new ItemStub("a"));
        assertEquals(1, this.list.asUnmodifiableObservableList().size());
    }

    @Test
    void add_duplicatedItem_shouldThrowItemDuplicateException() {
        this.list.add(new ItemStub("a"));
        Assert.assertThrows(DuplicateItemException.class, () ->
                this.list.add(new ItemStub("a")));
        try {
            this.list.add(new ItemStub("a"));
        } catch (DuplicateItemException e) {
            assertEquals(ItemStub.class, e.getDuplicatedClass());
        }
    }

    @Test
    void add_nullItem_shouldThrowNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> this.list.add(null));
    }

    @Test
    void contains_validItem_shouldReturnTrue() {
        ItemStub item = new ItemStub("a");
        this.list.add(item);
        assertTrue(this.list.contains(item));
    }

    @Test
    void contains_nullItem_shouldThrowNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> this.list.contains((ItemStub) null));
    }

    @Test
    void setItem_validItem_shouldReplaceItem() {
        ItemStub item = new ItemStub("a");
        this.list.add(item);
        ItemStub newItem = new ItemStub("b");
        this.list.setItem(item, newItem);
        assertTrue(this.list.contains(newItem));
        assertFalse(this.list.contains(item));
    }

    @Test
    void setItem_nullItem_shouldThrowNullPointerException() {
        ItemStub target = new ItemStub("a");
        ItemStub editedItem = new ItemStub("b");
        Assert.assertThrows(NullPointerException.class, () -> this.list.setItem(target,
                null));
        Assert.assertThrows(NullPointerException.class, () -> this.list.setItem(null,
                editedItem));
        Assert.assertThrows(NullPointerException.class, () -> this.list.setItem(null,
                null));
    }

    @Test
    void setItem_itemNotInList_shouldThrowItemNotFoundException() {
        ItemStub target = new ItemStub("a");
        ItemStub editedItem = new ItemStub("b");
        Assert.assertThrows(ItemNotFoundException.class, () -> this.list.setItem(target,
                editedItem));
    }

    @Test
    void setItem_sameItem_shouldNotThrowException() {
        ItemStub item = new ItemStub("a");
        this.list.add(item);
        assertDoesNotThrow(() -> this.list.setItem(item, item));
    }

    @Test
    void setItem_itemAlreadyInList_shouldThrowItemDuplicateException() {
        ItemStub item = new ItemStub("a");
        ItemStub item2 = new ItemStub("b");
        this.list.add(item);
        this.list.add(item2);
        Assert.assertThrows(DuplicateItemException.class, () -> this.list.setItem(item,
                item2));
        try {
            this.list.setItem(item, item2);
        } catch (DuplicateItemException e) {
            assertEquals(ItemStub.class, e.getDuplicatedClass());
        }
    }

    @Test
    void remove_validItem_shouldRemoveItem() {
        ItemStub item = new ItemStub("a");
        this.list.add(item);
        this.list.remove(item);
        assertFalse(this.list.contains(item));
    }

    @Test
    void remove_nullItem_shouldThrowNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> this.list.remove((ItemStub) null));
    }

    @Test
    void remove_itemNotInList_shouldThrowItemNotFoundException() {
        ItemStub item = new ItemStub("a");
        Assert.assertThrows(ItemNotFoundException.class, () -> this.list.remove(item));
    }

    @Test
    void setItems_validItems_shouldReplaceItems() {
        ItemStub item = new ItemStub("a");
        ItemStub item2 = new ItemStub("b");
        this.list.add(item);
        this.list.add(item2);

        ItemStub newItem = new ItemStub("c");
        ItemStub newItem2 = new ItemStub("d");
        ObservableList<ItemStub> items = FXCollections.observableArrayList(
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
        ItemStub item = new ItemStub("a");
        ItemStub item2 = new ItemStub("b");
        this.list.add(item);
        this.list.add(item2);

        ItemStub newItem = new ItemStub("c");
        ItemStub newItem2 = new ItemStub("d");
        UniqueList<ItemStub> items = new UniqueList<>();
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
        ItemStub item = new ItemStub("a");
        ItemStub item2 = new ItemStub("a");
        ObservableList<ItemStub> items = FXCollections.observableArrayList(
                item,
                item2
        );
        assertTrue(UniqueList.itemsHaveDuplicate(items));
    }

    @Test
    void itemsHaveDuplicate_noDuplicate_shouldReturnFalse() {
        ItemStub item = new ItemStub("a");
        ItemStub item2 = new ItemStub("b");
        ObservableList<ItemStub> items = FXCollections.observableArrayList(
                item,
                item2
        );
        assertFalse(UniqueList.itemsHaveDuplicate(items));
    }

    private static class ItemStub implements Item {
        private final String id;

        public ItemStub(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
