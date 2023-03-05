package seedu.address.model.group.exceptions;

public class PersonAlreadyInGroupException extends RuntimeException{
    public PersonAlreadyInGroupException() {
        super("Operation would result in duplicate person in a group");
    }
}
