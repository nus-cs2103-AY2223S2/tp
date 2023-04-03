package seedu.library.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.library.model.bookmark.Bookmark;


/**
 * UI component that is responsible for displaying the details of a single bookmark.
 */
public class ZoomView extends UiPart<Region> {
    private static final String FXML = "ZoomView.fxml";
    private Bookmark bookmark;

    @FXML
    private Label viewTitle;
    @FXML
    private Label authorView;
    @FXML
    private Label genreView;

    @FXML
    private FlowPane tagsView;
    @FXML
    private Label progressView;
    @FXML
    private Label zoomTag;
    @FXML
    private ImageView avatar;
    @FXML
    private Hyperlink urlLink;
    @FXML
    private Label urlView;
    @FXML
    private ImageView ratingStar;
    @FXML
    private Label labelHeader;


    /**
     * Constructs a ZoomView that is empty
     *
     */
    public ZoomView() {
        super(FXML);
        InputStream image = this.getClass().getResourceAsStream("/images/default-avatar.png");
        avatar.setImage(new Image(image));
        hideFields();

    }

    /**
     * Constructs a ZoomView that displays the details of the provided bookmark.
     *
     * @param bookmark a single Bookmark object
     */
    public ZoomView(Bookmark bookmark) {
        super(FXML);
        this.bookmark = bookmark;
        viewTitle.setText("Title: " + bookmark.getTitle().value);
        String authorString = (bookmark.getAuthor() == null) ? "-" : bookmark.getAuthor().value;
        authorView.setText("Author: " + authorString);
        genreView.setText("Genre: " + bookmark.getGenre().value);
        String progressString = (bookmark.getProgress() == null) ? "-" : bookmark.getProgress().toString();
        progressView.setText("Progress: " + progressString);
        urlLink.setText(bookmark.getUrl().value);
        urlLink.setMaxWidth(450);
        bookmark.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tagsView.getChildren().add(new Label(tag.tagName)));
        InputStream image = this.getClass().getResourceAsStream("/images/default-avatar.png");
        avatar.setImage(new Image(image));
        urlLink.setOnAction(e -> {
            openLink(urlLink.getText());
        });
        rate(bookmark);


    }

    /**
     * Open url in default browser
     * @param url url to open
     */
    public void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            throw new AssertionError(ex);
        }
    }

    /**
     * Helps set rating image in bookmarkcard
     * @param bookmark book to get rating value from
     */
    public void rate(Bookmark bookmark) {
        InputStream rating0 = this.getClass().getResourceAsStream("/images/Rating0.png");
        InputStream rating1 = this.getClass().getResourceAsStream("/images/Rating1.png");
        InputStream rating2 = this.getClass().getResourceAsStream("/images/Rating2.png");
        InputStream rating3 = this.getClass().getResourceAsStream("/images/Rating3.png");
        InputStream rating4 = this.getClass().getResourceAsStream("/images/Rating4.png");
        InputStream rating5 = this.getClass().getResourceAsStream("/images/Rating5.png");

        if (bookmark.getRating() == null) {
            ratingStar.setImage(new Image(rating0));
            ratingStar.setVisible(true);
            return;
        }

        String rating = bookmark.getRating().toString();

        if (rating.equals("1")) {
            ratingStar.setImage(new Image(rating1));
            ratingStar.setVisible(true);
        } else if (rating.equals("2")) {
            ratingStar.setImage(new Image(rating2));
            ratingStar.setVisible(true);
        } else if (rating.equals("3")) {
            ratingStar.setImage(new Image(rating3));
            ratingStar.setVisible(true);
        } else if (rating.equals("4")) {
            ratingStar.setImage(new Image(rating4));
            ratingStar.setVisible(true);
        } else if (rating.equals("5")) {
            ratingStar.setImage(new Image(rating5));
            ratingStar.setVisible(true);
        } else {
            ratingStar.setVisible(false);
        }

    }


    /**
     * Method to hide the contents of ZoomView
     */
    public void hideFields() {
        viewTitle.setVisible(false);
        authorView.setVisible(false);
        genreView.setVisible(false);
        progressView.setVisible(false);
        tagsView.setVisible(false);
        zoomTag.setVisible(false);
        urlLink.setVisible(false);
        urlView.setVisible(false);
        ratingStar.setVisible(false);
        labelHeader.setVisible(false);

    }


}
