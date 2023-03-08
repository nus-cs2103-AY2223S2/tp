package seedu.address.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class RepositoryModelManagerTest {

    private RepositoryModelManager<ItemStub> itemManager;
    private ItemStub item1;
    private ItemStub item2;

    @BeforeEach
    public void setUp() {
        itemManager = new RepositoryModelManager<>();
        item1 = new ItemStub("item1");
        item2 = new ItemStub("item2");
    }

    @Test
    public void hasItem_itemExists() {
        itemManager.addItem(item1);
        assertTrue(itemManager.hasItem(item1));
    }

    @Test
    public void hasItem_itemDoesNotExist() {
        assertFalse(itemManager.hasItem(item1));
    }

    @Test
    public void deleteItem_success() {
        itemManager.addItem(item1);
        itemManager.deleteItem(item1);
        assertFalse(itemManager.hasItem(item1));
    }

    @Test
    public void addItem_success() {
        itemManager.addItem(item1);
        assertTrue(itemManager.hasItem(item1));
    }

    @Test
    public void setItem_success() {
        itemManager.addItem(item1);
        itemManager.setItem(item1, item2);
        assertFalse(itemManager.hasItem(item1));
        assertTrue(itemManager.hasItem(item2));
    }

    @Test
    public void getFilteredItemList() {
        assertNotNull(itemManager.getFilteredItemList());
    }

    @Test
    public void updateFilteredItemList_success() {
        Predicate<ItemStub> predicate = (item) -> item.getValue().equals("item1");
        itemManager.addItem(item1);
        itemManager.addItem(item2);
        itemManager.updateFilteredItemList(predicate);
        assertEquals(1, itemManager.getFilteredItemList().size());
        assertTrue(itemManager.getFilteredItemList().contains(item1));
    }

    @Test
    public void showAllItems_success() {
        Predicate<ItemStub> predicate = (item) -> item.getValue().equals("item1");
        itemManager.addItem(item1);
        itemManager.addItem(item2);
        itemManager.updateFilteredItemList(predicate);
        itemManager.showAllItems();
        assertEquals(2, itemManager.getFilteredItemList().size());
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(itemManager, itemManager);
    }

    @Test
    public void equals_sameValues_true() {
        Repository<ItemStub> otherRepo = new Repository<>();
        otherRepo.addItem(item1);
        otherRepo.addItem(item2);

        RepositoryModelManager<ItemStub> otherManager = new RepositoryModelManager<>(otherRepo);
        itemManager.setRepository(otherRepo);
        assertEquals(itemManager, otherManager);
    }

    @Test
    public void equals_differentValues_false() {
        Repository<ItemStub> otherRepo = new Repository<>();
        otherRepo.addItem(item1);
        otherRepo.addItem(item2);

        itemManager.addItem(item1);

        RepositoryModelManager<ItemStub> otherManager = new RepositoryModelManager<>(otherRepo);
        assertNotEquals(itemManager, otherManager);
    }

    @Test
    public void equals_differentObject_false() {
        assertNotEquals(itemManager, new Object());
    }
}
