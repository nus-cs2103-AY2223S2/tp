package vimification.model;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import vimification.model.task.Task;

/**
 * Instances of this interface are responsible for controlling the displayed list.
 */
public interface UiTaskList {

    /**
     * Gets an unmodifiable view of the displayed list.
     *
     * @return an unmodifiable view of the displayed list
     */
    public ObservableList<Task> getUiSource();

    /**
     * Gets the predicate used to select tasks to display.
     *
     * @return the predicate used to select tasks to display
     */
    public Predicate<? super Task> getPredicate();

    /**
     * Sets the predicate used to select tasks to display.
     *
     * @param predicate the predicate used to select tasks to display
     */
    public void setPredicate(Predicate<? super Task> predicate);

    /**
     * Gets the comparator used to order the displayed tasks.
     *
     * @return the comparator used to order the displayed tasks
     */
    public Comparator<? super Task> getComparator();

    /**
     * Sets the comparator used to order the displayed tasks.
     *
     * @param comparator the comparator used to order the displayed tasks
     */
    public void setComparator(Comparator<? super Task> comparator);
}
