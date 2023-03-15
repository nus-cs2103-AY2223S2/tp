package seedu.address.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

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

        photo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto().getUrlPath()));

        photo.setCellFactory(
                p -> new PhotoCell()
        );

        //to sort
        //
        // name.setSortable(true);

        table.setItems(personList);
        table.setRowFactory(tableView -> {
            TableRow<Person> row = new TableRow<>();
            return row;
        });
    }

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
