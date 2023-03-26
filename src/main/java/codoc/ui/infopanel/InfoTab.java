package codoc.ui.infopanel;

import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.Person;
import codoc.ui.MainWindow;
import codoc.ui.UiEventListener;
import codoc.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Panel containing detailed information about a person.
 */
public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoTab.class);
    private UiEventListener<?> clickListener;

    private DetailedInfo detailedInfo;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label name;

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
        detailedInfo.setListener((MainWindow.ClickListener) clickListener);

        if (protagonist != null) {
            String profilePicturePath = protagonist.getProfilePicture().profilePicturePath;
            Image image = new Image("file:" + profilePicturePath);
            profilePicture.setImage(image);
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
    public void setClickListener(UiEventListener<?> listener) {
        this.clickListener = listener;
    }

    @FXML
    private void viewContactTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view c");
    }

    @FXML
    private void viewModulesTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view m");
    }

    @FXML
    private void viewSkillsTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view s");
    }



}
