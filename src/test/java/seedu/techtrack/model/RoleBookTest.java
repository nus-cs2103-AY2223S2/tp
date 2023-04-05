package seedu.techtrack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.techtrack.testutil.Assert.assertThrows;
import static seedu.techtrack.testutil.TypicalRoles.ALICE;
import static seedu.techtrack.testutil.TypicalRoles.getTypicalRoleBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.exceptions.DuplicateRoleException;
import seedu.techtrack.testutil.RoleBuilder;

public class RoleBookTest {

    private final RoleBook roleBook = new RoleBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roleBook.getRoleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roleBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRoleBook_replacesData() {
        RoleBook newData = getTypicalRoleBook();
        roleBook.resetData(newData);
        assertEquals(newData, roleBook);
    }

    @Test
    public void resetData_withDuplicateRoles_throwsDuplicateRoleException() {
        // Two roles with the same identity fields
        Role editedAlice = new RoleBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        List<Role> newRoles = Arrays.asList(ALICE, editedAlice);
        RoleBookStub newData = new RoleBookStub(newRoles);

        assertThrows(DuplicateRoleException.class, () -> roleBook.resetData(newData));
    }

    @Test
    public void hasRole_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roleBook.hasRole(null));
    }

    @Test
    public void hasRole_roleNotInRoleBook_returnsFalse() {
        assertFalse(roleBook.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleInRoleBook_returnsTrue() {
        roleBook.addRole(ALICE);
        assertTrue(roleBook.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleWithSameIdentityFieldsInRoleBook_returnsTrue() {
        roleBook.addRole(ALICE);
        Role editedAlice = new RoleBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(roleBook.hasRole(editedAlice));
    }

    @Test
    public void getRoleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> roleBook.getRoleList().remove(0));
    }

    /**
     * A stub ReadOnlyRoleBook whose roles list can violate interface constraints.
     */
    private static class RoleBookStub implements ReadOnlyRoleBook {
        private final ObservableList<Role> roles = FXCollections.observableArrayList();

        RoleBookStub(Collection<Role> roles) {
            this.roles.setAll(roles);
        }

        @Override
        public ObservableList<Role> getRoleList() {
            return roles;
        }
    }

}
