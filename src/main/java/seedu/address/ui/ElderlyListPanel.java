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
import seedu.address.model.person.Elderly;

/**
 * Panel containing the list of elderly.
 */
public class ElderlyListPanel extends UiPart<Region> {
    private static final String FXML = "ElderlyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ElderlyListPanel.class);

    @FXML
    private ListView<Elderly> elderlyListView;

    @FXML
    private VBox elderlyListBox;

    private final VBox emptyBox = new VBox();

    /**
     * Creates a {@code ElderlyListPanel} with the given {@code ObservableList}.
     *
     * @param elderlyList Elderly list to be displayed.
     */
    public ElderlyListPanel(ObservableList<Elderly> elderlyList) {
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
                "-fx-text-fill: whitesmoke; -fx-font-size: 23; -fx-label-padding: 10"
        );

        if (elderlyList.size() == 0) {
            elderlyListView.setMaxHeight(0);
            elderlyListBox.getChildren().add(emptyBox);
        } else {
            elderlyListBox.getChildren().remove(emptyBox);
            elderlyListView.setMaxHeight(Region.USE_COMPUTED_SIZE);
        }

        elderlyListView.setItems(elderlyList);
        elderlyListView.setCellFactory(listView -> new ElderlyListPanel.ElderlyListViewCell());
        elderlyList.addListener((ListChangeListener<? super Elderly>) c -> {
            if (c.getList().size() == 0) {
                elderlyListView.setMaxHeight(0);
                elderlyListBox.getChildren().add(emptyBox);
            } else {
                elderlyListBox.getChildren().remove(emptyBox);
                elderlyListView.setMaxHeight(Region.USE_COMPUTED_SIZE);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Elderly} using a {@code ElderlyCard}.
     */
    static class ElderlyListViewCell extends ListCell<Elderly> {
        @Override
        protected void updateItem(Elderly elderly, boolean empty) {
            super.updateItem(elderly, empty);

            if (empty || elderly == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ElderlyCard(elderly, getIndex() + 1).getRoot());
            }
        }
    }

}
