package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
    private Label selectedEarnings;

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
    @FXML
    private Label earningsField;

    //Statistics
    @FXML
    private Label todayField;
    @FXML
    private Label today;

    @FXML
    private Label thisWeekField;
    @FXML
    private Label thisWeek;

    @FXML
    private Label thisMonthField;
    @FXML
    private Label thisMonth;

    @FXML
    private Label lifetimeField;
    @FXML
    private Label lifetime;

    @FXML
    private VBox earningsChart;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public SessionListPanel(ObservableList<Session> sessionList, Logic logic) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell());

        //        if (sessionListView.getItems() != null) {
        //            updateDisplay(sessionListView.getItems().get(0));
        //        }
        getStatistics(sessionList);
        setClickEventListener();
        setUpdateEventListener(logic);

        sessionList.addListener((ListChangeListener<Session>) change -> {
            getStatistics(logic.getFilteredSessionList());
        });
    }

    private void getStatistics(ObservableList<Session> sessionList) {
        Label[] fields = new Label[]{todayField, thisWeekField, thisMonthField, lifetimeField};
        Label[] details = new Label[]{today, thisWeek, thisMonth, lifetime};

        setStatisticsFieldDisplay(fields);
        if (sessionList != null) {
            updateStatisticsDetail(sessionList, details);
        }

    }

    private void updateStatisticsDetail(ObservableList<Session> sessionList, Label[] details) {
        float todaysEarnings = getTodaysEarnings(sessionList);
        float weeklyEarnings = getWeeklyEarnings(sessionList);
        float monthlyEarnings = getMonthlyEarnings(sessionList);
        float lifetimeEarnings = getLifetimeEarnings(sessionList);

        today.setText("$" + String.format("%.2f", todaysEarnings));
        thisWeek.setText("$" + String.format("%.2f", weeklyEarnings));
        thisMonth.setText("$" + String.format("%.2f", monthlyEarnings));
        lifetime.setText("$" + String.format("%.2f", lifetimeEarnings));

        Node oldBarChart = earningsChart.lookup("EarningsBarChart");
        if (oldBarChart != null) {
            earningsChart.getChildren().remove(oldBarChart);
        } else {
            // Try using lookupAll() to search the entire subtree rooted at earningsChart
            Set<Node> nodes = earningsChart.lookupAll("EarningsBarChart");
            for (Node node : nodes) {
                earningsChart.getChildren().remove(node);
            }
        }

        EarningsBarChart barChart = new EarningsBarChart(sessionList);
        barChart.setStyle("-fx-text-inner-color: white;");
        earningsChart.getChildren().add(barChart);


        try {
            for (Label detail : details) {
                detail.setWrapText(true);
                detail.setMinWidth(0);
            }
        } catch (NullPointerException e) {
            logger.info("Pay rate calculation error");
        }
    }

    /**
     * Gets the earnings from sessions that occurred on the current date.
     * @param sessionList The list of sessions to search through.
     * @return The total earnings from today's sessions.
     */
    private float getTodaysEarnings(ObservableList<Session> sessionList) {
        float totalEarnings = 0;
        LocalDate now = LocalDate.now();

        for (Session session : sessionList) {
            LocalDate sessionDate = LocalDate.parse(session.getStartDateTime().split(" ")[0],
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (sessionDate.isEqual(now)) {
                totalEarnings += session.getTotalPay();
            }
        }
        return totalEarnings;
    }

    private float getWeeklyEarnings(ObservableList<Session> sessionList) {
        float totalEarnings = 0;
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(DayOfWeek.MONDAY);

        for (Session session : sessionList) {
            LocalDate sessionDate = LocalDate.parse(session.getStartDateTime().split(" ")[0],
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (sessionDate.isEqual(now) || sessionDate.isAfter(startOfWeek)) {
                totalEarnings += session.getTotalPay();
            }
        }
        return totalEarnings;
    }

    /**
     * Gets the earnings from sessions that occurred in the current month.
     * @param sessionList The list of sessions to search through.
     * @return The total earnings from this month's sessions.
     */
    private float getMonthlyEarnings(ObservableList<Session> sessionList) {
        float totalEarnings = 0;
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);

        for (Session session : sessionList) {
            LocalDate sessionDate = LocalDate.parse(session.getStartDateTime().split(" ")[0],
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (sessionDate.isEqual(now) || (sessionDate.isAfter(startOfMonth)
                    && sessionDate.isBefore(now.plusDays(1)))) {
                totalEarnings += session.getTotalPay();
            }
        }
        return totalEarnings;
    }

    private float getLifetimeEarnings(ObservableList<Session> sessionList) {
        float totalEarnings = 0;
        for (Session session : sessionList) {
            totalEarnings += session.getTotalPay();
        }
        return totalEarnings;
    }

    private void setStatisticsFieldDisplay(Label[] fields) {
        todayField.setText("Today");
        thisWeekField.setText("This Week");
        thisMonthField.setText("This Month");
        lifetimeField.setText("Lifetime");
        for (Label field: fields) {
            field.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(4),
                    new Insets(-3, -10, -3, -10))));
            VBox parent = (VBox) field.getParent();
            parent.setMargin(field, new Insets(0, 0, 10, 0));
        }
    }


    /**
     * Set up event listener to listen to updates within the ObservableList
     */
    private void setUpdateEventListener(Logic logic) {
        logic.getFilteredSessionList().addListener((ListChangeListener<Session>) change -> {
            getStatistics(logic.getFilteredSessionList());
            Session selectedSession = sessionListView.getSelectionModel().getSelectedItem();
            updateDisplay(selectedSession);
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
        Label[] fields = new Label[]{dateField, locationField, studentsField, earningsField};
        Label[] details = new Label[]{selectedName, selectedDate, selectedLocation, selectedEarnings};
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
        earningsField.setText("Session Earnings");
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
        selectedLocation.setText(selectedSession.getLocation().toString());

        attendanceChart.setData(SessionPieChart.generateAttendancePieChart(selectedSession.getAttendanceMap()));
        attendanceChart.setLabelsVisible(false);

        float sessionEarnings = selectedSession.getTotalPay();
        selectedEarnings.setText("$" + String.format("%.2f", sessionEarnings));
        selectedEarnings.setPadding(new Insets(5, -10, 5, -10));

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

