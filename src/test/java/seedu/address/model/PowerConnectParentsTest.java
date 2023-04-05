package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParents.ALICE;
import static seedu.address.testutil.TypicalParents.getTypicalPowerConnectParents;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateParentException;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Parents;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.testutil.ParentBuilder;

public class PowerConnectParentsTest {
    private final Parents parents = new Parents();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), parents.getParentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parents.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Parents newData = getTypicalPowerConnectParents();
        parents.resetData(newData);
        assertEquals(newData, parents);
    }

    @Test
    public void resetData_withDuplicateParents_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Parent editedAlice = new ParentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_QUIET)
                .build();
        List<Parent> newParents = Arrays.asList(ALICE, editedAlice);
        PowerConnectParentsStub newData = new PowerConnectParentsStub(newParents);

        assertThrows(DuplicateParentException.class, () -> parents.resetData(newData));
    }

    @Test
    public void hasParent_nullParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parents.hasParent(null));
    }

    @Test
    public void hasParent_parentNotInAddressBook_returnsFalse() {
        assertFalse(parents.hasParent(ALICE));
    }

    @Test
    public void hasParent_parentInAddressBook_returnsTrue() {
        parents.addParent(ALICE);
        assertTrue(parents.hasParent(ALICE));
    }

    @Test
    public void hasParent_parentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        parents.addParent(ALICE);
        Parent editedAlice = new ParentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_QUIET)
                .build();
        assertTrue(parents.hasParent(editedAlice));
    }

    @Test
    public void getParentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> parents.getParentList().remove(0));
    }

    /**
     * A stub ReadOnlyParents whose parents list can violate interface constraints.
     */
    private static class PowerConnectParentsStub implements ReadOnlyParents {
        private final ObservableList<Parent> parents = FXCollections.observableArrayList();

        PowerConnectParentsStub(Collection<Parent> parents) {
            this.parents.setAll(parents);
        }

        @Override
        public ObservableList<Parent> getParentList() {
            return parents;
        }
    }
}
