package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.DuplicateItemException;
import seedu.address.model.exceptions.ItemNotFoundException;

class UniqueItemListTest {
    private UniqueItemList<ItemStub> itemList;
    private ItemStub item1;
    private ItemStub item2;
    private ItemStub item3;

    @BeforeEach
    public void setUp() {
        itemList = new UniqueItemList<>();
        item1 = new ItemStub("item1");
        item2 = new ItemStub("item2");
        item3 = new ItemStub("item3");
    }

    @Test
    public void contains_itemExists() {
        itemList.add(item1);
        assertTrue(itemList.contains(item1));
    }

    @Test
    public void contains_itemDoesNotExist() {
        assertFalse(itemList.contains(item1));
    }

    @Test
    public void add_success() {
        itemList.add(item1);
        assertTrue(itemList.contains(item1));
    }

    @Test
    public void add_duplicateItem() {
        itemList.add(item1);
        assertThrows(DuplicateItemException.class, () -> itemList.add(item1));
    }

    @Test
    public void setItem_success() {
        itemList.add(item1);
        ItemStub editedItem = new ItemStub("item1-edited");
        itemList.setItem(item1, editedItem);
        assertTrue(itemList.contains(editedItem));
    }

    @Test
    public void setItem_itemNotFound() {
        assertThrows(ItemNotFoundException.class, () -> itemList.setItem(item1, item2));
    }

    @Test
    public void setItem_duplicateItem() {
        itemList.add(item1);
        itemList.add(item2);
        assertThrows(DuplicateItemException.class, () -> itemList.setItem(item1, item2));
    }

    @Test
    public void remove_success() {
        itemList.add(item1);
        itemList.remove(item1);
        assertFalse(itemList.contains(item1));
    }

    @Test
    public void remove_itemNotFound() {
        assertThrows(ItemNotFoundException.class, () -> itemList.remove(item1));
    }

    @Test
    public void setItems_success() {
        itemList.setItems(Arrays.asList(item1, item2, item3));
        assertTrue(itemList.contains(item1));
        assertTrue(itemList.contains(item2));
        assertTrue(itemList.contains(item3));
    }

    @Test
    public void setItems_duplicateItems() {
        assertThrows(DuplicateItemException.class, () -> itemList.setItems(Arrays.asList(item1, item1)));
    }

}
