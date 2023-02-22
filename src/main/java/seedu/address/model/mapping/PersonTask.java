package seedu.address.model.mapping;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.shared.Id;

/**
 * A many-to-many relationship mapping for task and person
 */
public class PersonTask {
    private final Id personId;
    private final Id taskId;

    /**
     * Every field must be present and not null.
     */
    public PersonTask(Id personId, Id taskId) {
        requireAllNonNull(personId, taskId);
        this.personId = personId;
        this.taskId = taskId;
    }

    public Id getPersonId() {
        return personId;
    }

    public Id getTaskId() {
        return taskId;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonTask)) {
            return false;
        }

        PersonTask otherPersonTask = (PersonTask) other;
        return otherPersonTask.getPersonId().equals(getPersonId())
            && otherPersonTask.getTaskId().equals(getTaskId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, taskId);
    }

}
