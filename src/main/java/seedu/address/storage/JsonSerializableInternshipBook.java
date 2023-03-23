package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InternshipBook;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.application.Application;

/**
 * An Immutable InternshipBook that is serializable to JSON format.
 */
@JsonRootName(value = "internshipbook")
class JsonSerializableInternshipBook {

    public static final String MESSAGE_DUPLICATE_APPLICATION = "Applications list contains duplicate application(s).";

    private final List<JsonAdaptedApplication> applications = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternshipBook} with the given applications.
     */
    @JsonCreator
    public JsonSerializableInternshipBook(@JsonProperty("applications") List<JsonAdaptedApplication> applications) {
        this.applications.addAll(applications);
    }

    /**
     * Converts a given {@code ReadOnlyInternshipBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternshipBook}.
     */
    public JsonSerializableInternshipBook(ReadOnlyInternshipBook source) {
        applications.addAll(source.getApplicationList().stream().map(JsonAdaptedApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this internship book into the model's {@code InternshipBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternshipBook toModelType() throws IllegalValueException {
        InternshipBook internshipBook = new InternshipBook();
        for (JsonAdaptedApplication jsonAdaptedApplication : applications) {
            Application application = jsonAdaptedApplication.toModelType();
            if (internshipBook.hasApplication(application)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICATION);
            }
            internshipBook.addApplication(application);
        }
        return internshipBook;
    }

}
