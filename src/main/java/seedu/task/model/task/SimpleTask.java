package seedu.task.model.task;

import java.util.List;
import java.util.Set;

import seedu.task.model.tag.Tag;

/**
 * Represents a Simple Task without dates in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SimpleTask extends Task {

    /**
     * Every field must be present and not null.
     */
    public SimpleTask(Name name, Description description, Set<Tag> tags, Effort effort) {
        super(name, description, tags, effort);
    }

    /**
     * Overload the constructor to take in a {@code subtasks} field
     */
    public SimpleTask(Name name, Description description, Set<Tag> tags, Effort effort, List<Subtask> subtasks) {
        super(name, description, tags, effort, subtasks);
    }

    @Override
    public boolean isSimpleTask() {
        return true;
    }

    @Override
    public boolean isDeadline() {
        return false;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public boolean isComingUp() {
        return false;
    }

    /**
     * Compares tasks to get their position in a sorted list.
     * If task is a SimpleTask instance, the task with lesser tags should be higher up on the list.
     * If tags size is the same, compare the name lexicographically.
     * If task is a deadline or event, simpleTask should be higher up on the list.
     *
     * @param task the object to be compared.
     * @return int priority.
     */
    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline) {
            return -1;
        }
        if (task instanceof Event) {
            return -1;
        }
        if (this.tags.size() != task.tags.size()) {
            return Integer.compare(this.tags.size(), task.tags.size());
        }
        return this.name.compareTo(task.name);
    }

}
