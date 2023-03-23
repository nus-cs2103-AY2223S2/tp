package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InternBuddy;
import seedu.address.model.ReadOnlyInternBuddy;
import seedu.address.model.internship.Internship;

/**
 * An Immutable InternBuddy that is serializable to JSON format.
 */
@JsonRootName(value = "internbuddy")
class JsonSerializableInternBuddy {

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "Internships list contains duplicate internship(s).";

    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternBuddy} with the given internships
     */
    @JsonCreator
    public JsonSerializableInternBuddy(@JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyInternBuddy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternBuddy}.
     */
    public JsonSerializableInternBuddy(ReadOnlyInternBuddy source) {
        internships.addAll(source.getInternshipList().stream().map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code InternBuddy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternBuddy toModelType() throws IllegalValueException {
        InternBuddy internBuddy = new InternBuddy();
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (internBuddy.hasInternship(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERNSHIP);
            }
            internBuddy.addInternship(internship);
        }
        return internBuddy;
    }

}
