package seedu.dengue.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.DateAndTime;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String postal;
    private final String dateandtime;
    private final String age;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("postal") String postal,
            @JsonProperty("datetime") String dateandtime, @JsonProperty("age") String age,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.postal = postal;
        this.dateandtime = dateandtime;
        this.age = age;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        postal = source.getPostal().value;
        dateandtime = source.getDateAndTime().value;
        age = source.getAge().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
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

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (postal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Postal.class.getSimpleName()));
        }
        if (!Postal.isValidPostal(postal)) {
            throw new IllegalValueException(Postal.MESSAGE_CONSTRAINTS);
        }
        final Postal modelPostal = new Postal(postal);

        if (dateandtime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateAndTime.class.getSimpleName()));
        }
        if (!DateAndTime.isValidDateAndTime(dateandtime)) {
            throw new IllegalValueException(DateAndTime.MESSAGE_CONSTRAINTS);
        }
        final DateAndTime modelDateAndTime = new DateAndTime(dateandtime);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPostal, modelDateAndTime, modelAge, modelTags);
    }

}
