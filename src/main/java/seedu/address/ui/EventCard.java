package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.event.Note;

/**
 * Informs the user on the number of undone tasks
 */
public class EventCard extends UiPart<Region> {
    private static final String FXML = "EventListCard.fxml";
    public final Event event;
    //private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private HBox studentProfiles;
    @FXML
    private VBox noteBox;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox details;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label attachments;
    @FXML
    private Label attendance;
    @FXML
    private Label progressBarCount;
    @FXML
    private ImageView attachmentLogo;
    @FXML
    private ImageView noteLogo;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane tags;
    @FXML
    private ProgressBar progressBar;

    /**
     * Adds an event with a unique index
     * @param event
     * @param displayedIndex
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;

        id.setText(displayedIndex + ". ");
        name.setText(event.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        date.setText(event.getDate().format(formatter));
        GuiSettings guiSettings = new GuiSettings();
        int size = guiSettings.getEventIconSize();

        //Set attachment icon
        if (event.countAttachments() > 0) {
            setImageIcon(attachmentLogo, guiSettings.getAttachmentIcon(), size);
        } else {
            setImageIcon(attachmentLogo, guiSettings.getNoAttachmentIcon(), size);
        }

        List<String> noteStrs;
        if (event.countNotes() > 0) {
            setImageIcon(noteLogo, guiSettings.getNoteIcon(), size);
            noteStrs = event.getNotes().stream().map(Note::toString).collect(Collectors.toList());
        } else {
            setImageIcon(noteLogo, guiSettings.getNoNoteIcon(), size);
            noteStrs = new ArrayList<>();
            noteStrs.add(new Note().toString());
        }
        for (String text : noteStrs) {
            Label label = new Label(text);
            noteBox.getChildren().add(label);
        }
        scrollPane.setContent(noteBox);
        borderPane.setCenter(scrollPane);
        borderPane.setTop(noteLogo);
        noteLogo.setOnMouseClicked(mouseEvent -> {
            scrollPane.setVisible(!scrollPane.isVisible());
        });

        //set list of student profiles at top right
        for (int i = 0; i < event.countStudents(); i++) {
            //Ensures only 5 student profile icons are displayed
            if (i == 4) {
                break;
            }
            String studentProfile = event.getStudentProfiles().get(i);
            ImageView newImageView = setImageIcon(studentProfile, size);
            studentProfiles.getChildren().addAll(newImageView);
        }

        //Ensures plus icon is rendered if more than 5 students are present
        if (event.countStudents() > 5) {
            ImageView newImageView = setImageIcon(guiSettings.getMorePhoto(), size);
            studentProfiles.getChildren().addAll(newImageView);
        }

        //Update progress bar with a maximum of 20 students
        if (event.countStudents() > 0) {
            progressBar.setProgress((double) event.countStudents() / 20);
        }

        //bind a click to open the attachment (only works for single attachment for now
        //Only prints error message for now
        if (event.getAttachments().size() > 0 && event.getAttachments().get(0).exists()) {
            cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, click -> {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(event.getAttachments().get(0));
                } catch (IOException e) {
                    System.out.println("file processing error!");
                }
                click.consume();
            });
        }

        if (event.countNotes() > 0) {
            cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, click -> {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(event.getAttachments().get(0));
                } catch (IOException e) {
                    System.out.println("file processing error!");
                }
                click.consume();
            });
        }
    }

    private void setImageIcon(ImageView imageView, String filePath, int size) {
        Image newImage = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(filePath)));
        imageView.setImage(newImage);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

    private ImageView setImageIcon(String filePath, int size) {
        ImageView imageView = new ImageView();
        Image newImage = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(filePath)));
        imageView.setImage(newImage);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        return imageView;
    }

    //Add more comparison in equals
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
