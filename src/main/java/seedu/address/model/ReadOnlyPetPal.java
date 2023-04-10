package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.pet.Pet;

/**
 * Unmodifiable view of a PetPal
 */
public interface ReadOnlyPetPal {

    /**
     * Returns an unmodifiable view of the pet list.
     * This list will not contain any duplicate pets.
     */
    ObservableList<Pet> getPetList();

}
