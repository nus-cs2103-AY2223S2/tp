package arb.storage;

import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import arb.commons.core.LogsCenter;
import arb.commons.exceptions.IllegalValueException;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Title;

/**
 * Jackson-friendly version of {@link Project}.
 */
public class JsonAdaptedProject {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedProject.class);

    private final String title;

    private final String deadline;

    private final String status;

    private final String price;

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline,
                              @JsonProperty("status") String status, @JsonProperty("price") String price) {
        this.title = title;
        this.deadline = Optional.ofNullable(deadline).orElse(null);
        //System.out.println(this.deadline); //-> Feb 1 2025 14:00PM
        this.status = status;
        this.price = Optional.ofNullable(price).orElse(null);
        //System.out.println(this.price); //-> Price : $ 2
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        logger.info("Logging project: " + source);
        this.title = source.getTitle().fullTitle;
        this.deadline = Optional.ofNullable(source.getDeadline()).map(d -> d.dueDate.toString()).orElse(null);
        //System.out.println(this.deadline); -> 2025-02-01T14:00
        this.status = source.getStatus().toString();
        this.price = Optional.ofNullable(source.getPrice()).map(pr -> pr.fullPrice.toString()).orElse(null);
        //System.out.println(this.price); -> 2
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

        if (price != null && !Price.isValidPrice(price)) {
            throw new IllegalValueException((Price.MESSAGE_CONSTRAINTS));
        }
        final Price modelPrice = Optional.ofNullable(price).map(pr -> new Price(pr)).orElse(null);
        //System.out.println(modelPrice); -> Price : $2

        Project project = new Project(modelTitle, modelDeadline, modelPrice);

        if (status.equals("DONE")) {
            project.markAsDone();
        }

        return project;
    }

}
