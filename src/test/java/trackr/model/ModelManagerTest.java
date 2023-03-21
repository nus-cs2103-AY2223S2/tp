package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalOrders.CHEESE_CAKES;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalSuppliers.BENSON;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.commons.core.GuiSettings;
import trackr.model.person.PersonNameContainsKeywordsPredicate;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.testutil.OrderListBuilder;
import trackr.testutil.SupplierListBuilder;
import trackr.testutil.TaskListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SupplierList(), new SupplierList(modelManager.getSupplierList()));
        assertEquals(new TaskList(), new TaskList(modelManager.getTaskList()));
        assertEquals(new OrderList(), new OrderList(modelManager.getOrderList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackrFilePath(Paths.get("trackr/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackrFilePath(Paths.get("new/trackr/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTrackrFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackrFilePath(null));
    }

    @Test
    public void setTrackrFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("trackr/file/path");
        modelManager.setTrackrFilePath(path);
        assertEquals(path, modelManager.getTrackrFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItem(null, ModelEnum.SUPPLIER));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasItem(ALICE, ModelEnum.SUPPLIER));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addItem(ALICE, ModelEnum.SUPPLIER);
        assertTrue(modelManager.hasItem(ALICE, ModelEnum.SUPPLIER));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredSupplierList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItem(null, ModelEnum.TASK));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(modelManager.hasItem(SORT_INVENTORY_N, ModelEnum.TASK));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        modelManager.addItem(SORT_INVENTORY_N, ModelEnum.TASK);
        assertTrue(modelManager.hasItem(SORT_INVENTORY_N, ModelEnum.TASK));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItem(null, ModelEnum.ORDER));
    }

    @Test
    public void hasOrder_orderNotInOrderList_returnsFalse() {
        assertFalse(modelManager.hasItem(CHOCOLATE_COOKIES, ModelEnum.ORDER));
    }

    @Test
    public void hasOrder_orderInOrderList_returnsTrue() {
        modelManager.addItem(CHOCOLATE_COOKIES, ModelEnum.ORDER);
        assertTrue(modelManager.hasItem(CHOCOLATE_COOKIES, ModelEnum.ORDER));
    }

    @Test
    public void getFilteredOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredOrderList().remove(0));
    }

    @Test
    public void equals() {
        SupplierList addressBook = new SupplierListBuilder().withSupplier(ALICE).withSupplier(BENSON).build();
        SupplierList differentAddressBook = new SupplierList();
        TaskList taskList = new TaskListBuilder().withTask(SORT_INVENTORY_N).withTask(BUY_FLOUR_N).build();
        TaskList differentTaskList = new TaskList();
        OrderList orderList = new OrderListBuilder().withOrder(CHOCOLATE_COOKIES).withOrder(CHEESE_CAKES).build();
        OrderList differentOrderList = new OrderList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, taskList, orderList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, taskList, orderList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, taskList, orderList, userPrefs)));

        // different taskList -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentTaskList,
                differentOrderList, userPrefs)));

        // different filteredPersonList -> returns false
        String[] personKeywords = ALICE.getPersonName().getName().split("\\s+");
        modelManager.updateFilteredItemList(new PersonNameContainsKeywordsPredicate(Arrays.asList(personKeywords)),
                ModelEnum.SUPPLIER);
        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, orderList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, ModelEnum.SUPPLIER);

        // different filteredTaskList -> returns false
        String[] taskKeywords = SORT_INVENTORY_N.getTaskName().getName().split("\\s+");
        TaskContainsKeywordsPredicate sortPredicate = new TaskContainsKeywordsPredicate();
        sortPredicate.setTaskNameKeywords(Arrays.asList(taskKeywords));
        modelManager.updateFilteredItemList(sortPredicate, ModelEnum.TASK);
        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, orderList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, ModelEnum.TASK);

        // different addressBook and different taskList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, differentTaskList,
                differentOrderList, userPrefs)));

        // different filteredPersonList and different filteredTaskList -> returns false
        personKeywords = ALICE.getPersonName().getName().split("\\s+");
        modelManager.updateFilteredItemList(new PersonNameContainsKeywordsPredicate(Arrays.asList(personKeywords)),
                ModelEnum.SUPPLIER);
        taskKeywords = BUY_FLOUR_N.getTaskName().getName().split("\\s+");
        TaskContainsKeywordsPredicate buyPredicate = new TaskContainsKeywordsPredicate();
        buyPredicate.setTaskNameKeywords(Arrays.asList(taskKeywords));
        modelManager.updateFilteredItemList(buyPredicate, ModelEnum.TASK);
        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, orderList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, ModelEnum.SUPPLIER);
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, ModelEnum.TASK);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackrFilePath(Paths.get("differentFilePath"));

        assertFalse(modelManager.equals(new ModelManager(addressBook, taskList, orderList, differentUserPrefs)));
    }
}
