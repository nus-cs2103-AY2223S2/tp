package seedu.address.testutil;

import seedu.address.model.PetPal;
import seedu.address.model.pet.Pet;

/**
 * A utility class to help with building PetPal objects.
 * Example usage: <br>
 *     {@code PetPal ab = new PetPalBuilder().withPerson("John", "Doe").build();}
 */
public class PetPalBuilder {

    private PetPal petPal;

    public PetPalBuilder() {
        petPal = new PetPal();
    }

    public PetPalBuilder(PetPal petPal) {
        this.petPal = petPal;
    }

    /**
     * Adds a new {@code Person} to the {@code PetPal} that we are building.
     */
    public PetPalBuilder withPet(Pet pet) {
        petPal.addPet(pet);
        return this;
    }

    public PetPal build() {
        return petPal;
    }


}
