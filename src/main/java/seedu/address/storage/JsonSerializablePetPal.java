package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PetPal;
import seedu.address.model.ReadOnlyPetPal;
import seedu.address.model.pet.Pet;

/**
 * An Immutable PetPal that is serializable to JSON format.
 */
@JsonRootName(value = "PetPal")
class JsonSerializablePetPal {

    public static final String MESSAGE_DUPLICATE_PET = "Pets list contains duplicate pet(s).";

    private final List<JsonAdaptedPet> pets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePetPal} with the given pets.
     */
    @JsonCreator
    public JsonSerializablePetPal(@JsonProperty("pets") List<JsonAdaptedPet> pets) {
        this.pets.addAll(pets);
    }

    /**
     * Converts a given {@code ReadOnlyPetPal} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePetPal}.
     */
    public JsonSerializablePetPal(ReadOnlyPetPal source) {
        pets.addAll(source.getPetList().stream().map(JsonAdaptedPet::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PetPal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PetPal toModelType() throws IllegalValueException {
        PetPal petPal = new PetPal();
        for (JsonAdaptedPet jsonAdaptedPet : pets) {
            Pet pet = jsonAdaptedPet.toModelType();
            if (petPal.hasPet(pet)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PET);
            }
            petPal.addPet(pet);
        }
        return petPal;
    }

}
