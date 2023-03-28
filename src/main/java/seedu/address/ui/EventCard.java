package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;

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
    private HBox details;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label notes;
    @FXML
    private Label attachments;
    @FXML
    private Label progressBar;
    @FXML
    private Label attendance;
    @FXML
    private ImageView attachmentLogo;
    @FXML
    private FlowPane tags;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date.setText(event.getDate().format(formatter));
        //notes.setText("" + event.countNotes());

        GuiSettings guiSettings = new GuiSettings();
        int size = guiSettings.getEventIconSize();

        //Set attachment icon
        if (event.getAttachments().size() > 0) {
            Image attachmentIcon = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(guiSettings.getAttachmentIcon())));
            attachmentLogo.setImage(attachmentIcon);
            attachmentLogo.setFitWidth(size);
            attachmentLogo.setFitHeight(size);
        } else {
            Image attachmentIcon = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(guiSettings.getNoAttachmentIcon())));
            attachmentLogo.setImage(attachmentIcon);
            attachmentLogo.setFitWidth(size);
            attachmentLogo.setFitHeight(size);
        }

        //set list of student profiles at top right
        for (String studentProfile: event.getStudentProfiles()) {
            ImageView profile = new ImageView();
            Image newImage = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(studentProfile)));
            profile.setImage(newImage);
            profile.setFitWidth(size);
            profile.setFitHeight(size);
            studentProfiles.getChildren().addAll(profile);
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
