package seedu.address.ui;

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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.event.Note;
import seedu.address.model.person.Person;

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
    private VBox studentBox;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox details;
    @FXML
    private HBox noteDetails;
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
    private Text noteText;
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
    @FXML
    private Line line;

    /**
     * Adds an event with a unique index
     * @param event
     * @param displayedIndex
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        noteDetails.setVisible(false);
        line.setVisible(false);
        noteText.setVisible(false);
        noteDetails.managedProperty().bind(noteDetails.visibleProperty());
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
        for (int i = 1; i <= noteStrs.size(); i++) {
            String text = noteStrs.get(i - 1);
            String stripContent = text;
            if (text.split("\n").length > 1) {
                stripContent = text.split("\n")[1];
            }
            Label label = new Label(i + ". " + stripContent);
            noteBox.getChildren().add(label);
        }

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
        if (event.countStudents() > 4) {
            ImageView newImageView = setImageIcon(guiSettings.getMorePhoto(), size);
            studentProfiles.getChildren().addAll(newImageView);
        }

        //Update progress bar with a maximum of 20 students
        if (event.countStudents() > 0) {
            progressBar.setProgress((double) event.countStudents() / 20);
        }

        displayStudentNames();
        handleNoteClick();

    }

    private void displayStudentNames() {
        if (event.countStudents() > 0) {
            for (int i = 1; i <= event.countStudents(); i++) {
                Person student = event.getStudents().get(i - 1);
                Label label = new Label(i + "." + student.getName() + ". Overall performance: "
                        + student.getPerformance() + "/100");
                label.setWrapText(true);
                studentBox.getChildren().add(label);
            }
        }
    }

    private void handleNoteClick() {
        if (event.countNotes() > 0) {
            cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, click -> {
                noteText.setVisible(!noteDetails.isVisible());
                line.setVisible(!noteDetails.isVisible());
                noteDetails.setVisible(!noteDetails.isVisible());
                noteDetails.managedProperty().bind(noteDetails.visibleProperty());
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
