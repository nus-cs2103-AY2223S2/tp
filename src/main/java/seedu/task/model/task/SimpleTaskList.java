package seedu.task.model.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;

/**
 * List of SimpleTask from TaskBook
 */
public class SimpleTaskList extends CategoricalTaskList {
    private List<SimpleTask> taskList = new ArrayList<SimpleTask>();

    /**
     * Regular constructor that filters and retains SimpleTasks.
     * @param internalList all entries in the taskbook
     * @param today date command is ran
     */
    public SimpleTaskList(ObservableList<Task> internalList, LocalDate today) {
        for (int i = 0; i < internalList.size(); i++) {
            isCorrectType(internalList.get(i), today);
        }

        Collections.sort(taskList, new SimpleTaskListComparator());
    }

    /**
     * Empty constructor for use in testing.
     * Should not be called in normal execution.
     */
    public SimpleTaskList() {}

    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves a SimpleTask stored at indicated index
     * @param index location to retrieve from
     * @return SimpleTask stored at location
     */
    public SimpleTask get(int index) {
        return taskList.get(index);
    }

    /**
     * Checks if Task from records is a SimpleTask
     * @param t Current task to check
     * @param d Date command is ran
     * @return boolean value indicating if it is a simpleTask
     */
    @Override
    public boolean isCorrectType(Task t, LocalDate d) {
        if (t.isSimpleTask()) {
            SimpleTask s = (SimpleTask) t;
            taskList.add(s);
            return true;
        }
        return false;
    }

    /**
     * Utility class to help order tasks in descending order of effort required.
     */
    public static class SimpleTaskListComparator implements Comparator<SimpleTask> {
        @Override
        public int compare(SimpleTask o1, SimpleTask o2) {
            int position = (int) (o2.getEffort().getEffort() - o1.getEffort().getEffort());
            return position;
        }
    }
}
