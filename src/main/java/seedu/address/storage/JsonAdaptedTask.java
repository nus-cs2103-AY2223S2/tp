package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Score;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskDesc;
    private final String date;
    private final String comment;
    private final String score;
    private final boolean status;
    private final String personAssignedName;
    private final String personAssignedIndex;
    private final String personAssignedRole;
    private final String taskType;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDesc") String taskDesc, @JsonProperty("date") String date,
            @JsonProperty("comment") String comment, @JsonProperty("score") String score,
            @JsonProperty("status") boolean status,
            @JsonProperty("personAssignedName") String personAssignedName,
            @JsonProperty("personAssignedIndex") String personAssignedIndex,
            @JsonProperty("personAssignedRole") String personAssignedRole,
            @JsonProperty("taskType") String taskType) {
        this.taskDesc = taskDesc;
        this.date = date;
        this.comment = comment;
        this.score = score;
        this.status = status;
        this.personAssignedName = personAssignedName;
        this.personAssignedIndex = personAssignedIndex;
        this.personAssignedRole = personAssignedRole;
        this.taskType = taskType;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDesc = source.getDescription().toString();
        date = source.getDate();
        comment = source.getTaskComment() == null ? null : source.getTaskComment().toString();
        score = source.getScore() == null ? null : Integer.toString(source.getScore().getValue());
        status = source.isDone();
        personAssignedName = source.getPersonAssignedName() == null ? null : source.getPersonAssignedName();
        personAssignedIndex = source.getPersonAssignedIndex() == null ? null
            : Integer.toString(source.getPersonAssignedIndex().getZeroBased());
        personAssignedRole = source.getPersonAssignedRole() == null ? null : source.getPersonAssignedRole();
        taskType = source.getTaskType();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskDesc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidTaskDescription(taskDesc)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelTaskDesc = new TaskDescription(taskDesc);

        final String modelDate = date;
        final String modelTaskType = taskType;
        Task task = new Task(modelTaskDesc, modelDate, modelTaskType);
        if (comment != null) {
            final Comment modelComment = new Comment(comment);
            task.setTaskComment(modelComment);
        }

        if (score != null) {
            final Score modelScore = new Score(Integer.parseInt(score));
            task.setScore(modelScore);
        }

        if (personAssignedName != null) {
            final String modelPersonAssignedName = personAssignedName;
            final Index modelPersonAssignedIndex = Index.fromZeroBased(Integer.parseInt(personAssignedIndex));
            final String modelPersonAssignedRole = personAssignedRole;
            task.assignPerson(modelPersonAssignedIndex, modelPersonAssignedName, modelPersonAssignedRole);
        }

        final boolean modelStatus = status;
        task.setStatus(modelStatus);

        return task;
    }

}
