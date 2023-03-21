package seedu.fitbook.testutil.client;

import java.util.HashSet;
import java.util.Set;

import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Goal;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;
import seedu.fitbook.model.tag.Tag;
import seedu.fitbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CALORIE = "2000";
    public static final String DEFAULT_WEIGHT = "50.00";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_GOAL = "lose weight";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Calorie calorie;
    private Set<Appointment> appointments;
    private Set<Tag> tags;
    private Weight weight;
    private Gender gender;
    private Goal goal;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        calorie = new Calorie(DEFAULT_CALORIE);
        weight = new Weight(DEFAULT_WEIGHT);
        gender = new Gender(DEFAULT_GENDER);
        goal = new Goal(DEFAULT_GOAL);
        appointments = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        calorie = clientToCopy.getCalorie();
        weight = clientToCopy.getWeight();
        gender = clientToCopy.getGender();
        goal = clientToCopy.getGoal();
        appointments = new HashSet<>(clientToCopy.getAppointments());
        tags = new HashSet<>(clientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code appointments} into a {@code Set<Appointment>}
     * and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withAppointments(String ... appointments) {
        this.appointments = SampleDataUtil.getAppointmentSet(appointments);
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
     * Sets the {@code Weight} of the {@code Client} that we are building.
     */
    public ClientBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }
    /**
     * Sets the {@code Gender} of the {@code Client} that we are building.
     */
    public ClientBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Goal} of the {@code Client} that we are building.
     */
    public ClientBuilder withGoal(String goal) {
        this.goal = new Goal(goal);
        return this;
    }
    /**
     * Sets the {@code Calorie} of the {@code Client} that we are building.
     */
    public ClientBuilder withCalorie(String calorie) {
        this.calorie = new Calorie(calorie);
        return this;
    }

    public Client build() {
        return new Client(name, phone, email, address, appointments, weight, gender, calorie, goal, tags);
    }

}
