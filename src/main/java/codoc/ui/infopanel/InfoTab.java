package codoc.ui.infopanel;

import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import codoc.model.person.Person;
import codoc.ui.MainWindow;
import codoc.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Panel containing detailed information about a person.
 */
public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoTab.class);
    private MainWindow.ClickListener clickListener;

    private DetailedInfo detailedInfo;

    @FXML
    private StackPane profilePicture;

    @FXML
    private Text name;

    @FXML
    private Label identity;

    @FXML
    private StackPane detailedInfoPlaceholder;

    /**
     * Creates a {@code InfoTab} with the given {@code protagonist} and {@code tab}.
     */
    public InfoTab(Person protagonist, String tab) {

        super(FXML);
        logger.info("Setting up Info Panel...");

        if (tab != null) {
            if (tab.equals("c")) {
                logger.info("[Info Panel]: Creating DetailedContact...");
                detailedInfo = new DetailedContact(protagonist);
            } else if (tab.equals("m")) {
                logger.info("[Info Panel]: Creating DetailedModule...");
                detailedInfo = new DetailedModule(protagonist);
            } else {
                logger.info("[Info Panel]: Creating DetailedSkill...");
                detailedInfo = new DetailedSkill(protagonist);
            }
        }

        if (protagonist != null) {
            Image image = new Image(protagonist.getProfilePicture().profilePicturePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            profilePicture.getChildren().set(0, imageView);
            name.setText(protagonist.getName().fullName);
            StringBuilder sb = new StringBuilder();
            sb.append("Year ");
            sb.append(protagonist.getYear());
            sb.append(", ");
            sb.append(protagonist.getCourse().toString());
            identity.setText(sb.toString());
            detailedInfoPlaceholder.getChildren().add(detailedInfo.getRoot());
        } else {
            logger.info("[Info Panel]: Protagonist not found. Showing default placeholders");
        }
    }

    /**
     * Set UiEventListener for InfoTab.
     * @param listener
     */
    public void setClickListener(MainWindow.ClickListener listener) {
        this.clickListener = listener;
        detailedInfo.setListener(clickListener);
    }

}
