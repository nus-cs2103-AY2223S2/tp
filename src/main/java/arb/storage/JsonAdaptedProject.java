package arb.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import arb.commons.core.LogsCenter;
import arb.commons.exceptions.IllegalValueException;
import arb.model.AddressBook;
import arb.model.client.Name;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.Title;
import arb.model.tag.Tag;

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
    private final String linkedClient;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("title") String title,
            @JsonProperty("deadline") String deadline,
            @JsonProperty("status") String status,
            @JsonProperty("price") String price,
            @JsonProperty("linkedClient") String linkedClient,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.deadline = Optional.ofNullable(deadline).orElse(null);
        this.price = Optional.ofNullable(price).orElse(null);
        this.linkedClient = Optional.ofNullable(linkedClient).orElse(null);
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        logger.info("Logging project: " + source);
        this.title = source.getTitle().fullTitle;
        this.deadline = Optional.ofNullable(source.getDeadline()).map(d -> d.dueDate.toString()).orElse(null);
        this.status = Boolean.toString(source.getStatus().getStatus());
        this.price = Optional.ofNullable(source.getPrice()).map(pr -> pr.getPrice().toString()).orElse(null);
        this.linkedClient = Optional.ofNullable(source.getClientName()).orElse(null);
        this.tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType(AddressBook ab) throws IllegalValueException {
        final List<Tag> projectTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            projectTags.add(tag.toModelType());
        }

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

        final Set<Tag> modelTags = new HashSet<>(projectTags);

        Project project = new Project(modelTitle, modelDeadline, modelPrice, modelTags);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (Boolean.parseBoolean(status)) {
            project.markAsDone();
        }

        if (linkedClient != null && !Name.isValidName(linkedClient)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (linkedClient != null && !ab.hasClient(new Name(linkedClient))) {
            throw new IllegalValueException("This client is not found in the addressbook!");
        }
        if (linkedClient != null) {
            ab.linkProjectToClient(new Name(linkedClient), project);
        }

        return project;
    }

}
