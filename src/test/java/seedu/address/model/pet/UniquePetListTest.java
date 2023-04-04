package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DOG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.WHISKERS;
import static seedu.address.testutil.TypicalPets.EXAMPLE_DOG;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

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
    public void contains_petNotInList_returnsFalse() {
        assertFalse(uniquePetList.contains(WHISKERS));
    }

    @Test
    public void contains_petInList_returnsTrue() {
        uniquePetList.add(WHISKERS);
        assertTrue(uniquePetList.contains(WHISKERS));
    }

    @Test
    public void contains_petWithSameIdentityFieldsInList_returnsTrue() {
        uniquePetList.add(WHISKERS);
        Pet editedWHISKERS = new PetBuilder(WHISKERS).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_DOG)
                .build();
        assertTrue(uniquePetList.contains(editedWHISKERS));
    }

    @Test
    public void add_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.add(null));
    }

    @Test
    public void add_duplicatePet_throwsDuplicatePetException() {
        uniquePetList.add(WHISKERS);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.add(WHISKERS));
    }

    @Test
    public void setPet_nullTargetPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPet(null, WHISKERS));
    }

    @Test
    public void setPet_nullEditedPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPet(WHISKERS, null));
    }

    @Test
    public void setPet_targetPetNotInList_throwsPetNotFoundException() {
        assertThrows(PetNotFoundException.class, () -> uniquePetList.setPet(WHISKERS, WHISKERS));
    }

    @Test
    public void setPet_editedPetIsSamePet_success() {
        uniquePetList.add(WHISKERS);
        uniquePetList.setPet(WHISKERS, WHISKERS);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(WHISKERS);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasSameIdentity_success() {
        uniquePetList.add(WHISKERS);
        Pet editedWHISKERS = new PetBuilder(WHISKERS).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_DOG)
                .build();
        uniquePetList.setPet(WHISKERS, editedWHISKERS);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(editedWHISKERS);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasDifferentIdentity_success() {
        uniquePetList.add(WHISKERS);
        uniquePetList.setPet(WHISKERS, EXAMPLE_DOG);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(EXAMPLE_DOG);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasNonUniqueIdentity_throwsDuplicatePetException() {
        uniquePetList.add(WHISKERS);
        uniquePetList.add(EXAMPLE_DOG);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.setPet(WHISKERS, EXAMPLE_DOG));
    }

    @Test
    public void remove_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.remove(null));
    }

    @Test
    public void remove_petDoesNotExist_throwsPetNotFoundException() {
        assertThrows(PetNotFoundException.class, () -> uniquePetList.remove(WHISKERS));
    }

    @Test
    public void remove_existingPet_removesPet() {
        uniquePetList.add(WHISKERS);
        uniquePetList.remove(WHISKERS);
        UniquePetList expectedUniquePetList = new UniquePetList();
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_nullUniquePetList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPets((UniquePetList) null));
    }

    @Test
    public void setPets_uniquePetList_replacesOwnListWithProvidedUniquePetList() {
        uniquePetList.add(WHISKERS);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(EXAMPLE_DOG);
        uniquePetList.setPets(expectedUniquePetList);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePetList.setPets((List<Pet>) null));
    }

    @Test
    public void setPets_list_replacesOwnListWithProvidedList() {
        uniquePetList.add(WHISKERS);
        List<Pet> petList = Collections.singletonList(EXAMPLE_DOG);
        uniquePetList.setPets(petList);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(EXAMPLE_DOG);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_listWithDuplicatePets_throwsDuplicatePetException() {
        List<Pet> listWithDuplicatePets = Arrays.asList(WHISKERS, WHISKERS);
        assertThrows(DuplicatePetException.class, () -> uniquePetList.setPets(listWithDuplicatePets));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePetList.asUnmodifiableObservableList().remove(0));
    }
}
