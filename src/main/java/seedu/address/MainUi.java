package seedu.address;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainUi extends Application {
    private static BorderStroke borderStroke =
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
    private static Border border = new Border(borderStroke);


    private static double MAIN_WINDOW_WIDTH = 400;
    private static double MAIN_WINDOW_HEIGHT = 400;

    @Override
    public void start(Stage stage) {
        // Create the main screen components
        Label mainLabel = new Label("Main Screen");

        // TODO : Refactor this into a custom component
        VBox leftComponent = new VBox(new Label("Task List Component"));

        // TODO : Refactor this into a fxml or make it more elegant
        leftComponent.prefHeightProperty().bind(stage.heightProperty().multiply(0.9));
        leftComponent.prefWidthProperty().bind(stage.widthProperty().multiply(0.3));
        leftComponent.setBorder(border);

        // TODO : Refactor this into a custom component
        VBox rightComponent = new VBox(new Label("Detailed Task Component"));

        // TODO : Refactor this into a fxml or make it more elegant
        rightComponent.prefHeightProperty().bind(stage.heightProperty().multiply(0.9));
        rightComponent.prefWidthProperty().bind(stage.widthProperty().multiply(0.7));
        rightComponent.setBorder(border);

        HBox mainComponents = new HBox(leftComponent, rightComponent);
        VBox mainScreen = new VBox(mainLabel, mainComponents);

        // Create the text box component
        TextField textBox = new TextField();
        VBox textBoxComponent = new VBox(textBox);

        // TODO : Refactor this into a fxml or make it more elegant
        textBoxComponent.prefHeightProperty().bind(stage.heightProperty().multiply(0.1));
        textBox.prefHeightProperty().bind(textBoxComponent.heightProperty().multiply(1));
        textBoxComponent.setVisible(false);


        // TODO : Refactor each event listener to obey SLAP
        // Set up the ":" key listener to show/hide the text box component
        mainScreen.setOnKeyPressed(event -> {
            if (event.getText().equals(":")) {
                textBoxComponent.setVisible(true);
                textBox.requestFocus();
            }
        });

        textBoxComponent.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                textBoxComponent.setVisible(false);
                mainScreen.requestFocus();
                System.out.println("You escaped");
            }
        });

        textBox.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                textBoxComponent.setVisible(false);
                mainScreen.requestFocus();
                String commandString = textBox.getText();
                System.out.println("Your command is " + commandString);

                // TODO : Create a Parser to parse the command and create a Driver to run it.
                if (commandString.equals(":wq!")) {
                    Platform.exit();
                }
            }
        });


        // Set up the layout
        VBox root = new VBox(mainScreen, textBoxComponent);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

        root.prefWidthProperty().bind(stage.widthProperty());
        root.prefHeightProperty().bind(stage.heightProperty());

        // Show the window
        stage.setScene(scene);
        stage.show();
        mainScreen.requestFocus();

    }

}
