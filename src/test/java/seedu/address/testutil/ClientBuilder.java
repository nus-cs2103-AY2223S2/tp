package seedu.address.testutil;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.UniquePolicyList;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    public static final Policy DEFAULT_POLICY = new PolicyBuilder().build();

    public static final Appointment DEFAULT_APPOINTMENT = new AppointmentBuilder().build();
    public static final Appointment DEFAULT_EMPTY_APPOINTMENT = new AppointmentBuilder().emptyBuild();

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;

    private UniquePolicyList policyList;
    private Appointment appointment;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        policyList = new UniquePolicyList();
        appointment = DEFAULT_EMPTY_APPOINTMENT;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        policyList = clientToCopy.getPolicyList();
        appointment = clientToCopy.getAppointment();
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code UniquePolicyList} of the {@code Client} that we are building.
     *
     * @param policies the policies that belong to the client.
     * @return A ClientBuilder that builds into {@code Client} when called.
     */
    public ClientBuilder withPolicyList(Policy... policies) {
        policyList = new UniquePolicyList();
        for (Policy policy : policies) {
            policyList.add(policy);
        }
        return this;
    }

    /**
     * Sets the {@code UniquePolicyList} of the {@code Client} that we are building.
     *
     * @param policyList the policies that belong to the client.
     * @return A ClientBuilder that builds into {@code Client} when called.
     */
    public ClientBuilder withPolicyList(UniquePolicyList policyList) {
        this.policyList = policyList;
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Client} that we are building.
     *
     * @param appointment the appointment that belongs to the client.
     * @return A ClientBuilder that builds into {@code Client} when called.
     */
    public ClientBuilder withAppointment(Appointment appointment) {
        this.appointment = appointment;
        return this;
    }

    // Don't need withPolicyList() because by default it should always be empty
    public Client build() {
        return new Client(name, phone, email, address, policyList, appointment);
    }

}
