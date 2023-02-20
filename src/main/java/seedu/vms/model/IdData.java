package seedu.vms.model;


public class IdData<T> {
    private final boolean isActive;
    private final int id;
    private final T value;


    public IdData(int id, T value) {
        this(true, id, value);
    }

    public IdData(boolean isActive, int id, T value) {
        this.isActive = isActive;
        this.id = id;
        this.value = value;
    }


    public boolean isActive() {
        return isActive;
    }


    public int getId() {
        return id;
    }


    public T getValue() {
        return value;
    }
}
