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


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof IdData)) {
            return false;
        }

        IdData<?> o = (IdData<?>) other;
        return this.isActive == o.isActive && this.id == o.id
                && this.value.equals(o.value);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + ((isActive) ? 1 : 0);
        result = 37 * result + id;
        result = 37 * value.hashCode();

        return result;
    }


    @Override
    public String toString() {
        return value.toString();
    }
}
