package seedu.address.model.mapping;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Relationship;
import seedu.address.model.shared.Id;

/**
 * A many-to-many relationship mapping for task and person
 * Take note that both personId and taskId must refer to a
 * valid Object.
 * TODO BUT THIS CODE HAS NOT IMPLEMENT THE LOGIC TO VERIFY THE TIGHT BOUND OF VALID RELATIONSHIP
 */
public class AssignTask implements Relationship<AssignTask> {
    private final Id personId;
    private final Id taskId;

    /**
     * Every field must be present and not null.
     */
    public AssignTask(Id personId, Id taskId) {
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
    public boolean hasSameId(AssignTask obj) {
        return equals(obj);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignTask)) {
            return false;
        }

        AssignTask otherAssignTask = (AssignTask) other;
        return otherAssignTask.getPersonId().equals(getPersonId())
            && otherAssignTask.getTaskId().equals(getTaskId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, taskId);
    }

    @Override
    public boolean isSame(AssignTask obj) {
        return equals(obj);
    }

    @Override
    public String toString() {
        return String.format("Person Id: %1$s\nAssigned Task Id: %2$s", personId, taskId);
    }

}
