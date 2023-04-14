package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Platoon;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Unit;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String rank;
    private final String name;
    private final String unit;
    private final String company;
    private final String platoon;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private boolean isFavorite = false;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
            @JsonProperty("rank") String rank,
            @JsonProperty("name") String name,
            @JsonProperty("unit") String unit,
            @JsonProperty("company") String company,
            @JsonProperty("platoon") String platoon,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("isFavorite") boolean isFavorite) {
        this.rank = rank;
        this.name = name;
        this.unit = unit == null ? "N/A" : unit;
        this.company = company == null ? "N/A" : company;
        this.platoon = platoon == null ? "N/A" : platoon;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isFavorite = isFavorite;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        rank = source.getRank().value;
        name = source.getName().fullName;
        unit = source.getUnit().value;
        company = source.getCompany().value;
        platoon = source.getPlatoon().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isFavorite = source.getIsFavorite();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

        if (rank == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rank.class.getSimpleName()));
        }
        if (!Rank.isValidRank(rank)) {
            throw new IllegalValueException(Rank.MESSAGE_CONSTRAINTS);
        }
        final Rank modelRank = new Rank(rank);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (unit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rank.class.getSimpleName()));
        }
        if (!Unit.isValidUnit(unit)) {
            throw new IllegalValueException(Unit.MESSAGE_CONSTRAINTS);
        }
        final Unit modelUnit = new Unit(unit);

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rank.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (platoon == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Platoon.class.getSimpleName()));
        }
        if (!Platoon.isValidPlatoon(platoon)) {
            throw new IllegalValueException(Platoon.MESSAGE_CONSTRAINTS);
        }
        final Platoon modelPlatoon = new Platoon(platoon);

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

        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        Person newPerson = new Person(modelRank, modelName, modelUnit, modelCompany, modelPlatoon,
                modelPhone, modelEmail, modelAddress, modelTags);
        newPerson.setIsFavorite(isFavorite);

        return newPerson;
    }

}
