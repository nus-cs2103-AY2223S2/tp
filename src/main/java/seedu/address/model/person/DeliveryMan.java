package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Delivery Man.
 */
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

    /**
     * Allows delivery man to edit name.
     *
     * @param newName edited name.
     * @return new delivery man with edited name.
     */
    public DeliveryMan editName(Name newName) {
        return new DeliveryMan(newName, this.getPhone(), this.getEmail(), this.getAddress(), this.getTags());
    }

    /**
     * Allows delivery man to edit phone number.
     *
     * @param newPhone edited phone number.
     * @return new delivery man with edited phone number.
     */
    public DeliveryMan editPhone(Phone newPhone) {
        return new DeliveryMan(this.getName(), newPhone, this.getEmail(), this.getAddress(), this.getTags());
    }

    /**
     * Allows delivery man to edit Email.
     *
     * @param newEmail edited Email.
     * @return new delivery man with edited Email.
     */
    public DeliveryMan editEmail(Email newEmail) {
        return new DeliveryMan(this.getName(), this.getPhone(), newEmail, this.getAddress(), this.getTags());
    }

    /**
     * Allows delivery man to edit Address.
     *
     * @param newAddress edited address.
     * @return new delivery man with edited address.
     */
    public DeliveryMan editAddress(Address newAddress) {
        return new DeliveryMan(this.getName(), this.getPhone(), this.getEmail(), newAddress, this.getTags());
    }

    /**
     * Allows delivery man to edit tag.
     *
     * @param newTag edited tag.
     * @return new delivery man with edited tag.
     */
    public DeliveryMan editTags(Tag newTag) {
        return new DeliveryMan(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), (Set<Tag>) newTag);
    }
}
