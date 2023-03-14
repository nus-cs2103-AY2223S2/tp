package seedu.task.model.task;

import java.time.LocalDate;

/**
 * Represents a list of Tasks from the TaskBook
 */
public abstract class CategoricalTaskList {
    abstract boolean isCorrectType(Task t, LocalDate d);
}
