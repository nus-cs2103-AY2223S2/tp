package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalFriendlyLink;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PairBuilder;
import seedu.address.testutil.PersonBuilder;

public class FriendlyLinkTest {

    private final FriendlyLink friendlyLink = new FriendlyLink();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friendlyLink.getPersonList());
        // TODO: implement assertEquals(Collections.emptyList(), addressBook.getPairList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FriendlyLink newData = getTypicalFriendlyLink();
        friendlyLink.resetData(newData);
        assertEquals(newData, friendlyLink);
        // TODO: check if reset data resets the pair list.
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        FriendlyLinkStub newData = new FriendlyLinkStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> friendlyLink.resetData(newData));
        // TODO: check that duplicate pairs throws exceptions.
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(friendlyLink.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        friendlyLink.addPerson(ALICE);
        assertTrue(friendlyLink.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        friendlyLink.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(friendlyLink.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendlyLink.getPersonList().remove(0));
    }

    @Test
    public void hasPair_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.hasPair(null));
    }

    @Test
    public void hasPair_pairNotInAddressBook_returnsFalse() {
        assertFalse(friendlyLink.hasPair(PAIR1));
    }

    @Test
    public void hasPair_pairInAddressBook_returnsTrue() {
        friendlyLink.addPair(PAIR1);
        assertTrue(friendlyLink.hasPair(PAIR1));
    }

    @Test
    public void hasPair_pairWithSameElderlyAndVolunteer_returnsTrue() {
        friendlyLink.addPair(PAIR1);
        Pair editedPair = new PairBuilder(PAIR1).build();
        assertTrue(friendlyLink.hasPair(editedPair));
    }

    @Test
    public void getPairList_modifyList_throwsUnsupportedOperationException() {
        // TODO: assertThrows(UnsupportedOperationException.class, () -> addressBook.getPairList().remove(0));
    }


    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class FriendlyLinkStub implements ReadOnlyFriendlyLink {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Pair> pairs = FXCollections.observableArrayList();

        FriendlyLinkStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Pair> getPairList() {
            return pairs;
        }
    }

}
