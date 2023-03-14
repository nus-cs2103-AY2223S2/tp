package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STRONG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;
import static seedu.address.testutil.TypicalElderly.ALICE;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalVolunteers.BOB;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.PairBuilder;
import seedu.address.testutil.VolunteerBuilder;

public class FriendlyLinkTest {

    private final FriendlyLink friendlyLink = new FriendlyLink();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friendlyLink.getElderlyList());
        assertEquals(Collections.emptyList(), friendlyLink.getVolunteerList());
        assertEquals(Collections.emptyList(), friendlyLink.getPairList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.resetFriendlyLinkData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFriendlyLink_replacesData() {
        FriendlyLink newData = getTypicalFriendlyLink();
        friendlyLink.resetFriendlyLinkData(newData);
        assertEquals(newData, friendlyLink);
        // TODO: check if reset data resets the pair list.
    }

    @Test
    public void resetData_withDuplicateElderly_throwsDuplicatePersonException() {
        // Two elderly with the same identity fields
        Elderly editedAlice = new ElderlyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_SINGLE)
                .build();
        List<Elderly> newElderlyList = Arrays.asList(ALICE, editedAlice);
        FriendlyLinkStub newData = new FriendlyLinkStub(newElderlyList,
                Collections.emptyList(), Collections.emptyList());

        assertThrows(DuplicatePersonException.class, () -> friendlyLink.resetFriendlyLinkData(newData));
    }

    @Test
    public void resetData_withDuplicateVolunteers_throwsDuplicatePersonException() {
        // Two volunteers with the same identity fields
        Volunteer editedBob = new VolunteerBuilder(BOB).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_STRONG)
                .build();
        List<Volunteer> newVolunteers = Arrays.asList(BOB, editedBob);
        FriendlyLinkStub newData = new FriendlyLinkStub(Collections.emptyList(),
                newVolunteers, Collections.emptyList());

        assertThrows(DuplicatePersonException.class, () -> friendlyLink.resetFriendlyLinkData(newData));
    }

    // TODO: check that duplicate pairs throws exceptions.

    @Test
    public void hasElderly_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.hasElderly(null));
    }

    @Test
    public void hasVolunteer_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.hasVolunteer(null));
    }

    @Test
    public void hasElderly_elderlyNotInFriendlyLink_returnsFalse() {
        assertFalse(friendlyLink.hasElderly(ALICE));
    }

    @Test
    public void hasVolunteer_volunteerNotInFriendlyLink_returnsFalse() {
        assertFalse(friendlyLink.hasVolunteer(BOB));
    }

    @Test
    public void hasElderly_elderlyInFriendlyLink_returnsTrue() {
        friendlyLink.addElderly(ALICE);
        assertTrue(friendlyLink.hasElderly(ALICE));
    }

    @Test
    public void hasVolunteer_volunteerInFriendlyLink_returnsTrue() {
        friendlyLink.addVolunteer(BOB);
        assertTrue(friendlyLink.hasVolunteer(BOB));
    }

    @Test
    public void hasElderly_elderlyWithSameIdentityFieldsInFriendlyLink_returnsTrue() {
        friendlyLink.addElderly(ALICE);
        Elderly editedAlice = new ElderlyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_SINGLE)
                .build();
        assertTrue(friendlyLink.hasElderly(editedAlice));
    }

    @Test
    public void hasVolunteer_volunteerWithSameIdentityFieldsInFriendlyLink_returnsTrue() {
        friendlyLink.addVolunteer(BOB);
        Volunteer editedAlice = new VolunteerBuilder(BOB).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_STRONG)
                .build();
        assertTrue(friendlyLink.hasVolunteer(editedAlice));
    }

    @Test
    public void getElderlyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendlyLink.getElderlyList().remove(0));
    }

    @Test
    public void getVolunteerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendlyLink.getVolunteerList().remove(0));
    }

    @Test
    public void hasPair_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.hasPair(null));
    }

    @Test
    public void hasPair_pairNotInFriendlyLink_returnsFalse() {
        assertFalse(friendlyLink.hasPair(PAIR1));
    }

    @Test
    public void hasPair_pairInFriendlyLink_returnsTrue() {
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
    public void setPair_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendlyLink.setPair(null, null));
        assertThrows(NullPointerException.class, () -> friendlyLink.setPair(PAIR1, null));
        assertThrows(NullPointerException.class, () -> friendlyLink.setPair(null, PAIR1));
    }

    @Test
    public void getPairList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendlyLink.getPairList().remove(0));
    }

    /**
     * A stub ReadOnlyFriendlyLink whose persons list can violate interface constraints.
     */
    private static class FriendlyLinkStub implements ReadOnlyFriendlyLink {
        private final ObservableList<Elderly> elderly = FXCollections.observableArrayList();
        private final ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();
        private final ObservableList<Pair> pairs = FXCollections.observableArrayList();

        FriendlyLinkStub(Collection<Elderly> elderly, Collection<Volunteer> volunteers,
                Collection<Pair> pairs) {
            this.elderly.setAll(elderly);
            this.volunteers.setAll(volunteers);
            this.pairs.setAll(pairs);
        }

        @Override
        public ObservableList<Elderly> getElderlyList() {
            return elderly;
        }

        @Override
        public ObservableList<Volunteer> getVolunteerList() {
            return volunteers;
        }

        @Override
        public ObservableList<Pair> getPairList() {
            return pairs;
        }
    }

}
