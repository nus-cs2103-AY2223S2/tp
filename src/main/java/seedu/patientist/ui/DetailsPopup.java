package seedu.patientist.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

public class DetailsPopup {
    public DetailsPopup(PersonListPanel personListPanel) {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Details");

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 500);

        root.getChildren().add(personListPanel.getRoot());
        detailsStage.setScene(scene);
        detailsStage.show();
    }
}
