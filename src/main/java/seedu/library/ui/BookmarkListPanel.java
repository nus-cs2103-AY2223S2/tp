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
    private boolean isStartEmpty;


    /**
     * Creates a {@code BookmarkListPanel} with the given {@code ObservableList}.
     */
    public BookmarkListPanel(ObservableList<Bookmark> bookmarkList) {
        super(FXML);
        bookmarks = bookmarkList;
        bookmarkListView.setItems(bookmarkList);
        bookmarkListView.setCellFactory(listView -> new BookmarkListViewCell());
        flag = false;
        isStartEmpty = false;
        bookmarkListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bookmark>() {
            @Override
            public void changed(ObservableValue<? extends Bookmark> observable, Bookmark oldValue, Bookmark newValue) {
                selectedItem = bookmarkListView.getSelectionModel().getSelectedItem();
                flag = true;
            }
        });


    }

    /**
     * Method to get the Bookmark currently selected in bookmarklistpanel
     */
    public Bookmark getSelectedItem() {
        return selectedItem;
    }

    /**
     * Unselects the item in bookmarklistpanel
     */
    public void unSelect() {
        bookmarkListView.getSelectionModel().clearSelection();
    }

    /**
     * selects the item in bookmarklistpanel
     */
    public void select(int index) {
        bookmarkListView.getSelectionModel().select(index);
    }

    /**
     * Return the flag which indicates if item selected has changed
     */
    public boolean isChangedSelect() {
        return flag;
    }

    /**
     * Method to get the first bookmark in bookmarklistpanel
     */
    public Bookmark getFirstItem() {
        try {
            bookmarkListView.getSelectionModel().select(0);
            flag = true;
            return bookmarks.get(0);
        } catch (IndexOutOfBoundsException e) {
            // just a default bookmark change
            isStartEmpty = true;
            String[] sampleProgress = {"1", "32", "56"};
            return new Bookmark(new Title("Attack on Titans"), new Progress(sampleProgress), new Genre("Fantasy"),
                    new Author("Hajime Isayama"), new Rating("5"), new Url(""),
                    SampleDataUtil.getTagSet("friends"));

        }

    }
    /**
     * Return the flag which indicates if starting bookmarklistpanel is empty
     */
    public boolean isStartEmpty() {
        return isStartEmpty;
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
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

}
