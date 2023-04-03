package vimification.model;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import vimification.model.task.Task;

public class UiTaskList {

    private final ObservableList<Task> allTasks;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;
    private final TaskListRef ref;

    public UiTaskList(
            ObservableList<Task> allTasks,
            FilteredList<Task> filterTasks,
            SortedList<Task> sortedTasks,
            TaskListRef ref) {
        // if (filterTasks.isInTransformationChain(sortedTasks)) {
        // throw new IllegalArgumentException("Lists should be in the same chain");
        // }
        this.allTasks = allTasks;
        this.filteredTasks = filterTasks;
        this.sortedTasks = sortedTasks;
        this.ref = ref;
    }

    public ObservableList<Task> getInternalList() {
        return sortedTasks;
    }

    public Predicate<? super Task> getPredicate() {
        return filteredTasks.getPredicate();
    }

    public void setPredicate(Predicate<? super Task> predicate) {
        filteredTasks.setPredicate(predicate);
        changeToReadOnly();
    }

    public Comparator<? super Task> getComparator() {
        return sortedTasks.getComparator();
    }

    public void setComparator(Comparator<? super Task> comparator) {
        sortedTasks.setComparator(comparator);
        changeToReadOnly();
    }

    public void refresh() {
        setComparator(null);
        setPredicate(null);
        changeToWritable();
    }

    private void changeToReadOnly() {
        ref.setTaskList(sortedTasks);
        ref.setReadOnly(true);
    }

    private void changeToWritable() {
        ref.setTaskList(allTasks);
        ref.setReadOnly(false);
    }
}
