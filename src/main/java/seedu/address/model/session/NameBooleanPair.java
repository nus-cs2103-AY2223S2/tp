package seedu.address.model.session;

public class NameBooleanPair {
    public String name;
    public boolean isPresent;

    public NameBooleanPair(String name, boolean isPresent) {
        this.name = name;
        this.isPresent = false;
    }

    @Override
    public String toString() {
        if (isPresent) {
            return name + ": present";
        } else {
            return name + ": absent";
        }
    }
}
