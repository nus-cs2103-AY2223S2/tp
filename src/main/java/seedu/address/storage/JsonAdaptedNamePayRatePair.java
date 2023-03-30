package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.session.NamePayRatePair;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link NamePayRatePair}.
 */
public class JsonAdaptedNamePayRatePair {

    private final String studentName;
    private final int payRate;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedNamePayRatePair(@JsonProperty("name") String studentName,
                                      @JsonProperty("payRate") int payRate) {
        this.studentName = studentName;
        this.payRate = payRate;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedNamePayRatePair(NamePayRatePair source) {
        this.studentName = source.getName();
        this.payRate = source.getPayRate();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public NamePayRatePair toModelType() throws IllegalValueException {
        if (!Name.isValidName(studentName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new NamePayRatePair(studentName, payRate);
    }

}
