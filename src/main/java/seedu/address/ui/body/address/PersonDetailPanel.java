package seedu.address.ui.body.address;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays detailed information of a {@code Person}.
 */
public class PersonDetailPanel extends UiPart<Region> {
    private static final String FXML = "body/address/PersonDetailPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<PersonDetailCard.DetailCardData> dataList;

    /**
     * Creates a blank {@code PersonDetailPanel}.
     */
    public PersonDetailPanel() {
        super(FXML);
        clearPerson();
    }

    public void setPerson(Person person, int displayedIndex) {
        clearPerson();
        if (person == null) {
            clearPerson();
            return;
        }

        id.setText(displayedIndex + ".");
        name.setText(person.getName().toString());
        person.getTags()
                .stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        ObservableList<PersonDetailCard.DetailCardData> detailCardDataList = FXCollections.observableArrayList(
                new PersonDetailCard.DetailCardData("Phone", person.getPhone().toString()),
                new PersonDetailCard.DetailCardData("Address", person.getAddress().toString()),
                new PersonDetailCard.DetailCardData("Email", person.getEmail().toString())
        );
        dataList.setItems(detailCardDataList);
        dataList.setCellFactory(listView -> {
            DataListViewCell cell = new DataListViewCell();

            /*
             * @@author hansstanley-reused
             * Reused from https://stackoverflow.com/questions/37130122 with minor modifications.
             * This restricts the width of the cells so the horizontal scroll bar does not appear.
             */
            cell.prefWidthProperty().bind(dataList.widthProperty().subtract(20));
            cell.setMaxWidth(Control.USE_PREF_SIZE);
            return cell;
        });
    }

    /**
     * Empties the fields, resulting in a blank {@code PersonDetailPanel}.
     */
    public void clearPerson() {
        id.setText("Select a contact.");
        name.setText(null);
        tags.getChildren().clear();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PersonDetailCard.DetailCardData}
     * using a {@code PersonDetailCard}.
     */
    static class DataListViewCell extends ListCell<PersonDetailCard.DetailCardData> {
        @Override
        protected void updateItem(PersonDetailCard.DetailCardData data, boolean empty) {
            super.updateItem(data, empty);

            if (empty || data == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonDetailCard(data).getRoot());
            }
        }
    }
}
