package arb.ui.tag;

import java.util.logging.Logger;

import arb.commons.core.LogsCenter;
import arb.model.tag.TagMapping;
import arb.ui.UiPart;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of tags.
 */
public class TagMappingListPanel extends UiPart<Region> {
    private static final String FXML = "tag/TagMappingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagMappingListPanel.class);

    @FXML
    private ListView<TagMapping> tagMappingListView;

    /**
     * Creates a {@code TagMappingListPanel} with the given {@code ObservableList}.
     */
    public TagMappingListPanel(ObservableList<TagMapping> tagMappingList) {
        super(FXML);
        tagMappingListView.setItems(tagMappingList);
        tagMappingListView.setCellFactory(listView -> new TagMappingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TagMapping}
     * using a {@code TagMappingCard}.
     */
    class TagMappingListViewCell extends ListCell<TagMapping> {
        @Override
        protected void updateItem(TagMapping tagMapping, boolean empty) {
            super.updateItem(tagMapping, empty);

            if (empty || tagMapping == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagMappingCard(tagMapping, getIndex() + 1).getRoot());
            }
        }
    }

}
