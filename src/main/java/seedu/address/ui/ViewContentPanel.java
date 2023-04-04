package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.Set;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.documents.Documents;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipApplicationAttribute;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;

/**
 * Panel containing the detailed content of selected internship application.
 */
public class ViewContentPanel extends UiPart<Region> {

    private static final String FXML = "ViewContentPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewContentPanel.class);

    @FXML
    private VBox container;
    @FXML
    private Pane contentContainer;
    @FXML
    private VBox headingContainer;

    @FXML
    private AnchorPane emptyAnchorPane;
    @FXML
    private Label emptyLabel;

    @FXML
    private Text title;
    @FXML
    private TextFlow titleTextFlow;
    @FXML
    private Text description;
    @FXML
    private Label sideLabel;
    @FXML
    private ListView<Region> pane;

    /**
     * Creates a {@code ViewContentPanel}.
     */
    public ViewContentPanel() {
        super(FXML);
        emptyLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(emptyLabel, 0.0);
        AnchorPane.setRightAnchor(emptyLabel, 0.0);
        AnchorPane.setTopAnchor(emptyLabel, 0.0);
        AnchorPane.setBottomAnchor(emptyLabel, 0.0);
        emptyLabel.setAlignment(Pos.CENTER);
    }

    /**
     * Getter for the underlying container.
     */
    public VBox getContainer() {
        return container;
    }

    private void addLabel(String labelString, InternshipApplicationAttribute attribute) {
        if (attribute != null && attribute.toString() != null && !attribute.toString().isBlank()) {
            Label label = new Label(labelString + ": " + attribute.toString());
            label.getStyleClass().add("cell_small_label");
            label.setWrapText(true);
            label.maxWidthProperty().bind(pane.widthProperty());
            pane.getItems().add(label);
        }
    }

    private void addFlowPane(String labelString, Set<? extends InternshipApplicationAttribute> attributesSet) {
        if (attributesSet.size() > 0) {
            VBox flowPaneContainer = new VBox();
            flowPaneContainer.setSpacing(3);
            Label label = new Label(labelString + ":");
            label.getStyleClass().add("cell_small_label");
            VBox vbox = new VBox(2);
            vbox.getStyleClass().add("multiple-items");
            vbox.getChildren().clear();
            attributesSet.stream()
                    .sorted(Comparator.comparing(InternshipApplicationAttribute::toString))
                    .forEach(review -> vbox.getChildren().add(new Label(review.toString())));
            flowPaneContainer.getChildren().addAll(label, vbox);
            pane.getItems().add(flowPaneContainer);
        }
    }

    private void displayEmptyPanel() {
        emptyAnchorPane.setVisible(true);
        emptyAnchorPane.setManaged(true);
        contentContainer.setVisible(false);
        contentContainer.setManaged(false);
    }

    private void showActualPanel() {
        emptyAnchorPane.setVisible(false);
        emptyAnchorPane.setManaged(false);
        contentContainer.setVisible(true);
        contentContainer.setManaged(true);
    }

    private void showCompulsoryAttributes(InternshipApplication internshipApplication) {
        title.setText(internshipApplication.getCompanyName().fullName);
        description.setText(internshipApplication.getJobTitle().fullName);
        if (!internshipApplication.isArchived()) {
            sideLabel.setText(internshipApplication.getStatus().label);
            sideLabel.setStyle("-fx-background-color: " + internshipApplication.getStatus().labelColour);
        } else {
            sideLabel.setText("Archived");
            sideLabel.setStyle("-fx-background-color: #808080");
        }
    }

    private void showOptionalAttributes(InternshipApplication internshipApplication) {
        pane.getItems().clear();
        if (internshipApplication.getContact() != null) {
            addLabel("Company Phone", internshipApplication.getContact().getPhone());
            addLabel("Company Email", internshipApplication.getContact().getEmail());
        }
        if (internshipApplication.getDocuments() != null) {
            addDocuments(internshipApplication.getDocuments());
        }
        addLabel("Interview Date", internshipApplication.getInterviewDate());
        addLabel("Location", internshipApplication.getLocation());
        addLabel("Salary", internshipApplication.getSalary());
        addLabel("Rating", internshipApplication.getRating());
        addFlowPane("Reviews", internshipApplication.getReviews());
        addFlowPane("Programming Languages", internshipApplication.getProgrammingLanguages());
        addFlowPane("Qualifications", internshipApplication.getQualifications());
        addFlowPane("Notes", internshipApplication.getNotes());
        addFlowPane("Reflections", internshipApplication.getReflections());
    }

