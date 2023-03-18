package seedu.patientist.ui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.patientist.model.person.Person;

/**
 * The UI component that is responsible for a pop-up to show details of Person.
 */
public class DetailsPopup {

    /**
     * Creates a {@code DetailsPopup} with the given {@code personListPanel}.
     */
    public DetailsPopup(ObservableList<Person> personListToView) {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Details");

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 500);

        root.getChildren().add(new PersonListPanel(personListToView).getRoot());
        detailsStage.setScene(scene);
        detailsStage.show();
    }
}
