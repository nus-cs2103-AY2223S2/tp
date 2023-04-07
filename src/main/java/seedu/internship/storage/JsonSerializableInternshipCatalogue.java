package seedu.internship.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.internship.Internship;

/**
 * An Immutable InternshipCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "TinS")
public class JsonSerializableInternshipCatalogue {

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "Internship list contains duplicate internship(s).";

    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternshipCatalogue} with the given internships.
     */
    @JsonCreator
    public JsonSerializableInternshipCatalogue(@JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyInternshipCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternshipCatalogue}.
     */
    public JsonSerializableInternshipCatalogue(ReadOnlyInternshipCatalogue source) {
        internships.addAll(source.getInternshipList().stream().map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this internship catalogue into the model's {@code InternshipCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternshipCatalogue toModelType() throws IllegalValueException {
        InternshipCatalogue internshipCatalogue = new InternshipCatalogue();
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (internshipCatalogue.hasInternship(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERNSHIP);
            }
            internshipCatalogue.addInternship(internship);
        }
        return internshipCatalogue;
    }

}
