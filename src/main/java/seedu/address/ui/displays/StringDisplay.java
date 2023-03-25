package seedu.address.ui.displays;

import javafx.scene.Node;
import javafx.scene.control.Label;

public final class StringDisplay {

    private StringDisplay() {}

    public static Node of(String message) {
        final Label output = new Label(message);
        output.setWrapText(true);
        return output;
    }
}
