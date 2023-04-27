package seedu.address.ui;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Contains a panel that displays all the event cards
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<Tutorial> eventListViewTutorials;
    @FXML
    private ListView<Lab> eventListViewLabs;
    @FXML
    private ListView<Consultation> eventListViewConsultations;

    @FXML
    private ImageView tutorial;
    @FXML
    private ImageView lab;
    @FXML
    private ImageView consultation;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(List<ObservableList<? extends Event>> events) {
        super(FXML);

        eventListViewTutorials.setItems((ObservableList<Tutorial>) events.get(0));
        eventListViewTutorials.setCellFactory(listView -> new TutorialListViewCell());

        eventListViewLabs.setItems((ObservableList<Lab>) events.get(1));
        eventListViewLabs.setCellFactory(listView -> new LabListViewCell());

        eventListViewConsultations.setItems((ObservableList<Consultation>) events.get(2));
        eventListViewConsultations.setCellFactory(listView -> new ConsultationListViewCell());

        GuiSettings guiSettings = new GuiSettings();
        int size = guiSettings.getEventIconSize();

        //set tutorial icon
        Image tutorialIcon = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(guiSettings.getTutorialIcon())));
        tutorial.setImage(tutorialIcon);
        tutorial.setFitWidth(size);
        tutorial.setFitHeight(size);

        //set lab icon
        Image labIcon = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(guiSettings.getLabIcon())));
        lab.setImage(labIcon);
        lab.setFitWidth(size);
        lab.setFitHeight(size);

        //set consultation icon
        Image consultationIcon = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(guiSettings.getConsultationIcon())));
        consultation.setImage(consultationIcon);
        consultation.setFitWidth(size);
        consultation.setFitHeight(size);
    }

    /**
     * Displays the graphics of a {@code Consultation} using a {@code EventCard}.
     */
    class ConsultationListViewCell extends ListCell<Consultation> {
        @Override
        protected void updateItem(Consultation consultation, boolean empty) {
            super.updateItem(consultation, empty);

            if (empty || consultation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(consultation, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Displays the graphics of a {@code Tutorial} using a {@code EventCard}.
     */
    class TutorialListViewCell extends ListCell<Tutorial> {
        @Override
        protected void updateItem(Tutorial tutorial, boolean empty) {
            super.updateItem(tutorial, empty);

            if (empty || tutorial == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(tutorial, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Displays the graphics of a {@code Lab} using a {@code EventCard}.
     */
    class LabListViewCell extends ListCell<Lab> {
        @Override
        protected void updateItem(Lab lab, boolean empty) {
            super.updateItem(lab, empty);

            if (empty || lab == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(lab, getIndex() + 1).getRoot());
            }
        }
    }
}
