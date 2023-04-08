package seedu.address.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final int notUrgentThreshold = 20;
    private final int midUrgentThreshold = 60;


    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> email;
    @FXML
    private TableColumn<Person, String> address;
    @FXML
    private TableColumn<Person, String> performance;
    @FXML
    private TableColumn<Person, String> remark;
    @FXML
    private TableColumn<Person, String> telegram;
    @FXML
    private TableColumn<Person, String> photo;
    @FXML
    private TableColumn<Person, Void> index;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     * Restricts mouse events from user.
     * Populates table with necessary student details.
     */
    public PersonListPanel(ObservableList<Person> persons) {
        super(FXML);

        table.addEventFilter(MouseEvent.MOUSE_DRAGGED, MouseEvent::consume);
        table.addEventFilter(MouseEvent.MOUSE_CLICKED, MouseEvent::consume);
        table.setSelectionModel(null);

        initCellValue();

        setIndexColumn();

        wrapName();

        wrapAddress();

        wrapTelegram();

        SortedList<Person> sorted = new SortedList<>(persons);
        table.setItems(sorted);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setRowFactory(tableView -> {
            TableRow<Person> row = new TableRow<>();
            return row;
        });

        setPerformanceColor();

        disableClickSort();

        setPhoto();
    }

    /**
     * Initializes the cell valye for name, email, telegram, address, performance, remark and photo.
     * This ensures that each value in the table cell is unique to the person
     */
    public void initCellValue() {
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().toString()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail().toString()));
        telegram.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPhone().toString()));
        address.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAddress().toString()));
        performance.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPerformance().toString()));
        remark.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRemark().toString()));
        photo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto().getUrlPath()));
    }

    /**
     * Creates an index column based on the number on non-empty rows. Index starts from 1
     */
    public void setIndexColumn() {
        index.setCellFactory(col -> {
            TableCell<Person, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null;
                } else {
                    return Integer.toString(cell.getIndex() + 1);
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell;
        });
    }

    /**
     * Allows a long name to be wrapped around instead of truncating with ellipses. This will allow the user
     * to see the full name of the student and create events related to the student full name
     */
    public void wrapName() {
        name.setCellFactory(param -> {
            return new TableCell<Person, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        //Credit to P113305A009D8M
                        //https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
                        Text text = new Text(item);
                        text.setStyle("-fx-text-alignment:justify; -fx-fill:derive(#828282, -50%)");
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                        setGraphic(text);
                    }
                }
            };
        });
    }

    /**
     * Allows long address to be wrapped instead of truncating with ellipses
     */
    public void wrapAddress() {
        address.setCellFactory(param -> {
            return new TableCell<Person, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        //Credit to P113305A009D8M
                        //https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
                        Text text = new Text(item);
                        text.setStyle("-fx-text-alignment:justify; -fx-fill:derive(#828282, -50%)");
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                        setGraphic(text);
                    }
                }
            };
        });
    }

    /**
     * Allows a long telegram phone number or handle to be wrapped around instead of truncating with ellipses.
     * This allows thw TA to see the full telegram and add the student to any telegram channel
     */
    public void wrapTelegram() {
        telegram.setCellFactory(param -> {
            return new TableCell<Person, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        //Credit to P113305A009D8M
                        //https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
                        Text text = new Text(item);
                        text.setStyle("-fx-text-alignment:justify; -fx-fill:derive(#828282, -50%)");
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                        setGraphic(text);
                    }
                }
            };
        });
    }

    /**
     * Sets the performance indicator color of each student based on a specific set of metrics
     */
    public void setPerformanceColor() {
        performance.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> param) {
                return new TableCell<Person, String>() {
                    public void updateItem(String item, boolean empty) {
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            super.updateItem(item, empty);
                            setText(item);
                            Person aux = getTableView().getItems().get(getIndex());
                            if (aux.getPerformance().calculateUrgency() > midUrgentThreshold) {
                                this.setTextFill(Color.RED);
                            } else if (aux.getPerformance().calculateUrgency() > notUrgentThreshold) {
                                this.setTextFill(Color.ORANGE);
                            } else {
                                this.setTextFill(Color.GREEN);
                            }
                        }
                    }
                };
            }
        });
    }

    /**
     * Disables the default behaviour to sort tableview by clicking on the column for all columns
     */
    public void disableClickSort() {
        photo.setSortable(false);
        name.setSortable(false);
        email.setSortable(false);
        performance.setSortable(false);
        remark.setSortable(false);
        telegram.setSortable(false);
        index.setSortable(false);
        address.setSortable(false);
    }

    /**
     * Maps a random photo stored in the images folder to a student to simulate retrieval of photo from NUS database
     */
    public void setPhoto() {
        photo.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> param) {
                return new TableCell<Person, String>() {
                    public void updateItem(String path, boolean empty) {
                        ImageView imageView = new ImageView();
                        if (path == null || empty) {
                            setGraphic(null);;
                        } else {
                            super.updateItem(path, empty);
                            Image newImage = new Image(Objects.requireNonNull(this.getClass()
                                    .getResourceAsStream(path)));
                            imageView.setImage(newImage);
                            imageView.setFitWidth(24);
                            imageView.setFitHeight(23);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });
    }
}
