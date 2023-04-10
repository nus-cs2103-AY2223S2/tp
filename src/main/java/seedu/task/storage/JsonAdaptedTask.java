package seedu.task.storage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Event;
import seedu.task.model.task.Name;
import seedu.task.model.task.SimpleTask;
import seedu.task.model.task.Subtask;
import seedu.task.model.task.Task;
import seedu.task.model.task.exceptions.InvalidEffortException;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    public static final String DEADLINE_EVENT_OVERLAP = "You can only declare a deadline or an event!";
    private final String name;
    private final String description;
    private final String hasDescription;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private String deadline = "";
    private String from = "";
    private String to = "";
    private long effort;

    private String alertWindow = "";

    private final List<JsonAdaptedSubtask> subtaskList = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name,
                           @JsonProperty("description") String description,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("from") String from,
                           @JsonProperty("to") String to,
                           @JsonProperty("effort") Long effort,
                           @JsonProperty("subtasks") List<JsonAdaptedSubtask> subtaskList,
                           @JsonProperty("alertWindow") String alertWindow,
                           @JsonProperty("hasDescription") String hasDescription) {
        this.name = name;
        this.description = description;
        this.hasDescription = String.valueOf(hasDescription);
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (deadline != null) {
            this.deadline = deadline;
        }
        if (from != null && to != null) {
            this.from = from;
            this.to = to;
        }
        this.effort = effort;
        if (alertWindow != null) {
            this.alertWindow = alertWindow;
        }
        if (subtaskList != null) {
            this.subtaskList.addAll(subtaskList);
        }

    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().fullName;
        description = source.getDescription().value;
        hasDescription = String.valueOf(source.hasDescription());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        subtaskList.addAll(source.getSubtasks().stream()
            .map(JsonAdaptedSubtask::new)
            .collect(Collectors.toList()));
        if (source instanceof Deadline) {
            deadline = ((Deadline) source).getDeadline().getValue();
        }
        if (source instanceof Event) {
            Event tmp = (Event) source;
            from = tmp.getFrom().getValue();
            to = tmp.getTo().getValue();
        }
        effort = source.getEffort().getEffort();
        alertWindow = String.valueOf(source.getAlertWindow().toHours());
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (description == null || hasDescription.equals("null")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        if (Boolean.parseBoolean(hasDescription) && !Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        final Description modelDescription;

        if (Boolean.parseBoolean(hasDescription)) {
            modelDescription = new Description(description);
        } else {
            modelDescription = new Description();
        }

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        if (!deadline.equals("") && (!from.equals("") || !to.equals(""))) {
            throw new IllegalValueException(String.format(DEADLINE_EVENT_OVERLAP));
        }

        if (!deadline.equals("") && !Date.isValidDate(deadline)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        if (alertWindow == null || alertWindow.isBlank()) {
            alertWindow = "24";
        }
        // if either from or to are non-empty check formatting
        if (!from.equals("") || !to.equals("")) {
            // if either of them are empty AND
            // either of them are invalid date formats (this will be true if either of them are empty)
            if ((from.equals("") || to.equals("")) && (!Date.isValidDate(from) || !Date.isValidDate(to))) {
                throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
            }
        }

        if (effort < 0) {
            throw new InvalidEffortException();
        }
        final Effort modelEffort = new Effort(this.effort);

        final List<Subtask> modelSubtaskList = new ArrayList<>();
        for (JsonAdaptedSubtask subtask : subtaskList) {
            modelSubtaskList.add(subtask.toModelType());
        }

        if (Date.isValidDate(deadline)) {
            // Deadline
            Date modelDeadline = new Date(deadline);
            Deadline deadline = new Deadline(modelName, modelDescription,
                modelTags, modelDeadline, modelEffort, modelSubtaskList);
            deadline.setAlertWindow(Duration.ofHours(Long.valueOf(alertWindow)));
            return deadline;

        }
        if (Date.isValidDate(from) && Date.isValidDate(to)) {
            // Event
            Date modelFrom = new Date(from);
            Date modelTo = new Date(to);

            Event event = new Event(modelName, modelDescription,
                modelTags, modelFrom, modelTo, modelEffort, modelSubtaskList);
            event.setAlertWindow(Duration.ofHours(Long.valueOf(alertWindow)));
            return event;
        }

        // Simple Task
        SimpleTask simpleTask = new SimpleTask(modelName, modelDescription, modelTags, modelEffort, modelSubtaskList);
        simpleTask.setAlertWindow(Duration.ofHours(Long.valueOf(alertWindow)));
        return simpleTask;

    }

}
