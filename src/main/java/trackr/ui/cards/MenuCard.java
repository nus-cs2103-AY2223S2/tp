package trackr.ui.cards;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackr.model.menu.MenuItem;
import trackr.ui.UiPart;

//@@author arkarsg-reused
/**
 * An UI component that displays information of a {@code Order}.
 */
public class MenuCard extends UiPart<Region> {

    private static final String FXML = "MenuListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final MenuItem menuItem;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label itemName;
    @FXML
    private Label itemPrice;
    @FXML
    private Label itemCost;
    @FXML
    private Label itemProfit;

    /**
     * Creates a {@code MenuCard} with the given {@code MenuItem} and index to display.
     */
    public MenuCard(MenuItem menuItem, int displayedIndex) {
        super(FXML);
        this.menuItem = menuItem;
        id.setText(displayedIndex + ". ");
        itemName.setText(menuItem.getItemName().getName());
        itemPrice.setText(menuItem.getItemPrice().toString());
        itemCost.setText(menuItem.getItemCost().toString());
        itemProfit.setText(menuItem.getItemProfit().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MenuCard)) {
            return false;
        }

        // state check
        MenuCard card = (MenuCard) other;
        return id.getText().equals(card.id.getText())
                && menuItem.equals(card.menuItem);
    }
}
