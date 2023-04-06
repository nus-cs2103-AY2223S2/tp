package vimification.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import vimification.model.task.Task;


public class TaskList implements LogicTaskList, UiTaskList {

    private final ObservableList<Task> allTasks;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;

    public TaskList(Collection<? extends Task> tasks) {
        this.allTasks = FXCollections.observableArrayList(tasks);
        this.filteredTasks = new FilteredList<>(allTasks);
        this.sortedTasks = new SortedList<>(filteredTasks);
    }

    public TaskList() {
        this(List.of());
    }

    @Override
    public int size() {
        return allTasks.size();
    }

    @Override
    public boolean isEmpty() {
        return allTasks.isEmpty();
    }

    @Override
    public Task get(int index) {
        return allTasks.get(index);
    }

    @Override
    public void set(int index, Task task) {
        allTasks.set(index, task);
    }

    @Override
    public Task remove(int index) {
        return allTasks.remove(index);
    }

    @Override
    public void add(Task task) {
        allTasks.add(task);
    }

    @Override
    public void add(int index, Task task) {
        allTasks.add(index, task);
    }

    @Override
    public Task removeLast() {
        return allTasks.remove(allTasks.size() - 1);
    }

    @Override
    public int getLogicSourceIndex(int index) {
        return sortedTasks.getSourceIndexFor(allTasks, index);
    }

    @Override
    public List<Task> getLogicSource() {
        return Collections.unmodifiableList(allTasks);
    }

    @Override
    public Predicate<? super Task> getPredicate() {
        return filteredTasks.getPredicate();
    }

    @Override
    public Comparator<? super Task> getComparator() {
        return sortedTasks.getComparator();
    }

    @Override
    public void setPredicate(Predicate<? super Task> predicate) {
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void setComparator(Comparator<? super Task> comparator) {
        sortedTasks.setComparator(comparator);
    }

    @Override
    public ObservableList<Task> getUiSource() {
        return sortedTasks;
    }
}
