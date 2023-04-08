package seedu.medinfo.model.ward.exceptions;

public class WardFullException extends RuntimeException{
    public WardFullException() {
        super("Ward is full!");
    }
}
