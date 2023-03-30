package vimification.model;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import vimification.model.task.Task;

public class UiTaskList {

    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;

    public UiTaskList(FilteredList<Task> filterTasks, SortedList<Task> sortedTasks) {
        if (sortedTasks.isInTransformationChain(filterTasks)) {
            throw new IllegalArgumentException("Lists should be in the same chain");
        }
        this.filteredTasks = filterTasks;
        this.sortedTasks = sortedTasks;
    }

    public ObservableList<Task> getInternalList() {
        return sortedTasks;
    }

    public Predicate<? super Task> getPredicate() {
        return filteredTasks.getPredicate();
    }

    public void setPredicate(Predicate<? super Task> predicate) {
        filteredTasks.setPredicate(predicate);
    }

    public Comparator<? super Task> getComparator() {
        return sortedTasks.getComparator();
    }

    public void setComparator(Comparator<? super Task> comparator) {
        sortedTasks.setComparator(comparator);
    }
}
