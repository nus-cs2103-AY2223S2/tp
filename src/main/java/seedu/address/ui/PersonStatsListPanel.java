package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;
import seedu.address.model.stats.PersonStats;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of persons and their average scores.
 */
public class PersonStatsListPanel extends UiPart<Region> {
    private static final String FXML = "PersonStatsListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonStatsListPanel.class);
    private final Logic logic;

    @FXML
    private ListView<PersonStats> personStatsListView;

    private ObservableList<PersonStats> personStatsList;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonStatsListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        personStatsList = createPersonStatsList(logic.getFilteredPersonList(), logic.getFilteredTaskList());
        personStatsListView.setItems(personStatsList);
        personStatsListView.setCellFactory(listView -> new PersonStatsListViewCell());

        logic.getFilteredPersonList().addListener((ListChangeListener<Person>) change -> updatePersonStatsList());
        logic.getFilteredTaskList().addListener((ListChangeListener<Task>) change -> updatePersonStatsList());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PersonStats} using a {@code PersonStatsCard}.
     */
    class PersonStatsListViewCell extends ListCell<PersonStats> {
        @Override
        protected void updateItem(PersonStats personStats, boolean empty) {
            super.updateItem(personStats, empty);

            if (empty || personStats == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonStatsCard(personStats, getIndex() + 1).getRoot());
            }
        }
    }

    private ObservableList<PersonStats> createPersonStatsList(
            ObservableList<Person> personList,
            ObservableList<Task> taskList
    ) {
        List<PersonStats> personStatsList = new ArrayList<>();

        for (Person person : personList) {
            List<Task> personTasks = taskList.stream()
                    .filter(task -> task.getPersonAssignedName() != null
                            && task.getPersonAssignedName().equals(person.getName().toString()))
                    .collect(Collectors.toList());

            int tasksAssigned = personTasks.size();
            int tasksCompleted = (int) personTasks.stream()
                    .filter(Task::isDone)
                    .count();
            double averageScore = personTasks.stream()
                    .filter(task -> task.getScore() != null)
                    .mapToDouble(task -> task.getScore().getValue())
                    .average()
                    .orElse(Double.NaN);

            double finalScore = round(averageScore, 1);

            personStatsList.add(new PersonStats(person, tasksAssigned, tasksCompleted, finalScore));
        }

        return FXCollections.observableArrayList(personStatsList);
    }

    private void updatePersonStatsList() {
        personStatsList = createPersonStatsList(logic.getFilteredPersonList(), logic.getFilteredTaskList());
        personStatsListView.setItems(personStatsList);
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
