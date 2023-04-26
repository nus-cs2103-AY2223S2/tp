package seedu.careflow.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.model.patient.Address;
import seedu.careflow.model.patient.DateOfBirth;
import seedu.careflow.model.patient.DrugAllergy;
import seedu.careflow.model.patient.Email;
import seedu.careflow.model.patient.Gender;
import seedu.careflow.model.patient.Ic;
import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.Phone;

/**
 * Jackson-friendly version of {@link Patient}.
 */
public class JsonAdaptedPatient {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthDate;
    private final String gender;
    private final String ic;
    private final String drugAllergy;
    private final String emergencyContact;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("birthDate") String birthDate, @JsonProperty("gender") String gender,
                              @JsonProperty("ic") String ic, @JsonProperty("drugAllergy") String drugAllergy,
                              @JsonProperty("emergencyContact") String emergencyContact) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.ic = ic;
        this.drugAllergy = drugAllergy;
        this.emergencyContact = emergencyContact;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthDate = source.getBirthDate().value;
        gender = source.getGender().value;
        ic = source.getIc().value;
        if (source.getDrugAllergy() != null) {
            drugAllergy = source.getDrugAllergy().value;
        } else {
            drugAllergy = null;
        }
        if (source.getEmergencyContact() != null) {
            emergencyContact = source.getEmergencyContact().value;
        } else {
            emergencyContact = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {

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

        if (birthDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidBirthDate(birthDate)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        final DateOfBirth modelBirthDate = new DateOfBirth(birthDate);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Ic.class.getSimpleName()));
        }
        if (!Ic.isValidIc(ic)) {
            throw new IllegalValueException(Ic.MESSAGE_CONSTRAINTS);
        }
        final Ic modelIc = new Ic(ic);

        if (drugAllergy != null && !DrugAllergy.isValidDrugAllergy(drugAllergy)) {
            throw new IllegalValueException(DrugAllergy.MESSAGE_CONSTRAINTS);
        }

        DrugAllergy modelDrugAllergy = null;
        if (drugAllergy != null) {
            modelDrugAllergy = new DrugAllergy(drugAllergy);
        }

        if (emergencyContact != null && !Phone.isValidPhone(emergencyContact)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }

        Phone modelEmergencyContact = null;
        if (emergencyContact != null) {
            modelEmergencyContact = new Phone(emergencyContact);
        }

        if (modelDrugAllergy == null && modelEmergencyContact == null) {
            return new Patient(modelName, modelPhone, modelEmail, modelAddress, modelBirthDate, modelGender, modelIc);
        }

        return new Patient(modelName, modelPhone, modelEmail, modelAddress, modelBirthDate, modelGender, modelIc,
                modelDrugAllergy, modelEmergencyContact);
    }

}
