package seedu.address.model.pet.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException() {
        super("Pet not found");
    }
}
