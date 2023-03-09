package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Set;

public class DeliveryMan extends Person {
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public DeliveryMan(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    public DeliveryMan editName(Name newName) {
        return new DeliveryMan(newName, this.getPhone(), this.getEmail(), this.getAddress(), this.getTags());
    }

    public DeliveryMan editPhone(Phone newPhone) {
        return new DeliveryMan(this.getName(), newPhone, this.getEmail(), this.getAddress(), this.getTags());
    }

    public DeliveryMan editEmail(Email newEmail) {
        return new DeliveryMan(this.getName(), this.getPhone(), newEmail, this.getAddress(), this.getTags());
    }

    public DeliveryMan editAddress(Address newAddress) {
        return new DeliveryMan(this.getName(), this.getPhone(), this.getEmail(), newAddress, this.getTags());
    }

    public DeliveryMan editTags(Tag newTag) {
        return new DeliveryMan(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), (Set<Tag>) newTag);
    }
}
