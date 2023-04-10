package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import seedu.address.model.time.ScheduleDay;
import seedu.address.model.time.ScheduleWeek;
import seedu.address.model.time.Status;

/**
 * An UI component that displays information of the FreeTimeSlot
 */
public class WhereGotTimeTable extends UiPart<Region> {

    private static final String FXML = "WhereGotTimeTable.fxml";

    private Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>> factory =
            conditionForColouredCell();

    private ScheduleWeek scheduleWeek = new ScheduleWeek();

    @FXML
    private TableView<ScheduleDay> table;

    @FXML
    private TableColumn<ScheduleDay, String> day;

    @FXML
    private TableColumn<ScheduleDay, Status> time0;

    @FXML
    private TableColumn<ScheduleDay, Status> time1;

    @FXML
    private TableColumn<ScheduleDay, Status> time2;

    @FXML
    private TableColumn<ScheduleDay, Status> time3;

    @FXML
    private TableColumn<ScheduleDay, Status> time4;
    @FXML
    private TableColumn<ScheduleDay, Status> time5;
    @FXML
    private TableColumn<ScheduleDay, Status> time6;
    @FXML
    private TableColumn<ScheduleDay, Status> time7;
    @FXML
    private TableColumn<ScheduleDay, Status> time8;
    @FXML
    private TableColumn<ScheduleDay, Status> time9;
    @FXML
    private TableColumn<ScheduleDay, Status> time10;
    @FXML
    private TableColumn<ScheduleDay, Status> time11;

    @FXML
    private TableColumn<ScheduleDay, Status> time12;

    @FXML
    private TableColumn<ScheduleDay, Status> time13;

    @FXML
    private TableColumn<ScheduleDay, Status> time14;

    @FXML
    private TableColumn<ScheduleDay, Status> time15;

    @FXML
    private TableColumn<ScheduleDay, Status> time16;

    @FXML
    private TableColumn<ScheduleDay, Status> time17;

    @FXML
    private TableColumn<ScheduleDay, Status> time18;

    @FXML
    private TableColumn<ScheduleDay, Status> time19;

    @FXML
    private TableColumn<ScheduleDay, Status> time20;

    @FXML
    private TableColumn<ScheduleDay, Status> time21;

    @FXML
    private TableColumn<ScheduleDay, Status> time22;

    @FXML
    private TableColumn<ScheduleDay, Status> time23;

    private ArrayList<TableColumn<ScheduleDay, Status>> allColumns;
    private ArrayList<String> columnNames;

    /**
     * Creates a table with the given {@code ScheduleDay} to display.
     */
    public WhereGotTimeTable() {
        super(FXML);
        allColumns = new ArrayList<>(Arrays.asList(time0, time1, time2, time3, time4, time5, time6, time7, time8,
        time9, time10, time11, time12, time13, time14, time15, time16, time17, time18, time19, time20, time21,
        time22, time23));
        columnNames = new ArrayList<>(Arrays.asList("time0", "time1", "time2", "time3", "time4", "time5", "time6",
                "time7", "time8", "time9", "time10", "time11", "time12", "time13", "time14", "time15", "time16",
                "time17", "time18", "time19", "time20", "time21", "time22", "time23"));

        day.setCellValueFactory(new PropertyValueFactory<>("day"));

        day.setReorderable(false);
        day.setResizable(false);
        day.setSortable(false);
        for (int i = 0; i < allColumns.size(); i++) {
            allColumns.get(i).setReorderable(false);
            allColumns.get(i).setResizable(false);
            allColumns.get(i).setSortable(false);
        }

        updateTable(scheduleWeek.getInternalList());

    }

    /**
     * Creates a callback and edits every cells in the columns to have their own unique background colour
     * according to the Status.
     */
    private Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>> conditionForColouredCell() {
        Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>> factory = new Callback<>() {
            @Override
            public TableCell<ScheduleDay, Status> call(TableColumn<ScheduleDay, Status> param) {
                return new TableCell<ScheduleDay, Status>() {
                    @Override
                    protected void updateItem(Status item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setStyle("-fx-text-fill:black;");
                        } else if (item.equals(Status.FREE)) {
                            setText(item.toString());
                            setStyle("-fx-background-color: #C1E1C1;" + "-fx-text-fill:black;");
                        } else if (item.equals(Status.BUSY)) {
                            setText(item.toString());
                            setStyle("-fx-background-color: #D88C9A;" + "-fx-text-fill:black;");
                        } else {
                            setStyle("-fx-background-color: transparent;");
                        }
                    }
                };
            }

        };
        return factory;
    }

    /**
     * Sets the WhereGotTimeTable with a new data
     * @param newData
     */
    public void updateTable(ObservableList<ScheduleDay> newData) {
        for (int i = 0; i < allColumns.size(); i++) {
            TableColumn<ScheduleDay, Status> currColumn = allColumns.get(i);
            currColumn.setCellValueFactory(new PropertyValueFactory<>(columnNames.get(i)));
            currColumn.setCellFactory(factory);

        }
        table.setFixedCellSize(25);
        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
        table.getColumns().forEach(column -> column.setMinWidth(50));

        table.setItems(newData);
    }

}
