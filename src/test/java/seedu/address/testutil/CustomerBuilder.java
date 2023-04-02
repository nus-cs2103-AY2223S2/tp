package seedu.address.testutil;

import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final int DEFAULT_ID = 1;
    public static final Set<Integer> DEFAULT_VEHICLE_IDS = new HashSet<>(List.of(1));
    public static final Set<Integer> DEFAULT_APPOINTMENT_IDS = new HashSet<>(List.of(1));


    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private int id;
    private Set<Integer> vehicleIds;
    private Set<Integer> appointmentIds;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        id =  DEFAULT_ID;
        tags = new HashSet<>();
        vehicleIds = DEFAULT_VEHICLE_IDS;
        appointmentIds = DEFAULT_APPOINTMENT_IDS;
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code personToCopy}.
     */
    public CustomerBuilder(Customer personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        id =  personToCopy.getId();
        vehicleIds = (Set<Integer>) personToCopy.getVehicleIds();
        appointmentIds = (Set<Integer>) personToCopy.getAppointmentIds();
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code vehicleIds} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withVehicleIds(Set<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
        return this;
    }

    /**
     * Sets the {@code appointmentIds} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAppointmentIds(Set<Integer> appointmentIds) {
        this.appointmentIds = appointmentIds;
        return this;
    }


    public Customer build() {
        return new Customer(id, name, phone, email, address, tags, vehicleIds, appointmentIds);
    }

}
