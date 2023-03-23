package seedu.address.ui.views;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class StringView {
    private StringView() {}

    public static Node from(String message) {
        final TextArea text = new TextArea(message);
        text.getStyleClass().add("result-display");
        text.setEditable(false);
        text.setWrapText(true);
        return text;
    }
}
