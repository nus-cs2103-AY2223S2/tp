package seedu.address.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
    private TableColumn<Person, String> photo;
    @FXML
    private TextField filterField;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);

        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName().toString()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail().toString()));
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().toString()));
        performance.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPerformance().toString()));
        remark.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRemark().toString()));

        //photo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto().getUrlPath()));
        /*
        photo.setCellFactory(
                p -> new PhotoCell()
        );
        */

        //to sort
        //
        //performance.setSortable(true);

        SortedList<Person> sorted = new SortedList<>(personList);
        table.setItems(sorted);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setRowFactory(tableView -> {
            TableRow<Person> row = new TableRow<>();
            return row;
        });
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
     * Allows the creation of a column with images
     */
    public static class PhotoCell extends TableCell<Person, String> {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(String url, boolean empty) {
            super.updateItem("", empty);
            imageView.setImage(new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/student.png"))));
            imageView.setFitWidth(24);
            imageView.setFitHeight(23);
            setGraphic(imageView);
        }
    }
}
