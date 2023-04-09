package bookopedia.testutil;

import java.util.HashSet;
import java.util.Set;

import bookopedia.model.DeliveryStatus;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Person;
import bookopedia.model.person.Phone;
import bookopedia.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Parcel> parcels;
    private DeliveryStatus deliveryStatus;
    private int noOfDeliveryAttempts;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        parcels = new HashSet<>();
        deliveryStatus = DeliveryStatus.PENDING;
        noOfDeliveryAttempts = 0;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        parcels = new HashSet<>(personToCopy.getParcels());
        deliveryStatus = personToCopy.getDeliveryStatus();
        noOfDeliveryAttempts = personToCopy.getNoOfDeliveryAttempts();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code parcels} into a {@code Set<Parcel>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withParcels(String ... parcels) {
        this.parcels = SampleDataUtil.getParcelSet(parcels);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code DeliveryStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    /**
     * Sets the {@code noOfDeliveryAttempts} of the {@code Person} that we are building.
     */
    public PersonBuilder withNoOfDeliveryAttempts(int noOfDeliveryAttempts) {
        this.noOfDeliveryAttempts = noOfDeliveryAttempts;
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, parcels, deliveryStatus, noOfDeliveryAttempts);
    }

}
