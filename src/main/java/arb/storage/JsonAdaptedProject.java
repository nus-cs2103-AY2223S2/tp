package arb.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import arb.commons.exceptions.IllegalValueException;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.Title;

/**
 * Jackson-friendly version of {@link Project}.
 */
public class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";
    private final String title;

    private final String deadline;

    private final String status;

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline) {
        this.title = title;
        this.deadline = Optional.ofNullable(deadline).orElse(null);
        this.status = new Status().toString(); //not sure if this is correct?
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        this.title = source.getTitle().fullTitle;
        this.deadline = Optional.ofNullable(source.getDeadline().toString()).map(p -> p.toString()).orElse(null); //????
        this.status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (deadline != null && !Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException((Deadline.MESSAGE_CONSTRAINTS));
        }
        final Deadline modelDeadline = Optional.ofNullable(deadline).map(d -> new Deadline(d)).orElse(null);

        return new Project(modelTitle, modelDeadline);
    }

}
