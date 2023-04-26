package seedu.address.model;

/**
 * A Simple Stub class implements Relationship
 */
public class ItemStub implements Relationship<ItemStub> {

    private final String value;

    public ItemStub(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    @Override
    public boolean isSame(ItemStub otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
            && otherItem.value.equals(value);
    }

    @Override
    public boolean hasSameId(ItemStub otherItem) {
        return isSame(otherItem);
    }
}
