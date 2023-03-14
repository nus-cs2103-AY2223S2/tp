package vimification;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vimification.taskui.MainWindow;

public class Gui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create control
        MainWindow mainWindow = new MainWindow();
        mainWindow.setFocusTraversable(true); // Important

        // Set window
        // configureStage(primaryStage);
        Scene scene = new Scene(mainWindow);
        scene.getRoot().requestFocus();
        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.

    }

}
