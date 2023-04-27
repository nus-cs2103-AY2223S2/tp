package vimification.ui;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vimification.MainApp;
import vimification.common.core.LogsCenter;
import vimification.common.util.StringUtil;
import vimification.internal.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";

    private static final Logger LOGGER = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/vimification.png";

    private Logic logic;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        this.logic = logic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting UI...");
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            primaryStage.setTitle("Vimification");
            primaryStage.setHeight(500);
            primaryStage.setWidth(1000);
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(1000);

            MainScreen mainScreen = new MainScreen(logic);
            logic.setMainScreen(mainScreen);
            mainScreen.getRoot().prefWidthProperty().bind(primaryStage.widthProperty());
            mainScreen.getRoot().prefHeightProperty().bind(primaryStage.heightProperty());
            Scene scene = new Scene(mainScreen.getRoot());
            scene.getRoot().requestFocus();
            primaryStage.setScene(scene); // Setting the stage to show our screen
            primaryStage.show();
        } catch (Throwable ex) {
            LOGGER.severe(StringUtil.getDetails(ex));
        }
    }

    /**
     * Returns an image from the given image path.
     */
    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

}
