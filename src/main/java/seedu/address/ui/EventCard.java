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
     * Adds an event with a unique index and renders the event card displays.
     *
     * @param event the type of event to handle which is either Tutorial or Lab or Consultation.
     * @param displayedIndex the displayed index of the event card.
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

        displayNotes(event, guiSettings.getNoteIcon(), guiSettings.getNoNoteIcon(), size);
        setTopRightProfiles(event, guiSettings.getMorePhoto(), size);
        handleProgressBar(event);
        displayStudentNames();
        handleNoteClick();
    }

    /**
     * Displays all the student that are present in the event by the student name and the student performance.
     * The index of the student in the event is also displayed, starting from 1.
     */
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

    /**
     * Allows the user to toggle the display of notes by double-clicking on the event card.
     */
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

    /**
     * Sets a specific imageview to an icon based on the filepath. The size is retrieved from the gui settings.
     *
     * @param imageView the imageview to set the image one.
     * @param filePath  the filepath to the image.
     * @param size      the TrAcker wide size for icons from the gui settings.
     */
    private void setImageIcon(ImageView imageView, String filePath, int size) {
        Image newImage = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(filePath)));
        imageView.setImage(newImage);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

    /**
     * Sets the image on a new imageview that has yet to be rendered.
     *
     * @param filePath  the filepath to the image.
     * @param size      the TrAcker wide size for icons from the gui settings.
     * @return the new ImageView.
     */
    private ImageView setImageIcon(String filePath, int size) {
        ImageView imageView = new ImageView();
        Image newImage = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(filePath)));
        imageView.setImage(newImage);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        return imageView;
    }

    /**
     * Sets the corner profile icons at the top right of the event card. If there are more
     * than 4 students, a more ellipses icon is added.
     *
     * @param event         the type of event to render the top right profile icons.
     * @param morePhoto     the filepath to the ellipses photo / more photo icon.
     * @param size          the TrAcker wide size for icons from the gui settings.
     */
    private void setTopRightProfiles(Event event, String morePhoto, int size) {
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
            ImageView newImageView = setImageIcon(morePhoto, size);
            studentProfiles.getChildren().addAll(newImageView);
        }
    }

    /**
     * Allows the progress bar to be updated based on the number of students are present.
     * The maximum number of students to fill up the progress bar is 20.
     *
     * @param event the type of event to set the progress bar.
     */
    private void handleProgressBar(Event event) {
        if (event.countStudents() > 0) {
            progressBar.setProgress((double) event.countStudents() / 20);
        }
    }

    /**
     * Renders the notes for event card in a list manner and hides it at first.
     *
     * @param event         the type of event to render the notes.
     * @param noteIcon      the filepath to the note icon.
     * @param size          the TrAcker wide size for icons from the gui settings.
     */
    private void displayNotes(Event event, String noteIcon, String noNoteIcon, int size) {
        List<String> noteStrs;
        if (event.countNotes() > 0) {
            setImageIcon(noteLogo, noteIcon, size);
            noteStrs = event.getNotes().stream().map(Note::toString).collect(Collectors.toList());
        } else {
            setImageIcon(noteLogo, noNoteIcon, size);
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
    }

    /**
     * Compares equality of event cards based on the id and the event.
     *
     * @param other the object to compare to.
     * @return      the boolean status of whether the current event card is equals to the object of comparison.
     */
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
