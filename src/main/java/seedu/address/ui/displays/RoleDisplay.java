package seedu.address.ui.displays;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import seedu.address.model.job.Role;

public final class RoleDisplay {

    private RoleDisplay() {}

    public static Node of(Role roleToDisplay) {
        final TextArea textArea = new TextArea(roleToDisplay.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        return textArea;
    }
}
