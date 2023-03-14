package seedu.task.model.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

/**
 * Storage for all Event task currently in the TaskBook in a list
 */
public class EventList extends CategoricalTaskList {
    private List<Event> taskList = new ArrayList<Event>();

    /**
     * Filters and stores events which are valid
     * @param internalList all events that should be included in the planning algorithm
     * @param today date command is ran
     */
    public EventList(ObservableList<Task> internalList, LocalDate today) {
        for (int i = 0; i < internalList.size(); i++) {
            isCorrectType(internalList.get(i), today);
        }
    }

    /**
     * Empty constructor used for testing.
     * Should not be called in normal program execution.
     */
    public EventList() {}

    public Event get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    @Override
    public boolean isCorrectType(Task t, LocalDate today) {
        if (t.isEvent()) {
            Event e = (Event) t;
            if (today.isBefore(e.getTo().getDate()) || today.isEqual(e.getTo().getDate())) {
                taskList.add(e);
                return true;
            }
        }
        return false;
    }
}
