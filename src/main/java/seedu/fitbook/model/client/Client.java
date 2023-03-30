package seedu.fitbook.model.client;

import static seedu.fitbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private Weight weight;
    private final Gender gender;
    private final Goal goal;
    private final Set<Appointment> appointments = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final Calorie calorie;
    private final WeightHistory weightHistory;
    private final Set<Routine> routines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Appointment> appointments,
                  Weight weight, Gender gender, Calorie calorie, Goal goal, Set<Tag> tags, Set<Routine> routines) {
        requireAllNonNull(name, phone, email, address, appointments, weight, gender, goal, tags, routines);
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
        this.goal = goal;
        this.weightHistory = new WeightHistory(new Weight(weight.value));
    }

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Appointment> appointments,
                  Weight weight, Gender gender, Calorie calorie, Goal goal, Set<Tag> tags, Set<Routine> routines,
                  List<Weight> weightHistory) {
        requireAllNonNull(
                name, phone, email, address, appointments, weight, gender, calorie, goal, tags, weightHistory);
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
        this.goal = goal;
        this.weightHistory = new WeightHistory(weightHistory);
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

    public void setWeight(Weight newWeight) {
        this.weight = newWeight;
    }

    public Gender getGender() {
        return gender;
    }

    public Goal getGoal() {
        return goal;
    }

    public WeightHistory getWeightHistory() {
        return weightHistory;
    }
    public List<Weight> getWeightList() {
        return weightHistory.weights;
    }
    public String getWeightValue() {
        return weight.value;
    }
    public boolean isAppointmentEmpty() {
        return appointments.isEmpty();
    }

    public String getWeightValue() {
        return weight.value;
    }

    /**
     * Copies a routine set to this instance object's {@code routine}.
     */
    public void copyRoutines(Set<Routine> routines) {
        this.routines.clear();
        this.routines.addAll(routines);
    }

    /**
     * Changes the {@code routines} set with the updated Routine with updated {@code RoutineName}.
     */
    public void changeRoutineIfRoutineNameMatch(Routine routineToEdit, Routine routineToChange) {
        if (routines.stream().anyMatch(routine -> routine.isSameRoutineName(routineToEdit))) {
            routines.removeIf(routine -> routine.isSameRoutineName(routineToEdit));
            routines.add(routineToChange);
        }
    }

    /**
     * Changes the {@code routines} set with the updated Routine with updated {@code Exercise}.
     */
    public void changeExerciseIfRoutineNameMatch(Routine routineToChange) {
        if (routines.stream().anyMatch(routine -> routine.isSameRoutineName(routineToChange))) {
            routines.removeIf(routine -> routine.isSameRoutineName(routineToChange));
            routines.add(routineToChange);
        }
    }

    /**
     * Deletes the {@code routines} set in the routine.
     */
    public void removeRoutineIfRoutineNameMatch(Routine routineToDelete) {
        routines.removeIf(routine -> routine.isSameRoutineName(routineToDelete));
    }

    /**
     * Deletes all {@Routine} in the {@code routines} set.
     */
    public void clearRoutines() {
        routines.clear();
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
    public List<RoutineName> getRoutinesName() {
        List<RoutineName> routineNames = new ArrayList<>();
        List<Routine> routinesList = new ArrayList<>(routines);
        routinesList.stream().sorted();
        routinesList.forEach(routine -> routineNames.add(routine.getRoutineName()));
        return Collections.unmodifiableList(routineNames);
    }

    /**
     * Returns a list of Exercise List for each routine that exists in the client, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public List<List<String>> getExercisesStringForRoutines() {
        List<List<String>> exercises = new ArrayList<>();
        List<Routine> routinesList = new ArrayList<>(routines);
        routinesList.stream().sorted();
        routinesList.forEach(routine -> exercises.add(routine.getExercisesName()));
        return Collections.unmodifiableList(exercises);
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
                && otherClient.getGoal().equals(getGoal())
                && otherClient.getCalorie().equals(getCalorie())
                && otherClient.getWeightHistory().equals(getWeightHistory())
                && otherClient.getRoutines().equals(getRoutines())
                && otherClient.getGoal().equals(getGoal());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, appointments, weight, gender, calorie, goal, tags, routines);
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

        Set<Routine> routines = getRoutines();
        if (!routines.isEmpty()) {
            builder.append("; Routines: ");
            routines.forEach(builder::append);
        }
        return builder.toString();
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

}
