package seedu.fitbook.model.client;

import static seedu.fitbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;
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
    private final Set<Appointment> appointments = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final Calorie calorie;
    private final Set<Routine> routines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Appointment> appointments,
                  Weight weight, Gender gender, Calorie calorie, Set<Tag> tags, Set<Routine> routines) {
        requireAllNonNull(name, phone, email, address, appointments, weight, gender, tags, routines);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.calorie = calorie;
        this.appointments.addAll(appointments);
        this.tags.addAll(tags);
        this.weight = weight;
        this.gender = gender;
        this.routines.addAll(routines);
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

    public boolean isAppointmentEmpty() {
        return appointments.isEmpty();
    }

    /**
     * Copies a routine set to this instance object's {@code routine}.
     */
    public void copyRoutines(Set<Routine> routines) {
        this.routines.clear();
        this.routines.addAll(routines);
    }

    /**
     * Returns an immutable routine set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Routine> getRoutines() {
        return Collections.unmodifiableSet(routines);
    }

    /**
     * Returns a set of Routine Names that exists in the client, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<RoutineName> getRoutinesName() {
        Set<RoutineName> routineNames = new HashSet<>();
        this.routines.forEach(routine -> routineNames.add(routine.getRoutineName()));
        return Collections.unmodifiableSet(routineNames);
    }

    /**
     * Returns a set of Exercise List for each routine that exists in the client, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<List<String>> getExercisesStringForRoutines() {
        Set<List<String>> exercises = new HashSet<>();
        this.routines.forEach(routine -> exercises.add(routine.getExercisesName()));
        return Collections.unmodifiableSet(exercises);
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
                && otherClient.getCalorie().equals(getCalorie())
                && otherClient.getTags().equals(getTags())
                && otherClient.getRoutines().equals(getRoutines());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, appointments, weight, gender, calorie, tags, routines);
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
                .append(getGender());

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

        Set<Routine> routines = getRoutines();
        if (!routines.isEmpty()) {
            builder.append("; Routines: ");
            routines.forEach(builder::append);
        }
        return builder.toString();
    }

}
