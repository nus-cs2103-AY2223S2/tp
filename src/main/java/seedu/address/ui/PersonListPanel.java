package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;


/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(Logic logic) {
        super(FXML);
        Logger logger = LogsCenter.getLogger(PersonListPanel.class);
        logger.info("Init PersonListPanel");
        personListView.setItems(logic.getFilteredPersonList());
        personListView.setCellFactory(listView -> new PersonListViewCell(logic.getOfficeConnectModel()));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListViewCell extends ListCell<Person> {

        private final OfficeConnectModel officeConnectModel;
        public PersonListViewCell(OfficeConnectModel officeConnectModel) {
            this.officeConnectModel = officeConnectModel;
        }

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);
            Logger logger = LogsCenter.getLogger(PersonListPanel.class);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1) { // Single Click
                        logger.info("An item selected: " + PersonListViewCell.super.getItem().toString());

                        ObservableList<AssignTask> assignTasks = officeConnectModel.getAssignTaskModelManager()
                            .getFilteredItemList().filtered(personTask ->
                                personTask.getPersonId().equals(PersonListViewCell.super.getItem().getId()));

                        officeConnectModel.getTaskModelManager().updateFilteredItemList(task -> assignTasks.stream()
                            .anyMatch(personTask -> personTask.getTaskId().equals(task.getId())));
                    } else if (event.getClickCount() == 2) { //Double Click
                        logger.info("A double Click triggered");

                    }
                });
            }
        }
    }

}
