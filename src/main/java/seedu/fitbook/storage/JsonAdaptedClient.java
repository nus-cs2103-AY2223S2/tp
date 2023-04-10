package seedu.fitbook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fitbook.commons.exceptions.IllegalValueException;
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
import seedu.fitbook.model.client.WeightHistory;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;
import seedu.fitbook.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String calorie;
    private final String weight;
    private final String gender;
    private final String goal;

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    private final List<JsonAdaptedWeightHistory> weightHistories = new ArrayList<>();
    private final List<JsonAdaptedWeightDate> weightHistoriesDate = new ArrayList<>();

    private final List<JsonAdaptedRoutineName> routinesRoutineName = new ArrayList<>();
    private final List<JsonAdaptedExerciseClient> exercises = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(
            @JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
            @JsonProperty("weight") String weight,
            @JsonProperty("weightHistory") List<JsonAdaptedWeightHistory> weightHistory,
            @JsonProperty("weightDateTime") List<JsonAdaptedWeightDate> weightDateTime,
            @JsonProperty("gender") String gender, @JsonProperty("goal") String goal,
            @JsonProperty("calorie") String calorie, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("routines_names") List<JsonAdaptedRoutineName> routinesRoutineName,
            @JsonProperty("exercises") List<JsonAdaptedExerciseClient> exercises) throws IllegalValueException {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.calorie = calorie;
        this.weight = weight;
        this.gender = gender;
        this.goal = goal;
        if (appointments != null) {
            this.appointments.addAll(appointments);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (weightHistory != null) {
            this.weightHistories.addAll(weightHistory);
        }
        if (weightDateTime != null) {
            this.weightHistoriesDate.addAll(weightDateTime);
        }
        if (routinesRoutineName != null) {
            this.routinesRoutineName.addAll(routinesRoutineName);
        }
        if (exercises != null) {
            this.exercises.addAll(exercises);
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        calorie = source.getCalorie().value;
        weight = source.getWeight().value;
        gender = source.getGender().value;
        goal = source.getGoal().value;
        appointments.addAll(source.getAppointments().stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        weightHistories.addAll(source.getWeightHistory().weights.stream()
                .map(JsonAdaptedWeightHistory::new)
                .collect(Collectors.toList()));
        weightHistoriesDate.addAll(source.getWeightHistory().getListDates().stream()
                .map(JsonAdaptedWeightDate::new)
                .collect(Collectors.toList()));
        routinesRoutineName.addAll(source.getRoutinesName().stream()
                .map(JsonAdaptedRoutineName::new)
                .collect(Collectors.toList()));
        exercises.addAll(source.getExercisesStringForRoutines().stream()
                .map(JsonAdaptedExerciseClient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toFitBookModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        final List<Appointment> clientAppointments = new ArrayList<>();
        final List<Weight> clientWeightHistory = new ArrayList<>();

        if (weightHistories.size() != weightHistoriesDate.size()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    WeightHistory.class.getSimpleName()));
        }

        for (int i = 0; i < weightHistories.size(); i++) {
            JsonAdaptedWeightHistory jsonAdaptedWeightHistory = weightHistories.get(i);
            JsonAdaptedWeightDate jsonAdaptedWeightDate = weightHistoriesDate.get(i);
            clientWeightHistory.add(
                    new Weight(jsonAdaptedWeightDate.toFitBookModelType(),
                            jsonAdaptedWeightHistory.toFitBookModelType()));
        }
        final List<Routine> clientRoutines = new ArrayList<>();
        final List<List<Exercise>> routineExercises = new ArrayList<>();
        for (JsonAdaptedAppointment appointment : appointments) {
            clientAppointments.add(appointment.toFitBookModelType());
        }
        for (JsonAdaptedExerciseClient currExercise : exercises) {
            routineExercises.add(currExercise.toFitBookModelType());
        }
        int routineCount = 0;
        for (JsonAdaptedRoutineName currRoutineName : routinesRoutineName) {
            RoutineName routineName = currRoutineName.toFitBookModelType();
            try {
                clientRoutines.add(new Routine(routineName, routineExercises.get(routineCount)));
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Exercise.class.getSimpleName()));
            }
            routineCount++;
        }
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toFitBookModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (calorie == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Calorie.class.getSimpleName()));
        }
        if (!Calorie.isValidCalorie(calorie)) {
            throw new IllegalValueException(Calorie.MESSAGE_CONSTRAINTS);
        }
        final Calorie modelCalorie = new Calorie(calorie);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (goal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Goal.class.getSimpleName()));
        }
        if (!Goal.isValidGoal(goal)) {
            throw new IllegalValueException(Goal.MESSAGE_CONSTRAINTS);
        }
        final Goal modelGoal = new Goal(goal);

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);

        final Set<Appointment> modelAppointment = new HashSet<>(clientAppointments);
        final Set<Tag> modelTags = new HashSet<>(clientTags);
        final List<Weight> modelWeightHistory = new ArrayList<>(clientWeightHistory);
        final Set<Routine> modelRoutine = new HashSet<>(clientRoutines);
        return new Client(modelName, modelPhone, modelEmail, modelAddress, modelAppointment,
                modelWeight, modelGender, modelCalorie, modelGoal, modelTags, modelRoutine, modelWeightHistory);
    }
}
