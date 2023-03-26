package vimification.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.model.task.Task;

public class DisplayTaskList {
    private final ObservableList<Task> displayList = FXCollections.observableArrayList();

    public ObservableList<Task> getReadOnly() {
        return FXCollections.unmodifiableObservableList(displayList);
    }

    public String getReadOnlyString() {
        ObservableList<Task> readOnlyList = FXCollections.unmodifiableObservableList(displayList);
        String result = "";
        int n = readOnlyList.size();
        for (int i = 0; i < n; i++) {
            String prefix = i + 1 < 10 ? "0" : "";
            result += prefix + (i + 1) + ". " + get(i).toString();
            if (i < n - 1) {
                result += "\n";
            }
        }
        return result;
    }

    public int size() {
        return displayList.size();
    }

    public Task get(int idx) {
        return displayList.get(idx);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            String prefix = i + 1 < 10 ? "0" : "";
            result += prefix + (i + 1) + ". " + get(i).toString();
            if (i < size() - 1) {
                result += "\n";
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DisplayTaskList // instanceof handles nulls
                        && displayList.equals(((DisplayTaskList) other).displayList));
    }
}
