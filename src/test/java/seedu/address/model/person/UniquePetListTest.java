package seedu.address.model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.ALICE;
import static seedu.address.testutil.TypicalPets.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.pet.Pet;
import seedu.address.model.pet.UniquePetList;
import seedu.address.model.pet.exceptions.DuplicatePetException;
import seedu.address.model.pet.exceptions.PetNotFoundException;
import seedu.address.testutil.PetBuilder;

public class UniquePetListTest {

    private final UniquePetList uniquePetList = new UniquePetList();

    @Test
    public void contains_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.contains(null));
    }

    @Test
    public void contains_PetNotInList_returnsFalse() {
        assertFalse(uniquePetList.contains(ALICE));
    }

    @Test
    public void contains_PetInList_returnsTrue() {
        uniquePetList.add(ALICE);
        assertTrue(uniquePetList.contains(ALICE));
    }

    @Test
    public void contains_PetWithSameIdentityFieldsInList_returnsTrue() {
        uniquePetList.add(ALICE);
        Pet editedAlice = new PetBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePetList.contains(editedAlice));
    }

    @Test
    public void add_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.add(null));
    }

    @Test
    public void add_duplicatePet_throwsDuplicatePetException() {
        uniquePetList.add(ALICE);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.add(ALICE));
    }

    @Test
    public void setPet_nullTargetPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPet(null, ALICE));
    }

    @Test
    public void setPet_nullEditedPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPet(ALICE, null));
    }

    @Test
    public void setPet_targetPetNotInList_throwsPetNotFoundException() {
        assertThrows(PetNotFoundException.class, () -> uniquePetList.setPet(ALICE, ALICE));
    }

    @Test
    public void setPet_editedPetIsSamePet_success() {
        uniquePetList.add(ALICE);
        uniquePetList.setPet(ALICE, ALICE);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(ALICE);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasSameIdentity_success() {
        uniquePetList.add(ALICE);
        Pet editedAlice = new PetBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePetList.setPet(ALICE, editedAlice);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(editedAlice);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasDifferentIdentity_success() {
        uniquePetList.add(ALICE);
        uniquePetList.setPet(ALICE, BOB);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(BOB);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasNonUniqueIdentity_throwsDuplicatePetException() {
        uniquePetList.add(ALICE);
        uniquePetList.add(BOB);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.setPet(ALICE, BOB));
    }

    @Test
    public void remove_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.remove(null));
    }

    @Test
    public void remove_PetDoesNotExist_throwsPetNotFoundException() {
        assertThrows(PetNotFoundException.class, () -> uniquePetList.remove(ALICE));
    }

    @Test
    public void remove_existingPet_removesPet() {
        uniquePetList.add(ALICE);
        uniquePetList.remove(ALICE);
        UniquePetList expectedUniquePetList = new UniquePetList();
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_nullUniquePetList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPets((UniquePetList) null));
    }

    @Test
    public void setPets_uniquePetList_replacesOwnListWithProvidedUniquePetList() {
        uniquePetList.add(ALICE);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(BOB);
        uniquePetList.setPets(expectedUniquePetList);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPets((List<Pet>) null));
    }

    @Test
    public void setPets_list_replacesOwnListWithProvidedList() {
        uniquePetList.add(ALICE);
        List<Pet> PetList = Collections.singletonList(BOB);
        uniquePetList.setPets(PetList);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(BOB);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_listWithDuplicatePets_throwsDuplicatePetException() {
        List<Pet> listWithDuplicatePets = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.setPets(listWithDuplicatePets));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePetList.asUnmodifiableObservableList().remove(0));
    }
}
