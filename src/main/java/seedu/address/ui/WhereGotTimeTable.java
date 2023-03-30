package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import seedu.address.model.timeslot.ScheduleDay;
import seedu.address.model.timeslot.Status;

/**
 * An UI component that displays information of the FreeTimeSlot
 */
public class WhereGotTimeTable extends UiPart<Region> {

    private static final String FXML = "WhereGotTimeTable.fxml";

    private Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>> factory = conditionForColouredCell();

    private final ObservableList<ScheduleDay> data = FXCollections.observableArrayList(
            new ScheduleDay("Monday", new ArrayList<>(Arrays.asList(Status.BUSY, Status.FREE, Status.BUSY,
                    Status.FREE, Status.BUSY))),
            new ScheduleDay("Tuesday", new ArrayList<>(Arrays.asList(Status.FREE, Status.FREE, Status.FREE,
                    Status.BUSY, Status.BUSY))),
            new ScheduleDay("Wednesday", new ArrayList<>(Arrays.asList(Status.FREE, Status.FREE, Status.FREE,
                    Status.BUSY, Status.BUSY))),
            new ScheduleDay("Thursday", new ArrayList<>(Arrays.asList(Status.FREE, Status.FREE, Status.FREE,
                    Status.BUSY, Status.BUSY))),
            new ScheduleDay("Friday", new ArrayList<>(Arrays.asList(Status.FREE, Status.FREE, Status.FREE,
                    Status.BUSY, Status.BUSY))),
            new ScheduleDay("Saturday", new ArrayList<>(Arrays.asList(Status.FREE, Status.FREE, Status.FREE,
                    Status.BUSY, Status.BUSY))),
            new ScheduleDay("Sunday", new ArrayList<>(Arrays.asList(Status.FREE, Status.FREE, Status.FREE,
                    Status.BUSY, Status.BUSY))));

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

    private ArrayList<TableColumn<ScheduleDay, Status>> allColumns;
    private ArrayList<String> columnNames;


    /**
     * Creates a table with the given {@code ScheduleDay} to display.
     */
    public WhereGotTimeTable() {
        super(FXML);
        allColumns = new ArrayList<>(Arrays.asList(time0, time1, time2, time3, time4));
        columnNames = new ArrayList<>(Arrays.asList("time0", "time1", "time2", "time3", "time4"));

        day.setCellValueFactory(new PropertyValueFactory<ScheduleDay, String>("day"));

        updateTable(data);

    }

    /**
     * Creates a callback and edits every cells in the columns to have their own unique background colour
     * according to the Status.
     */
    private Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>> conditionForColouredCell() {
        Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>> factory = new Callback<TableColumn<ScheduleDay, Status>, TableCell<ScheduleDay, Status>>() {
            @Override
            public TableCell<ScheduleDay, Status> call(TableColumn<ScheduleDay, Status> param) {
                return new TableCell<ScheduleDay, Status>() {
                    @Override
                    protected void updateItem(Status item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else if (item.equals(Status.FREE)) {
                            setText(item.toString());
                            setStyle("-fx-background-color: #71A89D");
                        } else if (item.equals(Status.BUSY)) {
                            setText(item.toString());
                            setStyle("-fx-background-color: transparent");
                        } else {
                            setStyle("-fx-background-color: grey");
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
            currColumn.setCellValueFactory(new PropertyValueFactory<ScheduleDay, Status>(columnNames.get(i)));
            currColumn.setCellFactory(factory);

        }

        table.setFixedCellSize(25);
        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));

        table.setItems(newData);
    }

}
