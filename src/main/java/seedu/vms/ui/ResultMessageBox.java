package seedu.vms.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.vms.logic.commands.CommandResult;

public class ResultMessageBox extends UiPart<Region> {
    private static final String FXML_FILE = "ResultMessageBox.fxml";

    @FXML private Label stateLabel;
    @FXML private Label messageLabel;


    public ResultMessageBox(CommandResult result) {
        super(FXML_FILE);
        stateLabel.setText(String.format("[%s]", result.getState().toString()));
        messageLabel.setText(result.getMessage());
    }
}
