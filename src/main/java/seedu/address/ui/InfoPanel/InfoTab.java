package seedu.address.ui.InfoPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;


public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";

    private DetailedInfo detailedInfo;

    @FXML
    private Label name;

    @FXML
    private Label identity;

    @FXML
    private StackPane detailedInfoPlaceholder;

    public InfoTab(Person protagonist, String tab) {
        super(FXML);
        if (tab.equals("c")) {
            detailedInfo = new DetailedContact();
        } else if (tab.equals("m")) {
            detailedInfo = new DetailedModule();
        } else {
            detailedInfo = new DetailedSkill();
        }

        name.setText(protagonist.getName().fullName);
        detailedInfoPlaceholder.getChildren().add(detailedInfo.getRoot());
    }

}