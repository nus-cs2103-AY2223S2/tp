package seedu.task.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.model.Planner;
import seedu.task.model.calendar.MonthlyPlan;
import seedu.task.model.task.exceptions.DuplicateTaskException;
import seedu.task.model.task.exceptions.TaskNotFoundException;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of
 * tasks uses Task#isSameTask(Task) for equality so as to ensure that the task being added or updated is
 * unique in terms of identity in the UniqueTaskList. However, the removal of a task uses Task#equals(Object) so
 * as to ensure that the task with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */

//@@author
public class UniqueTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }


    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    //@@author lywich
    /**
     * Sorts the contents of this list.
     */
    public void sort() {
        FXCollections.sort(internalList, Task::compareTo);
    }

    //@@author
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    //@@author joyngjr
    /**
     * Main algorithm that supports the planning function
     * @param workload amount of effort user wants to put in per day in the workplan.
     */
    public void plan(long workload, Planner planner) {
        LocalDate currentDate = java.time.LocalDate.now();
        SimpleTaskList simpleTasks = filterSimpleTasks(currentDate);
        EventList events = filterEvents(currentDate);
        DeadlineList deadlines = filterDeadlines(currentDate);

        MonthlyPlan plan = new MonthlyPlan(workload, currentDate);
        allocate(plan, events, deadlines, simpleTasks);

        planner.setDailyPlans(plan.getDailyPlans());
    }

    //@@author
    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueTaskList // instanceof handles nulls
            && internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        int size = tasks.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    //@@author joyngjr
    private SimpleTaskList filterSimpleTasks(LocalDate d) {
        return new SimpleTaskList(this.internalList, d);
    }

    private DeadlineList filterDeadlines(LocalDate d) {
        return new DeadlineList(this.internalList, d);
    }

    private EventList filterEvents(LocalDate d) {
        return new EventList(this.internalList, d);
    }

    private void allocate(MonthlyPlan plan, EventList events, DeadlineList deadlines, SimpleTaskList simpleTasks) {
        plan.allocateEvents(events);
        plan.allocateDeadlines(deadlines);
        plan.allocateSimpleTasks(simpleTasks);
    }
}
