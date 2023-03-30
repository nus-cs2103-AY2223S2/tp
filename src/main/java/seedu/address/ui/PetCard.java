package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.pet.Pet;

/**
 * An UI component that displays information of a {@code Pet}.
 */
public class PetCard extends UiPart<Region> {

    private static final String FXML = "PetListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Pet pet;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label oname;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label amountDue;
    @FXML
    private Pane amountDuePane;
    @FXML
    private Label deadline;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PetCode} with the given {@code Pet} and index to display.
     */
    public PetCard(Pet pet, int displayedIndex) {
        super(FXML);
        this.pet = pet;
        oname.setText(pet.getOwnerName().fullName);
        id.setText(displayedIndex + ". ");
        name.setText(pet.getName().fullName);
        phone.setText(pet.getPhone().value);
        address.setText(pet.getAddress().value);
        email.setText(pet.getEmail().value);

        LocalDateTime arrival = pet.getTimeStamp();
        System.out.println(arrival);
        Label amountDueValueLabel = new Label(pet.getCost().toString());
        amountDueValueLabel.setLayoutX(amountDue.getLayoutX() + amountDue.prefWidth(-1));
        amountDueValueLabel.setLayoutY(amountDue.getLayoutY());
        amountDueValueLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px;");
        amountDuePane.getChildren().add(amountDueValueLabel);

        deadline.setText(pet.getDeadline().toString());
        pet.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PetCard)) {
            return false;
        }

        // state check
        PetCard card = (PetCard) other;
        return id.getText().equals(card.id.getText())
                && pet.equals(card.pet);
    }
}
