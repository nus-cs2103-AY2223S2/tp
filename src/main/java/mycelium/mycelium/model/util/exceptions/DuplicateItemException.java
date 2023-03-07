package mycelium.mycelium.model.util.exceptions;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException() {
        super(String.format("Operation would result in duplicate item."));
    }

    public DuplicateItemException(Object dup) {
        super(String.format("Operation would result in duplicate item %s.", dup));
    }
}
