package arb.ui.tag;

import java.util.logging.Logger;

import arb.commons.core.LogsCenter;
import arb.model.tag.Tag;
import arb.ui.UiPart;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of tags.
 */
public class TagListPanel extends UiPart<Region> {
    private static final String FXML = "tag/TagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    @FXML
    private ListView<Tag> tagListView;

    /**
     * Creates a {@code TagListPanel} with the given {@code ObservableList}.
     */
    public TagListPanel(ObservableList<Tag> tagList) {
        super(FXML);
        tagListView.setItems(tagList);
        tagListView.setCellFactory(listView -> new TagListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tag} using a {@code TagCard}.
     */
    class TagListViewCell extends ListCell<Tag> {
        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagCard(tag, getIndex() + 1).getRoot());
            }
        }
    }

}
