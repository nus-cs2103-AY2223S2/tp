package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDING_INTERVAL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFishes.ALICE;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.exceptions.DuplicateFishException;
import seedu.address.testutil.FishBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getFishList());
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
    public void resetData_withDuplicateFishes_throwsDuplicateFishException() {
        // Two fish with the same identity fields
        Fish editedAlice = new FishBuilder(ALICE).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Fish> newFish = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newFish);

        assertThrows(DuplicateFishException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasFish_nullFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasFish(null));
    }

    @Test
    public void hasFish_fishNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasFish(ALICE));
    }

    @Test
    public void hasFish_fishInAddressBook_returnsTrue() {
        addressBook.addFish(ALICE);
        assertTrue(addressBook.hasFish(ALICE));
    }

    @Test
    public void hasFish_fishWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addFish(ALICE);
        Fish editedAlice = new FishBuilder(ALICE).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasFish(editedAlice));
    }

    @Test
    public void getFishList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFishList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose fish list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Fish> fish = FXCollections.observableArrayList();

        AddressBookStub(Collection<Fish> fish) {
            this.fish.setAll(fish);
        }

        @Override
        public ObservableList<Fish> getFishList() {
            return fish;
        }
    }

}
