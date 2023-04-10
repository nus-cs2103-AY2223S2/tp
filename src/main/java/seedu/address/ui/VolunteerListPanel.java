package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Volunteer;

/**
 * Panel containing the list of volunteers.
 */
public class VolunteerListPanel extends UiPart<Region> {
    private static final String FXML = "VolunteerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VolunteerListPanel.class);

    @FXML
    private ListView<Volunteer> volunteerListView;

    @FXML
    private VBox volunteerListBox;

    private final VBox emptyBox = new VBox();

    /**
     * Creates a {@code VolunteerListPanel} with the given {@code ObservableList}.
     *
     * @param volunteerList Volunteer list to be displayed.
     */
    public VolunteerListPanel(ObservableList<Volunteer> volunteerList) {
        super(FXML);

        ImageView imageView = new ImageView();
        Image image = new Image("/images/empty.png");
        imageView.setFitHeight(100);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);

        emptyBox.getChildren().add(imageView);
        Label label = new Label("There is nothing to see here.");
        emptyBox.getChildren().add(label);

        emptyBox.setAlignment(Pos.CENTER);
        emptyBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        emptyBox.setMinHeight(400);

        label.setStyle(
                "-fx-text-fill: whitesmoke; -fx-font-size: 23; -fx-label-padding: 10; "
                        + "-fx-font-family: Segoe UI Semibold"
        );

        if (volunteerList.size() == 0) {
            volunteerListView.setMaxHeight(0);
            volunteerListBox.getChildren().add(emptyBox);
        } else {
            volunteerListBox.getChildren().remove(emptyBox);
            volunteerListView.setMaxHeight(Region.USE_COMPUTED_SIZE);
        }

        volunteerListView.setItems(volunteerList);
        volunteerListView.setCellFactory(listView -> new VolunteerListPanel.VolunteerListViewCell());
        volunteerList.addListener((ListChangeListener<? super Volunteer>) c -> {
            if (c.getList().size() == 0) {
                volunteerListView.setMaxHeight(0);
                volunteerListBox.getChildren().add(emptyBox);
            } else {
                volunteerListBox.getChildren().remove(emptyBox);
                volunteerListView.setMaxHeight(Region.USE_COMPUTED_SIZE);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Volunteer} using a {@code VolunteerCard}.
     */
    static class VolunteerListViewCell extends ListCell<Volunteer> {
        @Override
        protected void updateItem(Volunteer volunteer, boolean empty) {
            super.updateItem(volunteer, empty);

            if (empty || volunteer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VolunteerCard(volunteer, getIndex() + 1).getRoot());
            }
        }
    }

}
