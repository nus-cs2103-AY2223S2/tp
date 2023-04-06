package vimification.model;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import vimification.model.task.Task;

public interface UiTaskList1 {

    public ObservableList<Task> getUiSource();

    public Predicate<? super Task> getPredicate();

    public void setPredicate(Predicate<? super Task> predicate);

    public Comparator<? super Task> getComparator();

    public void setComparator(Comparator<? super Task> comparator);
}
