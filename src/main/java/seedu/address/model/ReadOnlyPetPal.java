package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.pet.Pet;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPetPal {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Pet> getPetList();

}
