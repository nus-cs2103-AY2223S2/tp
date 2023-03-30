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

    private final List<JsonAdaptedApplication> jsonAdaptedApplications = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternshipBook} with the given applications.
     */
    @JsonCreator
    public JsonSerializableInternshipBook(@JsonProperty("jsonAdaptedApplications") List<JsonAdaptedApplication>
                                                      jsonAdaptedApplications) {
        this.jsonAdaptedApplications.addAll(jsonAdaptedApplications);
    }

    /**
     * Converts a given {@code ReadOnlyInternshipBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternshipBook}.
     */
    public JsonSerializableInternshipBook(ReadOnlyInternshipBook source) {
        jsonAdaptedApplications.addAll(source.getApplicationList().stream().map(JsonAdaptedApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this internship book into the model's {@code InternshipBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternshipBook toModelType() throws IllegalValueException {
        InternshipBook internshipBook = new InternshipBook();
        List<Application> applications = new ArrayList<>();
        for (JsonAdaptedApplication jsonAdaptedApplication : jsonAdaptedApplications) {
            Application application = jsonAdaptedApplication.toModelType();
            applications.add(application);
        }
        internshipBook.setApplications(applications);
        return internshipBook;
    }

}
