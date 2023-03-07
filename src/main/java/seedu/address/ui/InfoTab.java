package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;


public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";

    @FXML
    private Label name;

    @FXML
    private Label identity;

    @FXML
    private StackPane detailedInfo;

    public InfoTab(Person protagonist) {
        super(FXML);
        name.setText(protagonist.getName().fullName);
    }

}

/*
Upper tab and lower tab
upper is the contact/modules/tags
lower is the info
 */