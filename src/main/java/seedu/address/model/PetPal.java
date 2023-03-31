package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.UniquePetList;

/**
 * Wraps all data at the PetPal level
 * Duplicates are not allowed (by .isSamePet comparison)
 */
public class PetPal implements ReadOnlyPetPal {

    private final UniquePetList pets;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        pets = new UniquePetList();
    }

    public PetPal() {}

    /**
     * Creates an PetPal using the Pets in the {@code toBeCopied}
     */
    public PetPal(ReadOnlyPetPal toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the pets list with {@code pets}.
     * {@code pets} must not contain duplicate pets.
     */
    public void setPets(List<Pet> pets) {
        this.pets.setPets(pets);
    }

    /**
     * Resets the existing data of this {@code PetPal} with {@code newData}.
     */
    public void resetData(ReadOnlyPetPal newData) {
        requireNonNull(newData);

        setPets(newData.getPetList());
    }

    //// pet-level operations

    /**
     * Returns true if a pet with the same identity as {@code pet} exists in the address book.
     */
    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return pets.contains(pet);
    }

    /**
     * Adds a pet to the address book.
     * The pet must not already exist in the address book.
     */
    public void addPet(Pet p) {
        pets.add(p);
    }

    /**
     * Replaces the given pet {@code target} in the list with {@code editedpet}.
     * {@code target} must exist in the address book.
     * The pet identity of {@code editedPet} must not be the same as another existing pet in the address book.
     */
    public void setPet(Pet target, Pet editedPet) {
        requireNonNull(editedPet);

        pets.setPet(target, editedPet);
    }

    /**
     * Removes {@code key} from this {@code PetPal}.
     * {@code key} must exist in the address book.
     */
    public void removePet(Pet key) {
        pets.remove(key);
    }

    /**
     * Archives {@code p} from the {@code PetPal}
     * {@code p} must exists in the address book
     */
    public void archivePet(Pet p) {
        pets.add(p);
    }

    //// util methods

    /**
     * Marks the provided {@code Pet} isMarked field to true
     */
    public void markPet(Pet pet) {
        Pet markedPet = pet;
        markedPet.setMarked();
        pets.setPet(pet, markedPet);
    }

    @Override
    public String toString() {
        return pets.asUnmodifiableObservableList().size() + " pets";
        // TODO: refine later
    }

    @Override
    public ObservableList<Pet> getPetList() {
        return pets.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PetPal // instanceof handles nulls
                && pets.equals(((PetPal) other).pets));
    }

    @Override
    public int hashCode() {
        return pets.hashCode();
    }
}
