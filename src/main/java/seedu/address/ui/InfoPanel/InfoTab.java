package seedu.address.ui.InfoPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;


public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";

    @FXML
    private Label name;

    @FXML
    private Label identity;

    @FXML
    private StackPane detailedInfo;

    private InfoContact infoContact;
    private InfoModule infoModule;
    private InfoSkill infoSkill;

    public InfoTab(Person protagonist) {
        super(FXML);
        infoContact = new InfoContact();
        infoModule = new InfoModule();
        infoSkill = new InfoSkill();
        name.setText(protagonist.getName().fullName);
        detailedInfo.getChildren().add(infoContact.getRoot());
    }

}

/*
Upper tab and lower tab
upper is the contact/modules/tags
lower is the info
 */