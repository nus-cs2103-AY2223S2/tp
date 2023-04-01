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
import seedu.address.model.pair.Pair;

/**
 * Panel containing the list of pairs.
 */
public class PairListPanel extends UiPart<Region> {
    private static final String FXML = "PairListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PairListPanel.class);

    @FXML
    private ListView<Pair> pairListView;

    @FXML
    private VBox pairListBox;

    private final VBox emptyBox = new VBox();

    /**
     * Creates a {@code PairListPanel} with the given {@code ObservableList}.
     *
     * @param pairList Pair list to be displayed.
     */
    public PairListPanel(ObservableList<Pair> pairList) {
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
        emptyBox.setMinHeight(290);

        label.setStyle(
                "-fx-text-fill: whitesmoke; -fx-font-size: 23; -fx-label-padding: 10"
        );

        if (pairList.size() == 0) {
            pairListView.setMaxHeight(0);
            pairListBox.getChildren().add(emptyBox);
        } else {
            pairListBox.getChildren().remove(emptyBox);
            pairListView.setMaxHeight(Region.USE_COMPUTED_SIZE);
        }

        pairListView.setItems(pairList);
        pairListView.setCellFactory(listView -> new PairListViewCell());
        pairList.addListener((ListChangeListener<? super Pair>) c -> {
            if (c.getList().size() == 0) {
                pairListView.setMaxHeight(0);
                pairListBox.getChildren().add(emptyBox);
            } else {
                pairListBox.getChildren().remove(emptyBox);
                pairListView.setMaxHeight(Region.USE_COMPUTED_SIZE);
            }
        });


    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pair} using a {@code PairCard}.
     */
    static class PairListViewCell extends ListCell<Pair> {
        @Override
        protected void updateItem(Pair pair, boolean empty) {
            super.updateItem(pair, empty);

            if (empty || pair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PairCard(pair, getIndex() + 1).getRoot());
            }
        }
    }

}
