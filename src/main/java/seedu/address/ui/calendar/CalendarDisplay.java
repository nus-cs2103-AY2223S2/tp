
package seedu.address.ui.calendar;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.logic.CalendarLogic;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;

//@@author wongyewjon
/**
 * A UI component that displays information of a Calendar.
 * Allows for toggling between months and displays a grid layout of the calendar.
 * Uses a {@link CalendarLogic} object to manage the underlying logic.
 */

public class CalendarDisplay extends UiPart<Region> {
    private static final String FXML = "CalendarDisplay.fxml";
    private static final Insets MARGIN = new Insets(0, 50, 0, 0);
    private Stage primaryStage;
    private Logic logic;
    private CalendarLogic calendarLogic;
    private GridPane calendarGrid = new CalendarGrid().getRoot();
    @FXML
    private FlowPane topCalendar = new TopCalendar().getRoot();
    @FXML
    private AnchorPane calendarAnchorPane;
    @FXML
    private VBox calendarDisplay;
    @FXML
    private ScrollPane calendarScrollPane;
    private PreviousButton prevButton;
    private NextButton nextButton;

    //@@author wongyewjon
    /**
     * Creates a CalendarDisplay object with the given {@link Logic} object and {@link Stage} object.
     * @param logic the Logic object to be used.
     * @param primaryStage the Stage object to be used.
     */
    public CalendarDisplay(Logic logic, Stage primaryStage) {
        super(FXML);
        calendarDisplay.getChildren().setAll(topCalendar, calendarScrollPane);
        calendarAnchorPane.getChildren().add(calendarGrid);
        this.calendarLogic = new CalendarLogic(logic, primaryStage, this);
        prevButton = new PreviousButton("Prev", calendarLogic);
        nextButton = new NextButton("Next", calendarLogic);
        this.primaryStage = primaryStage;
        this.logic = logic;
        calendarLogic.initialiseLogic();
        drawCalendar();

    }
    //@@author wongyewjon
    /**
     * Draws the Ui for the Calendar.
     */
    public void drawCalendar() {
        drawHeader();
        calendarLogic.drawBody();
    }

    //@@author wongyewjon
    /**
     * Resets the margin for the given node.
     * @param node the node to have its margin reset.
     */
    public void resetMargin(Node node) {
        topCalendar.setMargin(node, MARGIN);
    }

    //@@author wongyewjon
    /**
     * Resets the calendarGrid.
     */
    public void resetGridPane() {
        topCalendar.getChildren().clear();
        Node node = calendarGrid.getChildren().get(0);
        calendarGrid.getChildren().clear();
        calendarGrid.getChildren().add(0, node);
    }

    //@@author wongyewjon
    /**
     * Resets the calendarGrid's body.
     */
    public void resetCalendarBody() {
        Node node = calendarGrid.getChildren().get(0);
        calendarGrid.getChildren().clear();
        calendarGrid.getChildren().add(0, node);
    }

    //@@author wongyewjon
    private void drawHeader() {
        Text textHeader = calendarLogic.getTextHeader();
        topCalendar.getChildren().addAll(textHeader, prevButton.getRoot(), nextButton.getRoot());
        topCalendar.setMargin(textHeader, MARGIN);
    }

    //@@author wongyewjon
    /**
     * Adds the node to be contained within the calendarGrid at the given column and row index.
     * @param node the node to be added.
     * @param columnIndex the column index for the node.
     * @param rowIndex the row index for the node.
     */
    public void addToCalendarGrid(Node node, int columnIndex, int rowIndex) {
        calendarGrid.add(node, columnIndex, rowIndex);
    }
    //@@author wongyewjon
    public void setTopCalendarHeader(int index, Node node) {
        topCalendar.getChildren().set(index, node);
    }

}

