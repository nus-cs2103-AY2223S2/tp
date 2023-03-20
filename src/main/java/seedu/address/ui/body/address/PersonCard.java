package seedu.address.ui.body.address;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {
    private static final String FXML = "body/address/PersonListCard.fxml";
    private static final int EMPTY_INDEX = -1;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private VBox summaryContainer;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView star;

    private Person person;
    private int index;

    /**
     * Creates an empty {@code PersonCard}.
     */
    public PersonCard() {
        this(null, EMPTY_INDEX);
    }

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        setPerson(person, displayedIndex);
    }

    public Person getPerson() {
        return person;
    }

    public int getIndex() {
        return index;
    }

    public void setPerson(Person person, int displayedIndex) {
        this.person = person;
        this.index = displayedIndex;
        if (person == null) {
            return;
        }

        id.setText(String.valueOf(displayedIndex));
        star.setVisible(person.getIsFavorite().getFavoriteStatus());

        ObservableList<Node> summary = summaryContainer.getChildren();
        summary.clear();

        name.setText(person.getName().toString());
        summary.add(name);

        phone.setText(person.getPhone().toString());
        if (hasPhone()) {
            summary.add(phone);
        }

        ObservableList<Node> tagsList = tags.getChildren();
        tagsList.clear();
        tagsList.addAll(person.getTags().stream()
                .sorted(Comparator.comparing(Object::toString))
                .map(tag -> new Label(tag.tagName))
                .collect(Collectors.toList()));
        if (hasTags()) {
            summary.add(tags);
        }
    }

    private boolean hasPhone() {
        return phone.getText() != null && !phone.getText().isBlank();
    }

    private boolean hasTags() {
        return !tags.getChildren().isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return index == card.index
                && Objects.equals(person, card.person);
    }
}
