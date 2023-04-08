package seedu.sprint.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.sprint.commons.exceptions.IllegalValueException;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.ReadOnlyInternshipBook;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.exceptions.DuplicateApplicationException;

/**
 * An Immutable InternshipBook that is serializable to JSON format.
 */
@JsonRootName(value = "internshipbook")
class JsonSerializableInternshipBook {
    public static final String MESSAGE_DUPLICATE_APPLICATION = "Operation would result in duplicate entries.";

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
        try {
            internshipBook.setApplications(applications);
        } catch (DuplicateApplicationException e) {
            throw new IllegalValueException(e.getMessage());
        }
        return internshipBook;
    }

}
