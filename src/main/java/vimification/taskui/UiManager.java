package vimification.taskui;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vimification.Gui;
import vimification.commons.core.LogsCenter;
import vimification.commons.util.StringUtil;
import vimification.logic.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/address_book_32.png";

    private Logic logic;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        this.logic = logic;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");

        // Set the application icon.
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            MainScreen mainScreen = new MainScreen(logic);
            Scene scene = new Scene(mainScreen.getRoot());
            scene.getRoot().requestFocus();
            primaryStage.setTitle("Vimification");
            primaryStage.setScene(scene); // Setting the stage to show our screen
            primaryStage.show();

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
        }
    }

    private Image getImage(String imagePath) {
        return new Image(Gui.class.getResourceAsStream(imagePath));
    }

}
