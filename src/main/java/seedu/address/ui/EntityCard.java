package seedu.address.ui;

import java.io.File;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.entity.Entity;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class EntityCard extends UiPart<Region> {

    private static final String FXML = "EntityListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Entity entity;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label classification;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView logoImageView;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public EntityCard(Entity entity, int displayedIndex) {
        super(FXML);
        this.entity = entity;
        name.setText(entity.getName().fullName);
        classification.setText(entity.getClass().getSimpleName());
        entity.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        setLogo();
    }

    private void setLogo() {
        File file;
        switch (entity.getClass().getSimpleName()) {
        case "Character":
            file = new File("src/main/resources/images/Monster4.png");
            break;
        case "Mob":
            file = new File("src/main/resources/images/Ghost1.png");
            break;
        case "Item":
            file = new File("src/main/resources/images/Potion1.png");
            break;
        default:
            file = new File("src/main/resources/images/address_book_32.png");
        }
        Image image = new Image(file.toURI().toString());
        logoImageView.setImage(image);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntityCard)) {
            return false;
        }

        // state check
        EntityCard card = (EntityCard) other;
        return entity.equals(card.entity);
    }
}
