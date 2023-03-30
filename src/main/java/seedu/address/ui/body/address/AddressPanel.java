package seedu.address.ui.body.address;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;
import seedu.address.ui.result.ResultDisplay;

/**
 * Panel for the address book.
 */
public class AddressPanel extends UiPart<Region> {
    private static final String FXML = "body/address/AddressPanel.fxml";

    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane personDetailPanelPlaceholder;

    private final PersonDetailPanel personDetailPanel;
    private final PersonListPanel personListPanel;

    /**
     * Creates an {@code AddressPanel}.
     *
     * @param personObservableList Observable list of {@code Person}s to display.
     */
    public AddressPanel(ObservableList<Person> personObservableList, Logic logic, ResultDisplay resultDisplay) {
        super(FXML);
        personDetailPanel = new PersonDetailPanel();
        personDetailPanelPlaceholder.getChildren().add(personDetailPanel.getRoot());
        personListPanel = new PersonListPanel(personObservableList, personDetailPanel, logic, resultDisplay);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Gets the index to display of specified person
     * @param person
     * @return (int) index
     */
    public int getIndexOfSelectedPerson(Person person) {
        return personListPanel.getIndexOfSelectedPerson(person);
    }

    /**
     * Sets selected person's details in person detail panel
     * @param person
     */
    public void setSelectedPerson(Person person) {
        int indexOfPerson = this.getIndexOfSelectedPerson(person);
        this.personDetailPanel.setSelectedPerson(person, indexOfPerson);
        this.personListPanel.bindSelectedIndex(indexOfPerson);
    }

}
