package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private AddPatientWindow addPatientWindow;

    @FXML
    private ListView<Person> personListView;
    @FXML
    private Button addPatientButton;
    private MainWindow mainWindow;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, MainWindow mainWindow,
                           CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        this.addPatientWindow = new AddPatientWindow(commandExecutor, new Stage());
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.mainWindow = mainWindow;
    }

    /**
     * Get the personListView of the panel.
     * @return The personListView.
     */
    public ListView<Person> getPersonListView() {
        return personListView;
    }

    /**
     * Displays the detailed information of the patient.
     *
     * @throws CommandException If an error occurs during execution of the command.
     * @throws ParseException If a parse error occurs during execution of the command.
     */
    @FXML
    private void displayDetail() throws CommandException, ParseException {
        try {
            int targetIndex = personListView.getSelectionModel().getSelectedIndex() + 1;
            int size = personListView.getItems().size();
            if (targetIndex > 0 && targetIndex <= size) {
                mainWindow.handleClickInPersonListPanel("show " + targetIndex);
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + "Something wrong happened when clicking the person.");
        }
    }

    /**
     * Show the AddPatientWindow or requests that this AddPatientWindow get the input focus.
     */
    @FXML
    private void showAddPatientWindow() {
        if (!addPatientWindow.isShowing()) {
            addPatientWindow.showAddPatientWindow();
        } else {
            addPatientWindow.requestFocus();
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
