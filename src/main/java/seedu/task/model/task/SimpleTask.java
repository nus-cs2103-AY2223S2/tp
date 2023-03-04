package seedu.task.model.task;

import java.util.HashSet;
import java.util.Set;

import seedu.task.model.tag.Tag;

/**
 * Represents a Simple Task without dates in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SimpleTask extends Task {

    // Identity fields
    private Name name;
    private Description description;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public SimpleTask(Name name, Description description, Set<Tag> tags) {
        super(name, description, tags);
    }

}
