package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String nric;
    private final String name;
    private final String date;
    private final String phone;
    private final String email;
    private final String address;
    private final String gender;
    private final String doctor;
    private final String drugAllergy;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedMedicine> medicines = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("nric") String nric,
                             @JsonProperty("name") String name,
                             @JsonProperty("date") String date,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("drugAllergy") String drugAllergy,
                             @JsonProperty("gender") String gender,
                             @JsonProperty("doctor") String doctor,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("medicines") List<JsonAdaptedMedicine> medicines) {
        this.nric = nric;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = String.valueOf(gender);
        this.doctor = doctor;
        this.drugAllergy = drugAllergy;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (medicines != null) {
            this.medicines.addAll(medicines);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        nric = source.getNric().fullNric;
        name = source.getName().fullName;
        date = source.getDate().toString();
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        gender = source.getGender().gender;
        doctor = source.getDoctor().doctor;
        drugAllergy = source.getDrugAllergy().value;

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        medicines.addAll(source.getMedicines().stream()
                .map(JsonAdaptedMedicine::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Medicine> personMedicines = new ArrayList<>();
        for (JsonAdaptedMedicine medicine : medicines) {
            personMedicines.add(medicine.toModelType());
        }

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                            DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidDate(date)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        final DateOfBirth modelDate = new DateOfBirth(date);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (doctor == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName()));
        }
        if (!Doctor.isValidDoctor(doctor)) {
            throw new IllegalValueException(Doctor.MESSAGE_CONSTRAINTS);
        }
        final Doctor modelDoctor = new Doctor(doctor);

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

        if (drugAllergy == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DrugAllergy.class.getSimpleName()));
        }
        if (!DrugAllergy.isValidDrugAllergy(drugAllergy)) {
            throw new IllegalValueException(DrugAllergy.MESSAGE_CONSTRAINTS);
        }
        final DrugAllergy modelAllergy = new DrugAllergy(drugAllergy);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Medicine> modelMedicines = new HashSet<>(personMedicines);
        return new Person(modelNric, modelName, modelDate,
                            modelPhone, modelEmail,
                            modelAddress, modelAllergy, modelGender,
                            modelDoctor, modelTags, modelMedicines);
    }

}
