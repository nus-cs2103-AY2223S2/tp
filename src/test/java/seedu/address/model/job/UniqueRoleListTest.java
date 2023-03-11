package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoles.ALICE;
import static seedu.address.testutil.TypicalRoles.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.job.exceptions.DuplicateRoleException;
import seedu.address.model.job.exceptions.RoleNotFoundException;
import seedu.address.testutil.RoleBuilder;

public class UniqueRoleListTest {

    private final UniqueRoleList uniqueRoleList = new UniqueRoleList();

    @Test
    public void contains_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.contains(null));
    }

    @Test
    public void contains_roleNotInList_returnsFalse() {
        assertFalse(uniqueRoleList.contains(ALICE));
    }

    @Test
    public void contains_roleInList_returnsTrue() {
        uniqueRoleList.add(ALICE);
        assertTrue(uniqueRoleList.contains(ALICE));
    }

    @Test
    public void contains_roleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoleList.add(ALICE);
        Role editedAlice = new RoleBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueRoleList.contains(editedAlice));
    }

    @Test
    public void add_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.add(null));
    }

    @Test
    public void add_duplicateRole_throwsDuplicateRoleException() {
        uniqueRoleList.add(ALICE);
        assertThrows(DuplicateRoleException.class, () -> uniqueRoleList.add(ALICE));
    }

    @Test
    public void setRole_nullTargetRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRole(null, ALICE));
    }

    @Test
    public void setRole_nullEditedRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRole(ALICE, null));
    }

    @Test
    public void setRole_targetRoleNotInList_throwsRoleNotFoundException() {
        assertThrows(RoleNotFoundException.class, () -> uniqueRoleList.setRole(ALICE, ALICE));
    }

    @Test
    public void setRole_editedRoleisSameRole_success() {
        uniqueRoleList.add(ALICE);
        uniqueRoleList.setRole(ALICE, ALICE);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(ALICE);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasSameIdentity_success() {
        uniqueRoleList.add(ALICE);
        Role editedAlice = new RoleBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueRoleList.setRole(ALICE, editedAlice);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(editedAlice);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasDifferentIdentity_success() {
        uniqueRoleList.add(ALICE);
        uniqueRoleList.setRole(ALICE, BOB);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(BOB);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasNonUniqueIdentity_throwsDuplicateRoleException() {
        uniqueRoleList.add(ALICE);
        uniqueRoleList.add(BOB);
        assertThrows(DuplicateRoleException.class, () -> uniqueRoleList.setRole(ALICE, BOB));
    }

    @Test
    public void remove_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.remove(null));
    }

    @Test
    public void remove_roleDoesNotExist_throwsRoleNotFoundException() {
        assertThrows(RoleNotFoundException.class, () -> uniqueRoleList.remove(ALICE));
    }

    @Test
    public void remove_existingRole_removesRole() {
        uniqueRoleList.add(ALICE);
        uniqueRoleList.remove(ALICE);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_nullUniqueRoleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRoles((UniqueRoleList) null));
    }

    @Test
    public void setRoles_uniqueRoleList_replacesOwnListWithProvidedUniqueRoleList() {
        uniqueRoleList.add(ALICE);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(BOB);
        uniqueRoleList.setRoles(expectedUniqueRoleList);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRoles((List<Role>) null));
    }

    @Test
    public void setRoles_list_replacesOwnListWithProvidedList() {
        uniqueRoleList.add(ALICE);
        List<Role> roleList = Collections.singletonList(BOB);
        uniqueRoleList.setRoles(roleList);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(BOB);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_listWithDuplicateRoles_throwsDuplicateRoleException() {
        List<Role> listWithDuplicateRoles = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateRoleException.class, () -> uniqueRoleList.setRoles(listWithDuplicateRoles));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRoleList.asUnmodifiableObservableList().remove(0));
    }
}
