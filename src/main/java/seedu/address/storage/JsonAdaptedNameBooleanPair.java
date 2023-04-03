package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.session.NameBooleanPair;

/**
 * Jackson-friendly version of {@link seedu.address.model.session.NameBooleanPair}.
 */
public class JsonAdaptedNameBooleanPair {

    private final String studentName;
    private final String isPresent;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedNameBooleanPair(@JsonProperty("name") String studentName,
                                      @JsonProperty("attendance") String isPresent) {
        this.studentName = studentName;
        this.isPresent = isPresent;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedNameBooleanPair(NameBooleanPair source) {
        this.studentName = source.getName();
        this.isPresent = String.valueOf(source.isPresent());

    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public NameBooleanPair toModelType() throws IllegalValueException {
        if (!Name.isValidName(studentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        return new NameBooleanPair(studentName, Boolean.parseBoolean(isPresent));
    }

}
