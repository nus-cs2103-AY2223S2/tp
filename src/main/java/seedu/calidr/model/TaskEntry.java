package seedu.calidr.model;

import com.calendarfx.model.Entry;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.Task;

/**
 * Custom CalendarFX entry representing a Calidr Task. This contains all the required
 * information for a single Task to interface with CalendarfX.
 */
public class TaskEntry extends Entry<Task> {

    private final ObjectProperty<Priority> priority = new SimpleObjectProperty<>(this, "priority");
    private final BooleanProperty isDone = new SimpleBooleanProperty(this, "isDone");

    public final ObjectProperty<Priority> priorityProperty() {
        return priority;
    }

    public final Priority getPriority() {
        return priority.get();
    }

    public final void setPriority(Priority priority) {
        this.priority.set(priority);
    }

    public final BooleanProperty isDoneProperty() {
        return isDone;
    }

    public final boolean getIsDone() {
        return isDone.get();
    }

    public final void setIsDone(boolean isDone) {
        this.isDone.set(isDone);
    }

    /**
     * Creates a new TaskEntry object to support recurrence.
     */
    @Override
    public TaskEntry createRecurrence() {
        TaskEntry recurrence = new TaskEntry();
        recurrence.setPriority(getPriority());
        recurrence.setIsDone(getIsDone());
        return recurrence;
    }

    @Override
    public String toString() {
        return "Task Entry: " + getTitle() + " " + getPriority() + " " + getIsDone();
    }
}
