package seedu.library.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.library.commons.core.LogsCenter;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Rating;
import seedu.library.model.bookmark.Title;
import seedu.library.model.bookmark.Url;
import seedu.library.model.util.SampleDataUtil;



/**
 * Panel containing the list of bookmarks.
 */
public class BookmarkListPanel extends UiPart<Region> {
    private static final String FXML = "BookmarkListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BookmarkListPanel.class);

    @FXML
    private ListView<Bookmark> bookmarkListView;
    private Bookmark selectedItem;

    private ObservableList<Bookmark> bookmarks;
    private boolean flag;

    /**
     * Creates a {@code BookmarkListPanel} with the given {@code ObservableList}.
     */
    public BookmarkListPanel(ObservableList<Bookmark> bookmarkList) {
        super(FXML);
        bookmarks = bookmarkList;
        bookmarkListView.setItems(bookmarkList);
        bookmarkListView.setCellFactory(listView -> new BookmarkListViewCell());
        flag = false;

        bookmarkListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bookmark>() {
            @Override
            public void changed(ObservableValue<? extends Bookmark> observable, Bookmark oldValue, Bookmark newValue) {
                selectedItem = bookmarkListView.getSelectionModel().getSelectedItem();
                flag = true;
            }
        });


    }
    public Bookmark getSelectedItem() {
        return selectedItem;
    }
    public void unSelect() {
        bookmarkListView.getSelectionModel().clearSelection();
    }
    public boolean isChangedSelect() {
        return flag;
    }
    public Bookmark getFirstItem() {
        try {
            Bookmark item = bookmarks.get(0);
            return item;
        } catch (IndexOutOfBoundsException e) {
            // just a default bookmark change
            String[] sampleProgress = {"1", "32", "56"};
            return new Bookmark(new Title("Attack on Titans"), new Progress(sampleProgress), new Genre("Fantasy"),
                    new Author("Hajime Isayama"), new Rating("5"), new Url(""),
                    SampleDataUtil.getTagSet("friends"));

        }

    }




    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Bookmark} using a {@code BookmarkCard}.
     */
    class BookmarkListViewCell extends ListCell<Bookmark> {
        @Override
        protected void updateItem(Bookmark bookmark, boolean empty) {
            super.updateItem(bookmark, empty);
            try {
                if (empty || bookmark == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new BookmarkCard(bookmark, getIndex() + 1).getRoot());
                }
            }
            catch (IOException e) {
                System.out.println("IO exception");
            }
        }
    }

}
