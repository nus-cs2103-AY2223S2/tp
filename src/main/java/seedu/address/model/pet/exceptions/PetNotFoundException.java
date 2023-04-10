package seedu.address.model.pet.exceptions;

/**
 * Signals that the operation will result in given Pet not found in the pet list
 */
public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException() {
        super("Pet not found");
    }
}
