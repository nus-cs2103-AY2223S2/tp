package seedu.fitbook.model.client;

import static seedu.fitbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.fitbook.model.tag.Tag;

/**
 * Represents a Client in the FitBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    // Data fields
    private final Address address;
    private final Weight weight;
    private final Gender gender;
    private final Goal goal;
    private final Set<Appointment> appointments = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final Calorie calorie;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Appointment> appointments,
                  Weight weight, Gender gender, Calorie calorie, Goal goal, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, appointments, weight, gender, calorie, goal, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.calorie = calorie;
        this.appointments.addAll(appointments);
        this.tags.addAll(tags);
        this.weight = weight;
        this.gender = gender;
        this.goal = goal;
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

    public Calorie getCalorie() {
        return calorie;
    }

    public Weight getWeight() {
        return weight;
    }

    public Gender getGender() {
        return gender;
    }

    public Goal getGoal() {
        return goal;
    }

    public boolean isAppointmentEmpty() {
        return appointments.isEmpty();
    }

    /**
     * Returns an immutable appointment set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getWeight().equals(getWeight())
                && otherClient.getGender().equals(getGender())
                && otherClient.getAppointments().equals(getAppointments())
                && otherClient.getTags().equals(getTags())
                && otherClient.getGoal().equals(getGoal())
                && otherClient.getCalorie().equals(getCalorie());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, appointments, weight, gender, calorie, goal, tags);
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
                .append(getAddress())
                .append("; Calorie: ")
                .append(getCalorie())
                .append("; Weight: ")
                .append(getWeight())
                .append(" Kg")
                .append(" Gender: ")
                .append(getGender())
                .append("; Goal: ")
                .append(getGoal());

        Set<Appointment> appointments = getAppointments();
        if (!appointments.isEmpty()) {
            builder.append("; Appointments: ");
            appointments.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
