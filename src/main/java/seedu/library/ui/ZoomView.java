package seedu.library.ui;

import java.awt.Desktop;

import java.io.FileInputStream;
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




    /**
     * Constructs a ZoomView that displays the details of the provided bookmark.
     *
     * @param bookmark a single Bookmark object
     */
    public ZoomView(Bookmark bookmark)  {
        super(FXML);

        try {
            this.bookmark = bookmark;
            viewTitle.setText("Title: " + bookmark.getTitle().value);
            authorView.setText("Author: " + bookmark.getAuthor().value);
            genreView.setText("Genre: " + bookmark.getGenre().value);
            progressView.setText("Progress: " + bookmark.getProgress().toString());
            urlLink.setText(bookmark.getUrl().value);
            genreView.setText("Genre: " + bookmark.getGenre().value);
            progressView.setText("Progress: " + bookmark.getProgress().toString());
            bookmark.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tagsView.getChildren().add(new Label(tag.tagName)));
            InputStream image = new FileInputStream("src/main/resources/images/default-avatar.png");
            avatar.setImage(new Image(image));
            urlLink.setOnAction(e -> {
               openLink(urlLink.getText());
            });
        }
        catch (IOException  e) {
            System.out.println("error");
        }

    }

    void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        }
        catch (IOException | URISyntaxException ex) {
            System.out.println("error");
        }
    }
    /**
     * Constructs a ZoomView that is empty
     *
     */
    public ZoomView()  {
        super(FXML);
        try {
            InputStream image = new FileInputStream("src/main/resources/images/default-avatar.png");
            avatar.setImage(new Image(image));
            hideFields();
        }
        catch (IOException e) {

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

    }


}