    private void addDocuments(Documents documents) {
        FlowPane resumePane = new FlowPane();
        resumePane.getStyleClass().add("link-items");
        Label resumeLabel = new Label("Resume Link: ");
        resumeLabel.getStyleClass().add("cell_small_label");
        Hyperlink resumeHyperlink = new Hyperlink(documents.getResumeLink().value);
        setHyperlink(resumePane, resumeLabel, resumeHyperlink);

        FlowPane coverLetterPane = new FlowPane();
        coverLetterPane.getStyleClass().add("link-items");
        Label coverLetterLabel = new Label("Cover Letter Link: ");
        coverLetterLabel.getStyleClass().add("cell_small_label");
        Hyperlink coverLetterLink = new Hyperlink(documents.getCoverLetterLink().value);
        setHyperlink(coverLetterPane, coverLetterLabel, coverLetterLink);

        pane.getItems().addAll(resumePane, coverLetterPane);
    }

    private void setHyperlink(FlowPane flowPane, Label label, Hyperlink hyperlink) {
        hyperlink.getStyleClass().add("link-item");
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    Desktop.getDesktop().browse(new URI(hyperlink.getText()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        flowPane.getChildren().addAll(label, hyperlink);
    }

    private void addInternshipTodoDetails(InternshipTodo internshipTodo) {
        Label deadline = new Label("Deadline: " + internshipTodo.getDeadline());
        deadline.getStyleClass().add("cell_small_label");
        deadline.maxWidthProperty().bind(pane.widthProperty());

        Label createdOn = new Label("Created on: " + internshipTodo.getDateString());
        createdOn.getStyleClass().add("cell_small_label");
        createdOn.maxWidthProperty().bind(pane.widthProperty());

        if (internshipTodo.getNote() != null) {
            Label note = new Label("Note: " + internshipTodo.getNote());
            note.getStyleClass().add("cell_small_label");
            note.maxWidthProperty().bind(pane.widthProperty());
            pane.getItems().addAll(deadline, note, createdOn);
        } else {
            pane.getItems().addAll(deadline, createdOn);
        }
    }

    /**
     * Clears the {@code ViewContentPanel}.
     */
    public void clearPanel() {
        displayEmptyPanel();
    }

    public void setInternshipApplication(InternshipApplication internshipApplication) {
        if (internshipApplication != null) {
            showActualPanel();
            showCompulsoryAttributes(internshipApplication);
            showOptionalAttributes(internshipApplication);
        } else {
            displayEmptyPanel();
        }
    }

    /**
     * Sets and displays the particulars of the selected {@code InternshipTodo} in the {@code ViewContentPanel}.
     */
    public void setInternshipTodo(InternshipTodo internshipTodo) {
        if (internshipTodo != null) {
            showActualPanel();
            title.setText(internshipTodo.getInternshipTitle().fullName);
            description.setText(internshipTodo.getJobTitle().fullName);
            sideLabel.setText(internshipTodo.getType().name());
            sideLabel.setStyle("-fx-background-color: #53A3D8");
            pane.getItems().clear();
            addInternshipTodoDetails(internshipTodo);
        } else {
            displayEmptyPanel();
        }
    }

    /**
     * Sets and displays the particulars of the selected {@code Note} in the {@code ViewContentPanel}.
     */
    public void setNote(Note note) {
        if (note != null) {
            showActualPanel();
            title.setText(note.getNote().content);
            description.setText(note.getDateString());
            sideLabel.setText(note.getType().name());
            sideLabel.setStyle("-fx-background-color: #588157");
            pane.getItems().clear();
        } else {
            displayEmptyPanel();
        }
    }
}
