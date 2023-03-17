package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.address.model.video.Video;

/**
 * Panel containing the list of modules.
 */
public class VideoListPanel extends UiPart<Region> {
    private static final String FXML = "VideoListPanel.fxml";

    @FXML
    private ListView<Video> videoListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public VideoListPanel(ObservableList<Video> observableList) {
        super(FXML);
        videoListView.setItems(observableList);
        videoListView.setCellFactory(listView -> new VideoListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class VideoListViewCell extends ListCell<Video> {
        @Override
        protected void updateItem(Video video, boolean empty) {
            super.updateItem(video, empty);

            if (empty || video == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VideoCard(video, getIndex() + 1).getRoot());
            }
        }
    }

}
