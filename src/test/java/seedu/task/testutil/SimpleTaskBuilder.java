package seedu.task.testutil;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import seedu.task.model.tag.Tag;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Name;
import seedu.task.model.task.SimpleTask;
import seedu.task.model.task.Subtask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueSubtaskList;
import seedu.task.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class SimpleTaskBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    private Name name;
    private Description description;
    private Effort effort;
    private Set<Tag> tags;
    private Duration alertWindow;
    private UniqueSubtaskList subtasks;

    /**
     * Creates a {@code SimpleTaskBuilder} with the default details.
     */
    public SimpleTaskBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description();
        tags = new HashSet<>();
        effort = new Effort();
        subtasks = new UniqueSubtaskList();
    }

    /**
     * Initializes the SimpleTaskBuilder with the data of {@code taskToCopy}.
     */
    public SimpleTaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        tags = new HashSet<>(taskToCopy.getTags());
        effort = taskToCopy.getEffort();
        subtasks = taskToCopy.getSubtasksAsOriginal();
    }

    //@@author lywich-reused
    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public SimpleTaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public SimpleTaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public SimpleTaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public SimpleTaskBuilder withDescription() {
        this.description = new Description();
        return this;
    }

    //@@author joyngjr
    /**
     * Sets the {@code Effort} of the {@code Task} that we are building.
     */
    public SimpleTaskBuilder withEffort(long e) {
        this.effort = new Effort(e);
        return this;
    }

    //@@author PROGRAMMERHAO
    /**
     * Sets the {@code UniqueSubtaskList}of the {@code Task} that we are building.
     * @param subtask A pre-built subtask
     * @return A SimpleTaskBuilder
     */
    public SimpleTaskBuilder withSubtasks(Subtask subtask) {
        this.subtasks.addSubtask(subtask);
        return this;
    }

    //@@author HUGGENGUGGEN
    /**
     * Sets the {@code alertWindow} of the {@code Task} that we are building.
     */
    public SimpleTaskBuilder withAlertWindow(String alertWindow) {
        this.alertWindow = Duration.ofHours(Long.valueOf(alertWindow));
        return this;
    }

    //@@author lywich-reused
    public Task build() {
        return new SimpleTask(name, description, tags, effort, subtasks.asInternalList());
    }

    //@@author joyngjr
    public Task buildDefault() {
        return new SimpleTask(name, description, tags, null);
    }

}
