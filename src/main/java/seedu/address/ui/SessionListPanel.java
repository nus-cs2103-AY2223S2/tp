package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.session.Session;

/**
 * Panel containing the list of persons.
 */
public class SessionListPanel extends UiPart<Region> {
    private static final String FXML = "SessionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @FXML
    private ListView<Session> sessionListView;
    @FXML
    private ScrollPane sessionDetailDisplay;
    @FXML
    private VBox sessionDetailContainer;

    @FXML
    private Label selectedSession;
    @FXML
    private Label selectedName;
    @FXML
    private Label selectedDate;
    @FXML
    private Label selectedLocation;

    @FXML
    private Label selectedStudents;

    @FXML
    private PieChart attendanceChart;

    @FXML
    private Label nameField;
    @FXML
    private Label dateField;
    @FXML
    private Label locationField;
    @FXML
    private Label studentsField;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public SessionListPanel(ObservableList<Session> sessionList, Logic logic) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell());

        setClickEventListener();
        setUpdateEventListener(logic);
    }


    /**
     * Set up event listener to listen to updates within the ObservableList
     */
    private void setUpdateEventListener(Logic logic) {
        logic.getFilteredSessionList().addListener(new ListChangeListener<Session>() {
            @Override
            public void onChanged(Change<? extends Session> c) {
                Session selectedSession = sessionListView.getSelectionModel().getSelectedItem();
                updateDisplay(selectedSession);
            }
        });
    }

    /**
     * Set up event listener to listen to click events within sessionListView
     */
    private void setClickEventListener() {
        sessionListView.setOnMouseClicked(event -> {
            Session selectedSession = sessionListView.getSelectionModel().getSelectedItem();
            if (selectedSession != null) {
                updateDisplay(selectedSession);
            }
        });
    }


    /**
     * Update the display inside {@code sessionDetailDisplay} in response to changes inside the ObservableList or click
     * @param selectedSession the session to be displayed
     */
    public void updateDisplay(Session selectedSession) {
        Label[] fields = new Label[]{dateField, locationField, studentsField};
        Label[] details = new Label[]{selectedName, selectedDate, selectedLocation, selectedStudents};
        setupStyle();
        setSessionFieldDisplay(fields);
        updateDisplayedSessionDetail(selectedSession, details);
    }

    /**
     * Set up the display of relevant Session fields
     * @param fields fields to be displayed
     */
    private void setSessionFieldDisplay(Label[] fields) {
        locationField.setText("Location");
        dateField.setText("Date");
        studentsField.setText("Students");
        for (Label field: fields) {
            field.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(4),
                    new Insets(-3, -10, -3, -10))));
            VBox parent = (VBox) field.getParent();
            parent.setMargin(field, new Insets(0, 0, 10, 0));
        }
    }

    /**
     * Update the detail information to be displayed inside {@code sessionDetailDisplay}
     * @param selectedSession the Session whose information is to be displayed
     * @param details the Labels to hold the information of the selected Session
     */
    private void updateDisplayedSessionDetail(Session selectedSession, Label[] details) {
        selectedName.setText(selectedSession.getName());
        selectedName.setPadding(new Insets(0, -10, 0, -10));
        selectedDate.setText(selectedSession.getStartDateTime());
        selectedLocation.setText(selectedSession.getLocation());

        attendanceChart.setData(SessionPieChart.generateAttendancePieChart(selectedSession.getAttendanceMap()));
        attendanceChart.setLabelsVisible(false);
        try {
            for (Label detail : details) {
                detail.setWrapText(true);
                detail.setMinWidth(0);
            }
        } catch (NullPointerException e) {
            logger.info(selectedSession.getName() + " session is empty!");
        }
    }

    /**
     * Set up the styling and spacing of {@code sessionDetailDisplay} region
     */
    private void setupStyle() {
        sessionDetailContainer.setSpacing(10);
        sessionDetailContainer.setPadding(new Insets(10, 0, 0, 20));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class SessionListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SessionCard(session, getIndex() + 1).getRoot());
            }
        }
    }

}

