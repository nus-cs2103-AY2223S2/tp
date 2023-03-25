package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;



/**
 * Controller for a help page
 */
public class QuickstartWindow extends UiPart<Stage> {

    private static final String FXML = "QuickstartWindow.fxml";

    @FXML
    private Pagination quickPagination;

    @FXML
    private ImageView quickImageView;

    /**
     * Creates a new QuickstartWindow.
     */
    public QuickstartWindow() {
        this(new Stage());
    }

    /**
     * Creates a new QuickstartWindow.
     *
     * @param root Stage to use as the root of the QuickstartWindow.
     */
    public QuickstartWindow(Stage root) {
        super(FXML, root);
//        quickPagination.setPageFactory((Integer pageIndex) -> pageImageSet(pageIndex));
    }

    public ImageView pageImageSet(int pageIndex) {
        quickImageView.setImage(QuickstartImages.giveImage(pageIndex));
        return quickImageView;
    }

    /**
     * Shows the QuickstartWindow.
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the QuickstartWindow is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the QuickstartWindow.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the QuickstartWindow.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}