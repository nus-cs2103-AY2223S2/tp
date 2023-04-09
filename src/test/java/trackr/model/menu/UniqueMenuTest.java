package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.item.exceptions.ItemNotFoundException;
import trackr.testutil.MenuItemBuilder;

public class UniqueMenuTest {

    private final UniqueMenu uniqueMenu = new UniqueMenu();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueMenu.contains(CHOCOLATE_COOKIE_M));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        assertTrue(uniqueMenu.contains(CHOCOLATE_COOKIE_M));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        MenuItem editedMenuItem = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                          .withItemPrice("9999")
                                          .build();
        assertTrue(uniqueMenu.contains(editedMenuItem));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        assertThrows(DuplicateItemException.class, () -> uniqueMenu.add(CHOCOLATE_COOKIE_M));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.setItem(null, CHOCOLATE_COOKIE_M));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.setItem(CHOCOLATE_COOKIE_M, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueMenu.setItem(CHOCOLATE_COOKIE_M, CHOCOLATE_COOKIE_M));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        uniqueMenu.setItem(CHOCOLATE_COOKIE_M, CHOCOLATE_COOKIE_M);

        UniqueMenu expectedUniqueMenu = new UniqueMenu();
        expectedUniqueMenu.add(CHOCOLATE_COOKIE_M);

        assertEquals(expectedUniqueMenu, uniqueMenu);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        MenuItem editedMenuItem = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                          .withItemPrice("9999")
                                          .build();
        uniqueMenu.setItem(CHOCOLATE_COOKIE_M, editedMenuItem); //change item price

        UniqueMenu expectedUniqueMenu = new UniqueMenu();
        expectedUniqueMenu.add(editedMenuItem);

        assertEquals(expectedUniqueMenu, uniqueMenu);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        uniqueMenu.setItem(CHOCOLATE_COOKIE_M, CUPCAKE_M);

        UniqueMenu expectedUniqueMenu = new UniqueMenu();
        expectedUniqueMenu.add(CUPCAKE_M);

        assertEquals(expectedUniqueMenu, uniqueMenu);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        uniqueMenu.add(CUPCAKE_M);
        assertThrows(DuplicateItemException.class, () -> uniqueMenu.setItem(CHOCOLATE_COOKIE_M, CUPCAKE_M));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueMenu.remove(CHOCOLATE_COOKIE_M));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        uniqueMenu.remove(CHOCOLATE_COOKIE_M);

        UniqueMenu expectedUniqueMenu = new UniqueMenu();

        assertEquals(expectedUniqueMenu, uniqueMenu);
    }

    @Test
    public void setTasks_nullUniqueMenu_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.setItems((UniqueMenu) null));
    }

    @Test
    public void setTasks_uniqueMenu_replacesOwnListWithProvidedUniqueMenu() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);

        UniqueMenu expectedUniqueMenu = new UniqueMenu();
        expectedUniqueMenu.add(CUPCAKE_M);

        uniqueMenu.setItems(expectedUniqueMenu);

        assertEquals(expectedUniqueMenu, uniqueMenu);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMenu.setItems((List<MenuItem>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);
        List<MenuItem> menuItemList = Collections.singletonList(CUPCAKE_M);
        uniqueMenu.setItems(menuItemList);

        UniqueMenu expectedUniqueMenu = new UniqueMenu();
        expectedUniqueMenu.add(CUPCAKE_M);

        assertEquals(expectedUniqueMenu, uniqueMenu);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<MenuItem> listWithDuplicateMenuItems = Arrays.asList(CHOCOLATE_COOKIE_M, CHOCOLATE_COOKIE_M);
        assertThrows(DuplicateItemException.class, () -> uniqueMenu.setItems(listWithDuplicateMenuItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueMenu
                                                                        .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        uniqueMenu.add(CHOCOLATE_COOKIE_M);

        UniqueMenu differentUniqueMenu = new UniqueMenu();
        differentUniqueMenu.add(CUPCAKE_M);

        UniqueMenu sameUniqueMenu = new UniqueMenu();
        sameUniqueMenu.add(CHOCOLATE_COOKIE_M);

        assertEquals(uniqueMenu, uniqueMenu); //same object
        assertEquals(uniqueMenu, sameUniqueMenu); //contains the same suppliers

        assertNotEquals(null, uniqueMenu); //null
        assertNotEquals(uniqueMenu, differentUniqueMenu); //different supplier lists
        assertNotEquals(1, uniqueMenu); //different objects
    }

}
