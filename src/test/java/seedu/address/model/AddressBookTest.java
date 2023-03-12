package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoles.ALICE;
import static seedu.address.testutil.TypicalRoles.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.job.Role;
import seedu.address.model.job.exceptions.DuplicateRoleException;
import seedu.address.testutil.RoleBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getRoleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateRoles_throwsDuplicateRoleException() {
        // Two roles with the same identity fields
        Role editedAlice = new RoleBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Role> newRoles = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newRoles);

        assertThrows(DuplicateRoleException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasRole_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRole(null));
    }

    @Test
    public void hasRole_roleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleInAddressBook_returnsTrue() {
        addressBook.addRole(ALICE);
        assertTrue(addressBook.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addRole(ALICE);
        Role editedAlice = new RoleBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasRole(editedAlice));
    }

    @Test
    public void getRoleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRoleList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose roles list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Role> roles = FXCollections.observableArrayList();

        AddressBookStub(Collection<Role> roles) {
            this.roles.setAll(roles);
        }

        @Override
        public ObservableList<Role> getRoleList() {
            return roles;
        }
    }

}
