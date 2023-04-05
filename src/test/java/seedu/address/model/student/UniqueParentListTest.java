package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParents.ALICE;
import static seedu.address.testutil.TypicalParents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateParentException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.UniqueParentList;
import seedu.address.testutil.ParentBuilder;

public class UniqueParentListTest {

    private final UniqueParentList uniqueParentList = new UniqueParentList();

    @Test
    public void contains_nullParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.contains(null));
    }

    @Test
    public void contains_parentNotInList_returnsFalse() {
        assertFalse(uniqueParentList.contains(ALICE));
    }

    @Test
    public void contains_parentInList_returnsTrue() {
        uniqueParentList.add(ALICE);
        assertTrue(uniqueParentList.contains(ALICE));
    }

    @Test
    public void contains_parentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueParentList.add(ALICE);
        Parent editedAlice = new ParentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_QUIET)
                .build();
        assertTrue(uniqueParentList.contains(editedAlice));
    }

    @Test
    public void add_nullParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.add(null));
    }

    @Test
    public void add_duplicateParent_throwsDuplicateParentException() {
        uniqueParentList.add(ALICE);
        assertThrows(DuplicateParentException.class, () -> uniqueParentList.add(ALICE));
    }

    @Test
    public void setParent_nullTargetParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.setParent(null, ALICE));
    }

    @Test
    public void setParent_nullEditedParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.setParent(ALICE, null));
    }

    @Test
    public void setParent_targetParentNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueParentList.setParent(ALICE, ALICE));
    }

    @Test
    public void setParent_editedParentIsSameParent_success() {
        uniqueParentList.add(ALICE);
        uniqueParentList.setParent(ALICE, ALICE);
        UniqueParentList expectedUniqueParentList = new UniqueParentList();
        expectedUniqueParentList.add(ALICE);
        assertEquals(expectedUniqueParentList, uniqueParentList);
    }

    @Test
    public void setParent_editedPersonHasSameIdentity_success() {
        uniqueParentList.add(ALICE);
        Parent editedAlice = new ParentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_QUIET)
                .build();
        uniqueParentList.setParent(ALICE, editedAlice);
        UniqueParentList expectedUniqueParentList = new UniqueParentList();
        expectedUniqueParentList.add(editedAlice);
        assertEquals(expectedUniqueParentList, uniqueParentList);
    }

    @Test
    public void setParent_editedParentHasDifferentIdentity_success() {
        uniqueParentList.add(ALICE);
        uniqueParentList.setParent(ALICE, BOB);
        UniqueParentList expectedUniqueParentList = new UniqueParentList();
        expectedUniqueParentList.add(BOB);
        assertEquals(expectedUniqueParentList, uniqueParentList);
    }

    @Test
    public void setParent_editedParentHasNonUniqueIdentity_throwsDuplicateParentException() {
        uniqueParentList.add(ALICE);
        uniqueParentList.add(BOB);
        assertThrows(DuplicateParentException.class, () -> uniqueParentList.setParent(ALICE, BOB));
    }

    @Test
    public void remove_nullParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.remove(null));
    }

    @Test
    public void remove_parentDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueParentList.remove(ALICE));
    }

    @Test
    public void remove_existingParent_removesParent() {
        uniqueParentList.add(ALICE);
        uniqueParentList.remove(ALICE);
        UniqueParentList expectedUniqueParentList = new UniqueParentList();
        assertEquals(expectedUniqueParentList, uniqueParentList);
    }

    @Test
    public void setParents_nullUniqueParentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.setParents((UniqueParentList) null));
    }

    @Test
    public void setParents_uniqueParentList_replacesOwnListWithProvidedUniqueParentList() {
        uniqueParentList.add(ALICE);
        UniqueParentList expectedUniqueParentList = new UniqueParentList();
        expectedUniqueParentList.add(BOB);
        uniqueParentList.setParents(expectedUniqueParentList);
        assertEquals(expectedUniqueParentList, uniqueParentList);
    }

    @Test
    public void setParents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParentList.setParents((List<Parent>) null));
    }

    @Test
    public void setParents_list_replacesOwnListWithProvidedList() {
        uniqueParentList.add(ALICE);
        List<Parent> parentList = Collections.singletonList(BOB);
        uniqueParentList.setParents(parentList);
        UniqueParentList expectedUniqueParentList = new UniqueParentList();
        expectedUniqueParentList.add(BOB);
        assertEquals(expectedUniqueParentList, uniqueParentList);
    }

    @Test
    public void setParents_listWithDuplicateParents_throwsDuplicateParentException() {
        List<Parent> listWithDuplicateParents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateParentException.class, () -> uniqueParentList.setParents(listWithDuplicateParents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueParentList.asUnmodifiableObservableList().remove(0));
    }
}
