package bookopedia.model.person;

import static bookopedia.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import bookopedia.model.DeliveryStatus;
import bookopedia.model.parcel.Parcel;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Parcel> parcels = new HashSet<>();
    private final DeliveryStatus deliveryStatus;
    private final int noOfDeliveryAttempts;

    /**
     * Every field must be present and not null except phone and email.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Parcel> parcels,
                  DeliveryStatus deliveryStatus, int noOfDeliveryAttempts) {
        requireAllNonNull(name, address, parcels);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.parcels.addAll(parcels);
        this.deliveryStatus = deliveryStatus;
        this.noOfDeliveryAttempts = noOfDeliveryAttempts;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable parcel set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Parcel> getParcels() {
        return Collections.unmodifiableSet(parcels);
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public int getNoOfDeliveryAttempts() {
        return noOfDeliveryAttempts;
    }

    /**
     * Returns true if both persons have the same name and address.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getParcels().equals(getParcels())
                && otherPerson.getDeliveryStatus().equals(getDeliveryStatus())
                && otherPerson.getNoOfDeliveryAttempts() == getNoOfDeliveryAttempts();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, parcels, deliveryStatus, noOfDeliveryAttempts);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Parcel> parcels = getParcels();
        if (!parcels.isEmpty()) {
            builder.append("; Parcels: ");
            parcels.forEach(builder::append);
        }

        builder.append("; Delivery Status: ")
                .append(getDeliveryStatus().name())
                .append("; Number of delivery attempts: ")
                .append(getNoOfDeliveryAttempts());



        return builder.toString();
    }

}
