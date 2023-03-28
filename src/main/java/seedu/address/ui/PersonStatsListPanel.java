package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.stats.PersonStats;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of persons and their average scores.
 */
public class PersonStatsListPanel extends UiPart<Region> {
    private static final String FXML = "PersonStatsListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonStatsListPanel.class);

    @FXML
    private ListView<PersonStats> personStatsListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonStatsListPanel(ObservableList<Person> personList, ObservableList<Task> taskList) {
        super(FXML);
        ObservableList<PersonStats> personStatsList = createPersonStatsList(personList, taskList);
        personStatsListView.setItems(personStatsList);
        personStatsListView.setCellFactory(listView -> new PersonStatsListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PersonStats} using a {@code PersonCard}.
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

    private ObservableList<PersonStats> createPersonStatsList(ObservableList<Person> personList, ObservableList<Task> taskList) {
        List<PersonStats> personStatsList = new ArrayList<>();
    
        for (Person person : personList) {
            List<Task> personTasks = taskList.stream()
                    .filter(task -> task.getPersonAssignedName() != null && task.getPersonAssignedName().equals(person.getName().toString()))
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
    
            personStatsList.add(new PersonStats(person, tasksAssigned, tasksCompleted, averageScore));
        }
    
        return FXCollections.observableArrayList(personStatsList);
    }
}
