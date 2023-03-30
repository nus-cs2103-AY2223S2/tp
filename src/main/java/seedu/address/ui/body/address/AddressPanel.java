package seedu.address.ui.body.address;

import javafx.beans.property.ReadOnlyObjectProperty;
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
     */
    public AddressPanel(Logic logic, ResultDisplay resultDisplay) {
        super(FXML);
        personDetailPanel = new PersonDetailPanel();
        personDetailPanelPlaceholder.getChildren().add(personDetailPanel.getRoot());
        personListPanel = new PersonListPanel(logic, resultDisplay);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        bindSelectedPerson(logic.getSelectedPerson());
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    private void bindSelectedPerson(ReadOnlyObjectProperty<Person> selectedPerson) {
        selectedPerson.addListener((observable, oldValue, newValue) -> {
            personListPanel.setSelectedPerson(newValue);
            personDetailPanel.setSelectedPerson(newValue, personListPanel.getSelectedIndex());
        });
    }
}
