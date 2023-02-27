package seedu.address.ui.body.address;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays detailed information of a {@code Person}.
 */
public class PersonDetailPanel extends UiPart<Region> {
    private static final String FXML = "body/address/PersonDetailPanel.fxml";

    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox dataContainer;

    /**
     * Creates a blank {@code PersonDetailPanel}.
     */
    public PersonDetailPanel() {
        super(FXML);
        clearPerson();
    }

    public void setPerson(Person person) {
        clearPerson();
        if (person == null) {
            return;
        }

        name.setText(person.getName().toString());
        tags.getChildren().addAll(getTagLabels(person));
        dataContainer.getChildren().addAll(getDataCardCollection(person));
    }

    /**
     * Sets the index of the {@code Person} to be displayed to the user.
     * If the given {@code index} is less than 1, it is assumed that
     * no {@code Person} is selected.
     *
     * @param index 1-based index of the corresponding {@code Person}.
     */
    public void setDisplayedIndex(int index) {
        if (index < 1) {
            id.setText("Select a contact.");
        } else {
            id.setText(String.format("Index: %d", index));
        }
    }

    /**
     * Empties the fields, resulting in a blank {@code PersonDetailPanel}.
     */
    public void clearPerson() {
        setDisplayedIndex(-1);

        name.setText(null);
        tags.getChildren().clear();
        dataContainer.getChildren().clear();
        scrollContainer.setVvalue(0);
    }

    private Collection<Label> getTagLabels(Person person) {
        return person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .map(tag -> tag.tagName)
                .map(Label::new)
                .collect(Collectors.toList());
    }

    private Collection<Region> getDataCardCollection(Person person) {
        return Stream.of(
                new PersonDetailCard.DetailCardData("Phone", person.getPhone().toString()),
                new PersonDetailCard.DetailCardData("Address", person.getAddress().toString()),
                new PersonDetailCard.DetailCardData("Email", person.getEmail().toString()))
                .map(PersonDetailCard::new)
                .map(PersonDetailCard::getRoot)
                .collect(Collectors.toList());
    }
}
