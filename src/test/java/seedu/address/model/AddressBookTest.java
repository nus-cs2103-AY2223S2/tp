package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2106_TUT;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.testutil.ModuleBuilder;

public class AddressBookTest {

    private final ModuleTracker addressBook = new ModuleTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ModuleTracker newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedAlice = new ModuleBuilder(CS2106_TUT).withAddress(VALID_ADDRESS_CS3219).withTags(VALID_TAG_LECTURE)
                .build();
        List<Module> newModules = Arrays.asList(CS2106_TUT, editedAlice);
        AddressBookStub newData = new AddressBookStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModule(CS2106_TUT));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(CS2106_TUT);
        assertTrue(addressBook.hasModule(CS2106_TUT));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addModule(CS2106_TUT);
        Module editedAlice = new ModuleBuilder(CS2106_TUT).withAddress(VALID_ADDRESS_CS3219).withTags(VALID_TAG_LECTURE)
                .build();
        assertTrue(addressBook.hasModule(editedAlice));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose modules list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyModuleTracker {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        AddressBookStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
