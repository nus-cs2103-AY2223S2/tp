package seedu.medinfo.model.ward.exceptions;

public class WardFullException extends RuntimeException{
    private String name;
    public WardFullException(String name) {
        super(name + " is full!");
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " is full!";
    }
}
