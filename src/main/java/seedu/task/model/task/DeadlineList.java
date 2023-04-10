package seedu.task.model.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

/**
 * Storage for all Deadline task currently in the TaskBook in a list
 */
public class DeadlineList extends CategoricalTaskList {
    private List<Deadline> taskList = new ArrayList<Deadline>();

    /**
     * Constructor takes in all tasks from the taskbook and performs a check on them.
     * Copies tasks which are of type Deadline which due date is in the future.
     * @param internalList all tasks from taskbook
     * @param today date command is ran
     */
    public DeadlineList(ObservableList<Task> internalList, LocalDate today) {
        for (int i = 0; i < internalList.size(); i++) {
            isCorrectType(internalList.get(i), today);
        }
    }

    /**
     * Empty constructor used for testing purposes.
     * Should not be called in regular program execution.
     */
    public DeadlineList() {}

    /**
     * Returns the number of tasks to be allocated in the planning algorithm.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves a Deadline stored at indicated index.
     * @param index location to retrieve from
     * @return Deadline stored at location
     */
    public Deadline getDeadlineFromIndex(int index) {
        return taskList.get(index);
    }

    @Override
    public boolean isCorrectType(Task t, LocalDate today) {
        if (t.isDeadline()) {
            Deadline d = (Deadline) t;

            if (d.isValidDate(today)) {
                taskList.add(d);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof DeadlineList) {
            DeadlineList list = (DeadlineList) other;
            return taskList.equals(list.taskList);
        } else {
            return false;
        }
    }
}
